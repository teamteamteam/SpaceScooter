package de.teamteamteam.spacescooter.screen;

import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import de.teamteamteam.spacescooter.background.StarBackground;
import de.teamteamteam.spacescooter.background.item.ItemChance;
import de.teamteamteam.spacescooter.control.Keyboard;
import de.teamteamteam.spacescooter.entity.Entity;
import de.teamteamteam.spacescooter.entity.HealthBar;
import de.teamteamteam.spacescooter.entity.Player;
import de.teamteamteam.spacescooter.entity.enemy.EnemyFour;
import de.teamteamteam.spacescooter.entity.enemy.EnemyThree;

public class GameScreen extends Screen {

	private ArrayList<Point> points = new ArrayList<Point>();
	
	public GameScreen(Screen parent) {
		super(parent);
		new ItemChance();
		points.add(new Point(300,300));
		points.add(new Point(600,100));
		points.add(new Point(0,500));
		new StarBackground(0, 0);
		new Player(200, 300);
		new HealthBar(10, 10);
		new EnemyFour(800, 400, points);
		new EnemyThree(650, 300);
		new EnemyThree(450, 100);
	}

	@Override
	protected void paint(Graphics2D g) {
		List<Entity> list = this.getEntities();
		Iterator<Entity> i = list.iterator();
		while (i.hasNext()) {
			i.next().paint(g);
		}
	}

	@Override
	protected void update() {
		List<Entity> list = this.getEntities();
		Iterator<Entity> i = list.iterator();
		while (i.hasNext()) {
			i.next().update();
		}
		if (Keyboard.isKeyDown(KeyEvent.VK_ESCAPE)) {
			this.setOverlay(new GamePausedScreen(this));
		}
		if (list.get(1) instanceof Player) {
			Player player = (Player) list.get(1);
			if (!player.isAlive()) {
				this.parent.setOverlay(new GameOverScreen(this.parent));
			}
		}
	}

}

