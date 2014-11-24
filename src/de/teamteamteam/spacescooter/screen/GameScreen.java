package de.teamteamteam.spacescooter.screen;

import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.event.KeyEvent;
import java.util.ArrayList;

import de.teamteamteam.spacescooter.background.StarBackground;
import de.teamteamteam.spacescooter.control.Keyboard;
import de.teamteamteam.spacescooter.entity.Player;
import de.teamteamteam.spacescooter.entity.enemy.EnemyBoss;
import de.teamteamteam.spacescooter.entity.enemy.EnemyFour;
import de.teamteamteam.spacescooter.entity.enemy.EnemyThree;
import de.teamteamteam.spacescooter.entity.item.ItemChance;
import de.teamteamteam.spacescooter.gui.HealthBar;
import de.teamteamteam.spacescooter.gui.InterfaceBar;
import de.teamteamteam.spacescooter.gui.ScoreBar;
import de.teamteamteam.spacescooter.gui.ShieldBar;
import de.teamteamteam.spacescooter.utility.CollisionHandler;

/**
 * In this GameScreen, the actual gameplay takes place.
 * All Entities are updated and painted by it.
 * Also, it offers the GamePausedScreen when the user presses VK_ESCAPE.
 * When the Player died in the game, the GameOverScreen replaces this Screen.
 */
public class GameScreen extends Screen {

	/**
	 * Road points for EnemyFour
	 */
	private ArrayList<Point> points = new ArrayList<Point>();
	
	private static Player player;
	
	public GameScreen(Screen parent) {
		super(parent);
		new ItemChance();
		points.add(new Point(300,300));
		points.add(new Point(600,100));
		points.add(new Point(0,500));
		new StarBackground(0, 50);
		GameScreen.player = new Player(200, 300);
		new InterfaceBar(0, 0);
		new HealthBar(10, 5);
		new ShieldBar(10, 27);
		new ScoreBar(575, 33);
		new EnemyFour(800, 400, points);
		new EnemyThree(450, 100);
		new EnemyBoss(200, 300);
	}

	@Override
	protected void paint(Graphics2D g) {
		this.entityPaintIterator.reset();
		while (this.entityPaintIterator.hasNext()) {
			this.entityPaintIterator.next().paint(g);
		}
	}

	@Override
	protected void update() {
		this.entityUpdateIterator.reset();
		while (this.entityUpdateIterator.hasNext()) {
			this.entityUpdateIterator.next().update();
		}
		// Pass the collision handler a copy of the entity list
		CollisionHandler.handleCollisions();
		if (Keyboard.isKeyDown(KeyEvent.VK_ESCAPE)) {
			this.setOverlay(new GamePausedScreen(this));
		}
		if (!GameScreen.player.isAlive()) {
			this.parent.setOverlay(new GameOverScreen(this.parent));
		}
	}
	
	public static Player getPlayer() {
		return GameScreen.player;
	}
	
}

