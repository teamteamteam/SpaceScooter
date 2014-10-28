package de.teamteamteam.spacescooter.screen;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.event.KeyEvent;

import de.teamteamteam.spacescooter.control.Keyboard;
import de.teamteamteam.spacescooter.utility.GameConfig;

public class GamePausedScreen extends Screen {

	public GamePausedScreen(Screen parent) {
		super(parent);
	}

	@Override
	protected void paint(Graphics2D g) {
		g.setColor(new Color(0,0,120));
		g.fillRect(0, 0, GameConfig.windowWidth, GameConfig.windowHeight);
		g.setColor(new Color(255,255,255));
		g.setFont(new Font("Monospace", 0, 50));
		g.drawString("Pause Menu. LOL", 100, 100);
		g.drawString("Press space to continue!", 100, 400);
	}

	@Override
	protected void update() {
		if(Keyboard.isKeyDown(KeyEvent.VK_SPACE)) {
			this.parent.setOverlay(null);
		}
	}

}
