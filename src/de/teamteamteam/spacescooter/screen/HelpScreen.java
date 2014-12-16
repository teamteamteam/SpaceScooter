package de.teamteamteam.spacescooter.screen;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.event.KeyEvent;

import de.teamteamteam.spacescooter.brain.GameConfig;
import de.teamteamteam.spacescooter.control.Keyboard;

/**
 * A Screen providing all the cool instructions about how to play.
 */
public class HelpScreen extends Screen {
	
	/**
	 * Default Constructor
	 */
	public HelpScreen(Screen parent) {
		super(parent);
	}
	
	/**
	 * Draw the Credits :)
	 */
	@Override
	protected void paint(Graphics2D g) {
		g.setColor(new Color(0,0,120));
		g.fillRect(0, 0, GameConfig.windowWidth, GameConfig.windowHeight);
		g.setColor(Color.WHITE);
		g.setFont(new Font("Monospace", 0, 50));
		String text = "Hilfe kommt";
		g.drawString(text, (GameConfig.windowWidth - g.getFontMetrics().stringWidth(text))/2, 150);
	}

	/**
	 * In case the Loader is done, immediately fire up the MainMenuScreen.
	 */
	@Override
	protected void update() {
		if(Keyboard.isKeyDown(KeyEvent.VK_ENTER) || Keyboard.isKeyDown(KeyEvent.VK_SPACE)) {
			this.parent.setOverlay(new MainMenuScreen(this.parent));
		}
 	}

}
