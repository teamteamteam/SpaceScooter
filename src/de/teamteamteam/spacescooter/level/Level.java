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
import de.teamteamteam.spacescooter.entity.Entity.availableNames;
import de.teamteamteam.spacescooter.screen.GameScreen;
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
	private long levelClock;
	
	
	/**
	 * Constructor creating a LevelConfig based on a given config file.
	 */
	public Level(String levelConfig) {
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
	private void spawnEntityByName(String entityName, int x, int y) {
		availableNames entity = Entity.availableNames.valueOf(entityName);
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
				System.err.println("Fuck you, i don't know what you mean with this!");
				break;
		}
	}
}
