package de.teamteamteam.spacescooter.level;

import java.util.ArrayList;
import java.util.List;

public class LevelConfig {

	public String name;
	public String background;
	public String backgroundMusic;
	public String bossEnemy;
	
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
	public List<int[]> ruleList;
	
	
	public LevelConfig() {
		this.intervalList = new ArrayList<int[]>();
		this.ruleList = new ArrayList<int[]>();
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
		sb.append(" bossEnemy=");
		sb.append(this.bossEnemy);
		sb.append("\\\n\tRules:\n");
		for(int[] rule : this.ruleList) {
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
		if(this.getIntervalByBorders(intervalStart, intervalEnd) != -1) {
			System.err.println("Duplicate interval - not added!");
		} else {
			int[] newInterval= {intervalStart, intervalEnd};
			this.intervalList.add(newInterval);
		}
	}
	
	/**
	 * Return index of given interval.
	 * Returns -1 in case the interval is not in the list.
	 */
	public int getIntervalByBorders(int intervalStart, int intervalEnd) {
		for(int[] interval : this.intervalList) {
			if(interval[0] == intervalStart && interval[1] == intervalEnd) {
				return this.intervalList.indexOf(interval);
			}
		}
		return -1;
	}
	
	/**
	 * Add a given EntitySpawnRule to the ruleList.
	 */
	public void addEntitySpawnRule(int intervalStart, int intervalEnd, String enemyName, int amount, int spawnRate) {
		System.out.println("Adding rule for " + intervalStart + " to " + intervalEnd + ": " + enemyName + ", " + amount + ", " + spawnRate);
		if(this.getIntervalByBorders(intervalStart, intervalEnd) == -1) {
			System.err.println("No Interval for rule found!");
			System.err.println("Rule: " + intervalStart + " to " + intervalEnd + ": " + enemyName + ", " + amount + ", " + spawnRate);
		} else {
			int enemyNumber = -1; //TODO: implement this!
			int[] newRule = {enemyNumber, amount, spawnRate};
			this.ruleList.add(newRule);
		}
	}
	
}
