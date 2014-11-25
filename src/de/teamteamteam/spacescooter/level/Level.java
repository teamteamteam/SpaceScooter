package de.teamteamteam.spacescooter.level;

import java.awt.Point;
import java.util.ArrayList;

import de.teamteamteam.spacescooter.background.StarBackground;
import de.teamteamteam.spacescooter.entity.Player;
import de.teamteamteam.spacescooter.entity.enemy.EnemyBoss;
import de.teamteamteam.spacescooter.entity.enemy.EnemyFour;
import de.teamteamteam.spacescooter.entity.enemy.EnemyThree;
import de.teamteamteam.spacescooter.entity.item.ItemChance;
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
		
		
		new ItemChance();
		ArrayList<Point> points = new ArrayList<Point>();
		points.add(new Point(300,300));
		points.add(new Point(600,100));
		points.add(new Point(0,500));
		new EnemyFour(800, 400, points);

		new EnemyThree(450, 100);
		new EnemyBoss(200, 300);
	}
	
	/**
	 * The magic will happen in here.
	 * Each time the Level will receive its updateTick,
	 * it will do some checks, increase an internal counter and do
	 * evil stuff like looking up what monsters to spawn and whatever else
	 * is neccessary to torture the player.
	 */
	public void handleUpdateTick() {
		
	}
	
	/**
	 * Tell whether the Game is over or not.
	 * Evaluates things like whether the Player is alive or
	 * - if there is a bossfight - if the boss is dead.
	 */
	public boolean isGameOver() {
		return !GameScreen.getPlayer().isAlive();
	}
}
