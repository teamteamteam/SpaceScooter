package de.teamteamteam.spacescooter.screen;

import java.awt.Graphics;

import java.awt.event.KeyEvent;

import de.teamteamteam.spacescooter.background.StarBackground;
import de.teamteamteam.spacescooter.control.Keyboard;
import de.teamteamteam.spacescooter.entity.Entity;

public class MainMenuScreen extends Screen {

	public MainMenuScreen(Screen parent) {
		super(parent);
		this.addEntity(new StarBackground());
	}

	@Override
	public void paint(Graphics g) {
		for(Entity entity : this.entities) {
			entity.paint(g);
		}
	}

	@Override
	public void update() {
		for(Entity entity : this.entities) {
			entity.update();
		}
		if(Keyboard.isKeyDown(KeyEvent.VK_SPACE)) {
			this.parent.setOverlay(new GameScreen(this.parent));
		}
	}

}
