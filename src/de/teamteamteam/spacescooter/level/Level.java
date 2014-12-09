package de.teamteamteam.spacescooter.level;

import de.teamteamteam.spacescooter.background.CloudBackground;
import de.teamteamteam.spacescooter.background.EarthBackground;
import de.teamteamteam.spacescooter.background.StarBackground;
import de.teamteamteam.spacescooter.brain.GameConfig;
import de.teamteamteam.spacescooter.brain.PlayerSession;
import de.teamteamteam.spacescooter.datastructure.ConcurrentIterator;
import de.teamteamteam.spacescooter.entity.Entity;
import de.teamteamteam.spacescooter.entity.Player;
import de.teamteamteam.spacescooter.entity.enemy.Enemy;
import de.teamteamteam.spacescooter.entity.enemy.EnemyBoss;
import de.teamteamteam.spacescooter.entity.enemy.EnemyOne;
import de.teamteamteam.spacescooter.entity.enemy.EnemyThree;
import de.teamteamteam.spacescooter.entity.enemy.EnemyTwo;
import de.teamteamteam.spacescooter.entity.obstacle.Obstacle;
import de.teamteamteam.spacescooter.entity.obstacle.StoneOne;
import de.teamteamteam.spacescooter.entity.obstacle.StoneThree;
import de.teamteamteam.spacescooter.entity.obstacle.StoneTwo;
import de.teamteamteam.spacescooter.screen.GameScreen;
import de.teamteamteam.spacescooter.screen.Screen;
import de.teamteamteam.spacescooter.sound.SoundSystem;
import de.teamteamteam.spacescooter.utility.Loader;

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
	 * Thread handle for the backgroundMusic being played.
	 */
	private Thread backgroundMusic;
	
	/**
	 * Indicator whether the game is over.
	 * This will be updated within handleUpdateTick() and helper methods.
	 */
	private boolean isGameOver;
	
	/**
	 * A counter determining the amount of time to wait before revealing
	 * that the game is over (meaning isGameOver() returning true).
	 */
	private int gameOverDelay;

	/**
	 * Tells how the game over has to be interpreted.
	 * True - player won, False - player lost.
	 */
	private boolean playerWon;

	/**
	 * EntityIterator to evaluate the state of all the existing Entities.
	 */
	private ConcurrentIterator<Entity> entityIterator;
	
	/**
	 * Constructor creating a LevelConfig based on a given config file.
	 */
	public Level(String levelConfig) {
		this.levelClock = 0;
		this.isGameOver = false;
		this.playerWon = false;
		this.gameOverDelay = 3;
		this.config = Loader.getLevelConfigByFilename(levelConfig);
		this.entityIterator = Screen.currentScreen.createEntityIterator();
	}
	
	/**
	 * Initialize the level based on the LevelConfig attributes.
	 */
	public void doBuildUp() {
		this.spawnEntityByAvailableName(Entity.availableNames.valueOf(this.config.background), 0, 50);
		GameScreen.setPlayer(new Player(200, 300));
		this.backgroundMusic = SoundSystem.playSound(this.config.backgroundMusic);
	}
	
	/**
	 * The magic will happen in here.
	 * Each time the Level will receive its updateTick,
	 * it will do some checks, increase an internal counter and do
	 * evil stuff like looking up what monsters to spawn and whatever else
	 * is necessary to torture the player.
	 */
	public void handleUpdateTick() {
		//Check whether the current interval is configured
		int currentIntervalIndex = this.config.getIntervalIndexByCurrentTime(this.levelClock);
		
		//If there are still configured intervals, take care of the rules
		if(currentIntervalIndex != -1) {
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
			 * - 4: SpawnPosition - percentage of GameConfig.windowHeight - Where the Enemy shall spawn.
			 */
			for(int[] spawnRule : this.config.spawnRuleList) {
				//Skip spawn rules that are not in the current spawn interval.
				if(spawnRule[0] != currentIntervalIndex) continue;
				//Divide the current interval by spawnrate
				int intervalModulus = intervalLength / spawnRule[3];
				//Check whether the spawn rate strikes right now.
				if(relativeTimeWithinCurrentInterval % Math.max(1,intervalModulus) == 0) {
					//If the rule matches the current time, spawn the configured Entity in the configured amount:
					for(int i=0; i<spawnRule[2]; i++) {
						//Minus one because the upper border is _excluded_ from the range!
						int x = GameConfig.gameScreenWidth + GameConfig.gameScreenXOffset - 1;
						//Minus one because the upper border is _excluded_ from the range!
						int y = Math.round((GameConfig.gameScreenHeight * spawnRule[4]) / 100) + GameConfig.gameScreenYOffset - 1;
						this.spawnEntityByAvailableName(Entity.availableNames.values()[spawnRule[1]], x, y);
					}
				}
			}
		} //end if still intervals configured
		
		//Check for GameOver things.
		this.checkGameOverCondition();
		
		//Apply the gameOverDelay if game is already over.
		if(this.isGameOver && this.gameOverDelay > 0) {
			this.gameOverDelay--;
		}
		
		//Increase levelClock
		this.levelClock++;
	}
	
	/**
	 * Evaluates things like whether the Player is alive or
	 * - if there is a bossfight - if the boss is dead.
	 * Also checks whether the player has survived everything (won)
	 */
	private void checkGameOverCondition() {
		if(!GameScreen.getPlayer().isAlive()) {
			this.isGameOver = true;
			this.playerWon = false;
		}
		int enemyCounter = 0;
		int obstacleCounter = 0;
		this.entityIterator.reset();
		while(this.entityIterator.hasNext()) {
			Entity e = this.entityIterator.next();
			if(e instanceof Enemy) enemyCounter++;
			if(e instanceof Obstacle) obstacleCounter++;
		}
		
		//use the currentIntervalIndex to determine whether there are things scheduled to spawn.
		int currentIntervalIndex = this.config.getIntervalIndexByCurrentTime(this.levelClock);
		if(enemyCounter == 0 && obstacleCounter == 0 && GameScreen.getPlayer().isAlive() && currentIntervalIndex == -1) {
			this.isGameOver = true;
			this.playerWon = true;
			//Update the next Level
			PlayerSession.setNextLevel(this.config.nextLevel);
		}
	}

	/**
	 * Tell whether the Game is over or not.
	 * Takes into account the delay, so a player can properly explode.
	 */
	public boolean isGameOver() {
		return (this.gameOverDelay == 0);
	}
	
	/**
	 * Tell whether the player won the game.
	 */
	public boolean playerHasWon() {
		return this.playerWon;
	}

	/**
	 * Clean up before the Level is torn down.
	 * Stop the music, ...
	 */
	public void tearDown() {
		if(this.backgroundMusic != null) {
			this.backgroundMusic.interrupt();
			this.backgroundMusic = null;
		}
	}
	
	/**
	 * Spawn an Entity by name on the given position.
	 * Uses Entity.availableNames to determine the actual Entity to spawn.
	 */
	private void spawnEntityByAvailableName(Entity.availableNames entity, int x, int y) {
		switch(entity) {
			case StarBackground:
				new StarBackground(x, y);
				break;
			case CloudBackground:
				new CloudBackground(x, y);
				break;
			case EarthBackground:
				new EarthBackground(x, y);
				break;
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
			case StoneOne:
				new StoneOne(x, y);
				break;
			case StoneTwo:
				new StoneTwo(x, y);
				break;
			case StoneThree:
				new StoneThree(x, y);
				break;
			default:
				System.err.println("I don't know how to spawn this: " + entity);
				break;
		}
	}

}
