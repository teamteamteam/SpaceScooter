package de.teamteamteam.spacescooter.screen;

import java.awt.Graphics;

import java.awt.event.KeyEvent;
import java.util.Iterator;
import java.util.LinkedList;

import de.teamteamteam.spacescooter.background.StarBackground;
import de.teamteamteam.spacescooter.control.Keyboard;
import de.teamteamteam.spacescooter.entity.Entity;
import de.teamteamteam.spacescooter.entity.Player;

public class GameScreen extends Screen {

	public GameScreen(Screen parent) {
		super(parent);
		this.entities.add(new StarBackground(0, 0));
		this.entities.add(new Player(200, 300));
	}

	@Override
	protected void paint(Graphics g) {
		LinkedList<Entity> list = new LinkedList<Entity>(this.entities);
		Iterator<Entity> i = list.iterator();
		while (i.hasNext()) {
			i.next().paint(g);
		}
	}

	@Override
	protected void update() {
		LinkedList<Entity> list = new LinkedList<Entity>(this.entities);
		Iterator<Entity> i = list.iterator();
		while (i.hasNext()) {
			i.next().update();
		}
		if (Keyboard.isKeyDown(KeyEvent.VK_ESCAPE)) {
			this.parent.setOverlay(new MainMenuScreen(this.parent));
		}
	}

}
