package de.teamteamteam.spacescooter.screen;

import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.event.KeyEvent;
import java.util.ArrayList;

import de.teamteamteam.spacescooter.background.StarBackground;
import de.teamteamteam.spacescooter.control.Keyboard;
import de.teamteamteam.spacescooter.datastructure.ConcurrentIterator;
import de.teamteamteam.spacescooter.entity.Entity;
import de.teamteamteam.spacescooter.entity.Player;
import de.teamteamteam.spacescooter.entity.enemy.EnemyFour;
import de.teamteamteam.spacescooter.entity.enemy.EnemyThree;
import de.teamteamteam.spacescooter.entity.item.ItemChance;
import de.teamteamteam.spacescooter.gui.HealthBar;
import de.teamteamteam.spacescooter.utility.CollisionHandler;

/**
 * In this GameScreen, the actual gameplay takes place.
 * All Entities are updated and painted by it.
 * Also, it offers the GamePausedScreen when the user presses VK_ESCAPE.
 * When the Player died in the game, the GameOverScreen replaces this Screen.
 */
public class GameScreen extends Screen {

	private ArrayList<Point> points = new ArrayList<Point>();
	
	private Player player;
	
	public GameScreen(Screen parent) {
		super(parent);
		new ItemChance();
		points.add(new Point(300,300));
		points.add(new Point(600,100));
		points.add(new Point(0,500));
		new StarBackground(0, 0);
		this.player = new Player(200, 300);
		new HealthBar(10, 10);
		new EnemyFour(800, 400, points);
		new EnemyThree(650, 300);
		new EnemyThree(450, 100);
	}

	@Override
	protected void paint(Graphics2D g) {
		ConcurrentIterator<Entity> i = this.getEntityIterator();
		while (i.hasNext()) {
			i.next().paint(g);
		}
	}

	@Override
	protected void update() {
		ConcurrentIterator<Entity> i = this.getEntityIterator();
		while (i.hasNext()) {
			i.next().update();
		}
		// Pass the collision handler a copy of the entity list
		CollisionHandler.handleCollisions();
		if (Keyboard.isKeyDown(KeyEvent.VK_ESCAPE)) {
			this.setOverlay(new GamePausedScreen(this));
		}
		if (!this.player.isAlive()) {
			this.parent.setOverlay(new GameOverScreen(this.parent));
		}
	}
	
}

