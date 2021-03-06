package de.teamteamteam.spacescooter.screen;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.event.KeyEvent;
import de.teamteamteam.spacescooter.brain.GameConfig;
import de.teamteamteam.spacescooter.brain.PlayerSession;
import de.teamteamteam.spacescooter.control.Keyboard;
import de.teamteamteam.spacescooter.gui.Button;
import de.teamteamteam.spacescooter.gui.ImageEntity;

/**
 * This GamePausedScreen shows up when the user pressed VK_ESCAPE ingame.
 * It allows to return back into the game or going back to the MainMenuScreen,
 * discarding the current GameScreen completely.
 */
public class GamePausedScreen extends Screen {

	private ImageEntity cursor;
	private float cursorMoveSpeed = 0;
	private int colorValue = 0;
	private boolean colorValueIncrease = true;
	private int menuPoint = 0;
	private int animationStatus = 0; //0 = Noch nicht gestartet, 1 = Animation läuft, 2 = Animation beendet
	
	public GamePausedScreen(Screen parent) {
		super(parent);
		new ImageEntity(0, 0, "images/pausebackground.png");
		new Button(GameConfig.windowWidth/2-125, 300);
		new Button(GameConfig.windowWidth/2-125, 400);
		this.cursor = new ImageEntity(GameConfig.windowWidth/2-170, 309, "images/ship.png");
	}

	@Override
	protected void paint(Graphics2D g) {
		this.entityPaintIterator.reset();
		while (this.entityPaintIterator.hasNext()) {
			this.entityPaintIterator.next().paint(g);
		}
		g.setFont(new Font("Monospace", 0, 100));
		g.setColor(new Color(75 + colorValue, 175 + colorValue, 175 + colorValue));
		g.drawString("Pause", GameConfig.windowWidth/2-145, 200);
		g.setFont(new Font("Monospace", 0, 20));
		g.setColor(new Color(0, 0, 0));
		g.drawString("Weiter", GameConfig.windowWidth/2-30, 332);
		g.drawString("Hauptmen\u00fc", GameConfig.windowWidth/2-50, 432);
	}

	@Override
	protected void update() {
		this.entityUpdateIterator.reset();
		while (this.entityUpdateIterator.hasNext()) {
			this.entityUpdateIterator.next().update();
		}
		
		if(this.colorValueIncrease) {
			this.colorValue += 2;
			if(this.colorValue > 70) this.colorValueIncrease = false;
		} else {
			this.colorValue -= 2;
			if(this.colorValue < -70) this.colorValueIncrease = true;
		}
				
		if(Keyboard.isKeyDown(KeyEvent.VK_DOWN) && this.animationStatus == 0) {
			this.menuPoint = 1;
			cursor.setPosition(cursor.getX(), 409);
		}
		if(Keyboard.isKeyDown(KeyEvent.VK_UP) && this.animationStatus == 0) {
			this.menuPoint = 0;
			cursor.setPosition(cursor.getX(), 309);
		}
		
		// make a selection
		if(Keyboard.isKeyDown(KeyEvent.VK_ENTER) || Keyboard.isKeyDown(KeyEvent.VK_SPACE)) {
			animationStatus = 1;
		}
		
		if(this.animationStatus == 1) {
			if(cursor.getX() <= GameConfig.windowWidth) {
				cursor.setPosition(cursor.getX() + (int)cursorMoveSpeed, cursor.getY());
				cursorMoveSpeed += 0.1;
			} else this.animationStatus = 2;
		} else if(this.animationStatus == 2) {
			switch (this.menuPoint) {
			case 0:
				//Removes itself from the GameScreen, so the player can continue playing.
				this.parent.setOverlay(null, false);
				break;
			case 1:
				//Clear the PlayerSession
				PlayerSession.reset();
				//Replaces its parents (the GameScreen) parent (the SuperScreen) overlay.
				this.parent.parent.setOverlay(new MainMenuScreen(this.parent.parent));
				break;
			}
		}
	}

}
