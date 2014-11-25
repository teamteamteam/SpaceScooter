package de.teamteamteam.spacescooter.level;

import java.awt.event.KeyEvent;
import de.teamteamteam.spacescooter.background.StarBackground;
import de.teamteamteam.spacescooter.control.Keyboard;
import de.teamteamteam.spacescooter.entity.Player;
import de.teamteamteam.spacescooter.entity.enemy.EnemyBoss;
import de.teamteamteam.spacescooter.entity.enemy.EnemyOne;
import de.teamteamteam.spacescooter.entity.enemy.EnemyThree;
import de.teamteamteam.spacescooter.entity.enemy.EnemyTwo;
import de.teamteamteam.spacescooter.entity.Entity;
import de.teamteamteam.spacescooter.screen.GameScreen;
import de.teamteamteam.spacescooter.utility.GameConfig;
import de.teamteamteam.spacescooter.utility.Loader;
import de.teamteamteam.spacescooter.utility.Random;

/**
 * Implementation of the actual level based gameplay logic.
 * This guy takes care of building up the basics at the very beginning,
 * spawning all the entities in the right moments, doing logic concerning whether
 * the game is over or not and other "event"-based stuff that can be configured in the
 * Levelconfig.
 */
public final class Level {

	/**
	 * Internal LevelConfig containing all the details about how this Level will manage GamePlay.
	 */
	private LevelConfig config;
	
	/**
	 * Internal levelClock counter thing.
	 * This is the "level time, we will use in our configs and such.
	 */
	private int levelClock;
	
	
	/**
	 * Constructor creating a LevelConfig based on a given config file.
	 */
	public Level(String levelConfig) {
		this.levelClock = 0;
		this.config = Loader.getLevelConfigByFilename(levelConfig);
		System.out.println(this.config);
	}
	
	
	/**
	 * Initialize the level based on the LevelConfig attributes.
	 */
	public void doBuildUp() {
		new StarBackground(0, 50);
		GameScreen.setPlayer(new Player(200, 300));
	}
	
	/**
	 * The magic will happen in here.
	 * Each time the Level will receive its updateTick,
	 * it will do some checks, increase an internal counter and do
	 * evil stuff like looking up what monsters to spawn and whatever else
	 * is necessary to torture the player.
	 */
	public void handleUpdateTick() {
		//Debug Spawn Enemy on Press
		if (Keyboard.isKeyDown(KeyEvent.VK_1)) {
			new EnemyOne(400,400);
		}
		if (Keyboard.isKeyDown(KeyEvent.VK_2)) {
			new EnemyTwo(400,400);
		}
		if (Keyboard.isKeyDown(KeyEvent.VK_3)) {
			new EnemyThree(400,400);
		}
		if (Keyboard.isKeyDown(KeyEvent.VK_0)) {
			new EnemyBoss(400,400);
		}
		//Check whether the current interval is configured
		int currentIntervalIndex = this.config.getIntervalIndexByCurrentTime(this.levelClock);
		if(currentIntervalIndex == -1) return; //Nothing to do
		//Fetch the current interval
		int[] currentInterval = this.config.intervalList.get(currentIntervalIndex);
		int relativeTimeWithinCurrentInterval = this.levelClock - currentInterval[0];
		int intervalLength = currentInterval[1] - currentInterval[0];
		/*
		 * @see <LevelConfig>
		 * Get all the spawnrules for the current interval.
		 * - 0: Interval this rule belongs to
		 * - 1: EntityNumber - This helps to find out what Entity to actually spawn.
		 * - 2: Amount - The amount of Entities to spawn at a time.
		 * - 3: SpawnRate - The rate at which the Entities are supposed to be spawned.
		 */
		for(int[] spawnrule : this.config.spawnRuleList) {
			//Skip spawn rules that are not in the current spawn interval.
			if(spawnrule[0] != currentIntervalIndex) continue;
			//Divide the current interval by spawnrate
			int intervalModulus = intervalLength / spawnrule[3];
			//Check whether the spawn rate strikes right now.
			if(relativeTimeWithinCurrentInterval % Math.max(1,intervalModulus) == 0) {
				//If the rule matches the current time, spawn the configured Entity in the configured amount:
				for(int i=0; i<spawnrule[2]; i++) {
					//TODO: More beautiful positions!
					this.spawnEntityByAvailableName(Entity.availableNames.values()[spawnrule[1]], GameConfig.windowWidth - 75, Random.nextInt(GameConfig.windowHeight));
				}
			}
		}
		//Increase levelClock
		this.levelClock++;
	}
	
	/**
	 * Tell whether the Game is over or not.
	 * Evaluates things like whether the Player is alive or
	 * - if there is a bossfight - if the boss is dead.
	 */
	public boolean isGameOver() {
		return !GameScreen.getPlayer().isAlive();
	}
	
	/**
	 * Spawn an Entity by name on the given position.
	 * Uses Entity.availableNames to determine the actual Entity to spawn.
	 */
	private void spawnEntityByAvailableName(Entity.availableNames entity, int x, int y) {
		switch(entity) {
			case EnemyOne:
				new EnemyOne(x, y);
				break;
			case EnemyTwo:
				new EnemyTwo(x, y);
				break;
			case EnemyThree:
				new EnemyThree(x, y);
				break;
			case EnemyFour:
				//TODO: FIX CONSTRUCTOR new EnemyFour(x, y);
				break;
			case EnemyBoss:
				new EnemyBoss(x, y);
				break;
			default:
				System.err.println("Fuck you, i don't know what you mean with this: " + entity);
				break;
		}
	}
}
