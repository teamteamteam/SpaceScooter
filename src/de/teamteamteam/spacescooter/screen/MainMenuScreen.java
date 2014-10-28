package de.teamteamteam.spacescooter.screen;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;

import java.awt.event.KeyEvent;

import de.teamteamteam.spacescooter.control.Keyboard;
import de.teamteamteam.spacescooter.utility.GameConfig;

public class MainMenuScreen extends Screen {

	public MainMenuScreen(Screen parent) {
		super(parent);
	}

	@Override
	public void paint(Graphics2D g) {
		g.setColor(new Color(0,0,120));
		g.fillRect(0, 0, GameConfig.windowWidth, GameConfig.windowHeight);
		g.setColor(new Color(255,255,255));
		g.setFont(new Font("Monospace", 0, 50));
		g.drawString("Main Menu. LOL", 100, 100);
		g.drawString("Press space to play!", 100, 400);
	}

	@Override
	public void update() {
		/*for(Entity entity : this.entities) {
			entity.update();
		}*/
		if(Keyboard.isKeyDown(KeyEvent.VK_SPACE)) {
			this.parent.setOverlay(new GameScreen(this.parent));
		}
		if(Keyboard.isKeyDown(KeyEvent.VK_ESCAPE)) {
			System.exit(0);
		}
	}

}
