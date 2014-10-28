package de.teamteamteam.spacescooter.screen;

import java.awt.Graphics;

import java.awt.event.KeyEvent;

import de.teamteamteam.spacescooter.background.StarBackground;
import de.teamteamteam.spacescooter.control.Keyboard;
import de.teamteamteam.spacescooter.entity.Entity;
import de.teamteamteam.spacescooter.entity.Player;
import de.teamteamteam.spacescooter.entity.TestEntity;

public class GameScreen extends Screen {

	public GameScreen(Screen parent) {
		super(parent);
		this.entities.add(new StarBackground());
		this.entities.add(new Player());
		this.entities.add(new TestEntity());
	}

	@Override
	protected void paint(Graphics g) {
		for(Entity e : this.entities) {
			e.paint(g);
		}
	}

	@Override
	protected void update() {
		for(Entity e : this.entities) {
			e.update();
		}
		if(Keyboard.isKeyDown(KeyEvent.VK_ESCAPE)) {
			this.parent.setOverlay(new MainMenuScreen(this.parent));
		}
	}

}
