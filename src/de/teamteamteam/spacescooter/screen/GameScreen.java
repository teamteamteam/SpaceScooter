package de.teamteamteam.spacescooter.screen;

import java.awt.Graphics;

import java.awt.event.KeyEvent;
import java.util.Iterator;
import java.util.LinkedList;

import de.teamteamteam.spacescooter.background.StarBackground;
import de.teamteamteam.spacescooter.control.Keyboard;
import de.teamteamteam.spacescooter.entity.EnemyOne;
import de.teamteamteam.spacescooter.entity.Entity;
import de.teamteamteam.spacescooter.entity.Player;

public class GameScreen extends Screen {

	public GameScreen(Screen parent) {
		super(parent);
		this.entities.add(new StarBackground(0, 0));
		this.entities.add(new Player(200, 300));
		this.entities.add(new EnemyOne(650, 300));
		this.entities.add(new EnemyOne(450, 100));
		this.entities.add(new EnemyOne(750, 550));
		this.entities.add(new EnemyOne(150, 250));
	}

	@Override
	protected void paint(Graphics g) {
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
	}

}
