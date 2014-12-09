package de.teamteamteam.spacescooter.screen;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.event.KeyEvent;

import de.teamteamteam.spacescooter.brain.GameConfig;
import de.teamteamteam.spacescooter.control.Keyboard;
import de.teamteamteam.spacescooter.entity.enemy.EnemyOne;

/**
 * This is the Screen where you can look at all those awesome guys who created this game. :D
 */
public class CreditsScreen extends Screen {
	
	/**
	 * Default Constructor
	 */
	public CreditsScreen(Screen parent) {
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
		String text = "#yolo";
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
