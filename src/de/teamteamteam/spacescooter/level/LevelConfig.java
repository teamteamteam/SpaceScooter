package de.teamteamteam.spacescooter.level;

import java.util.ArrayList;
import java.util.List;

import de.teamteamteam.spacescooter.entity.Entity;

/**
 * The actual LevelConfig.
 * It contains all the important details that are required to build the level up
 * and fill it with hot living action.
 * Its attributes are read by the LevelConfigParser from the files in res/levels/*.level .
 */
public class LevelConfig {

	/**
	 * The levels name.
	 */
	public String name;
	
	/**
	 * The background the level will be using.
	 */
	public String background;
	
	/**
	 * The background music this level will use.
	 */
	public String backgroundMusic;
	
	/**
	 * Intervals have a start and an end.
	 * They are put within this list in a sorted manner, ascending in values.
	 * They are guaranteed to not overlap.
	 */
	public List<int[]> intervalList;
	
	/**
	 * The entitySpawnRules are encoded here.
	 * The first index is simply the number of the rule.
	 * The second index reveals the values of the rule:
	 * - 0: Interval this rule belongs to
	 * - 1: EntityNumber - This helps to find out what Entity to actually spawn.
	 * - 2: Amount - The amount of Entities to spawn at a time.
	 * - 3: SpawnRate - The rate at which the Entities are supposed to be spawned.
	 */
	public List<int[]> spawnRuleList;
	
	
	public LevelConfig() {
		this.intervalList = new ArrayList<int[]>();
		this.spawnRuleList = new ArrayList<int[]>();
	}
	
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("[LevelConfig");
		sb.append(" name=");
		sb.append(this.name);
		sb.append(" background=");
		sb.append(this.background);
		sb.append(" backgroundMusic=");
		sb.append(this.backgroundMusic);
		sb.append("\\\n\tRules:\n");
		for(int[] rule : this.spawnRuleList) {
			sb.append("\t");
			sb.append(rule);
			sb.append("\n");
		}
		sb.append("]");
		return sb.toString();
	}

	/**
	 * Add a given interval to the list in case it is not a duplicate.
	 * TODO: Catch overlapping intervals and more!
	 */
	public void addIntervalToList(int intervalStart, int intervalEnd) {
		if(intervalStart >= intervalEnd) {
			throw new LevelConfigException("Interval borders are invalid! intervalStart must be < intervalEnd!");
		} else if(this.getIntervalIndexByBorders(intervalStart, intervalEnd) != -1) {
			throw new LevelConfigException("Duplicate interval - not added!");
		} else {
			if(this.intervalList.size() > 0) {
				int[] lastIntervalInList = this.intervalList.get(this.intervalList.size() - 1);
				if(intervalStart < lastIntervalInList[1]) {
					throw new LevelConfigException("Intervals must be added in order!");
				}
			}
			int[] newInterval= {intervalStart, intervalEnd};
			this.intervalList.add(newInterval);
		}
	}
	
	/**
	 * Return index of given interval.
	 * Returns -1 in case the interval is not in the list.
	 */
	public int getIntervalIndexByBorders(int intervalStart, int intervalEnd) {
		for(int[] interval : this.intervalList) {
			if(interval[0] == intervalStart && interval[1] == intervalEnd) {
				return this.intervalList.indexOf(interval);
			}
		}
		return -1;
	}
	
	/**
	 * Returns the index of the current interval the given time fits into.
	 */
	public int getIntervalIndexByCurrentTime(int time) {
		for(int[] interval : this.intervalList) {
			if(time >= interval[0] && time < interval[1]) {
				return this.intervalList.indexOf(interval);
			}
		}
		return -1;
	}
	
	/**
	 * Add a given EntitySpawnRule to the ruleList.
	 */
	public void addEntitySpawnRule(int intervalStart, int intervalEnd, String entityName, int amount, int spawnRate) {
		int intervalIndex = this.getIntervalIndexByBorders(intervalStart, intervalEnd);
		if(intervalIndex == -1) {
			throw new LevelConfigException("No Interval for rule found!\nRule: " + intervalStart + " to " + intervalEnd + ": " + entityName + ", " + amount + ", " + spawnRate);
		} else {
			int enemyNumber = Entity.availableNames.valueOf(entityName).ordinal();
			int[] newRule = {intervalIndex, enemyNumber, amount, spawnRate};
			this.spawnRuleList.add(newRule);
		}
	}
	
}
