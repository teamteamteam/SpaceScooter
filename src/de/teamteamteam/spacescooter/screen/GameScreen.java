package de.teamteamteam.spacescooter.screen;

import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;

import de.teamteamteam.spacescooter.background.StarBackground;
import de.teamteamteam.spacescooter.control.Keyboard;
import de.teamteamteam.spacescooter.entity.EnemyFour;
import de.teamteamteam.spacescooter.entity.EnemyThree;
import de.teamteamteam.spacescooter.entity.EnemyTwo;
import de.teamteamteam.spacescooter.entity.Entity;
import de.teamteamteam.spacescooter.entity.HealthBar;
import de.teamteamteam.spacescooter.entity.Player;

public class GameScreen extends Screen {

	private ArrayList<Point> points = new ArrayList<Point>();
	
	public GameScreen(Screen parent) {
		super(parent);
		points.add(new Point(300,300));
		points.add(new Point(600,100));
		points.add(new Point(0,500));
		this.entities.add(new StarBackground(0, 0));
		this.entities.add(new Player(200, 300));
		this.entities.add(new HealthBar(10, 10));
		this.entities.add(new EnemyFour(800, 400, points));
		this.entities.add(new EnemyThree(650, 300));
		this.entities.add(new EnemyThree(450, 100));
		this.entities.add(new EnemyTwo(750, 550));
		this.entities.add(new EnemyTwo(150, 250));
	}

	@Override
	protected void paint(Graphics2D g) {
		LinkedList<Entity> list = this.getEntities();
		Iterator<Entity> i = list.iterator();
		while (i.hasNext()) {
			i.next().paint(g);
		}
	}

	@Override
	protected void update() {
		LinkedList<Entity> list = this.getEntities();
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

