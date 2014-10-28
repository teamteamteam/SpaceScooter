package de.teamteamteam.spacescooter.screen;

import java.awt.Color;
import java.awt.Graphics;

import java.awt.event.KeyEvent;

import de.teamteamteam.spacescooter.control.Keyboard;
import de.teamteamteam.spacescooter.utility.GameConfig;

public class GameScreen extends Screen {

	public GameScreen(Screen parent) {
		super(parent);
	}

	@Override
	protected void paint(Graphics g) {
		g.fillRect(0, 0, GameConfig.windowWidth, GameConfig.windowHeight);
		g.setColor(new Color(255,0,255));
		g.drawRect(100, 200, 150, 75);
	}

	@Override
	protected void update() {
		if(Keyboard.isKeyDown(KeyEvent.VK_ESCAPE)) {
			this.parent.setOverlay(new MainMenuScreen(this.parent));
		}
	}

}
