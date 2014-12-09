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
import de.teamteamteam.spacescooter.utility.Highscore;

public class HighscoreScreen extends Screen{

	private ImageEntity cursor;
	private float cursorMoveSpeed = 0;
	private int animationStatus = 0; //0 = Animation noch nicht gestartet, 1 = Animation laeuft, 2 = Animation beendet
	private int[] points;
	private String[] names;
	
	public HighscoreScreen(Screen parent) {
		super(parent);
		new ImageEntity(0, 0, "images/shopbackground.png");
		new Button(GameConfig.windowWidth/2-125, GameConfig.windowHeight-75);
		this.cursor = new ImageEntity(GameConfig.windowWidth/2-170, GameConfig.windowHeight-63, "images/ship.png");
		this.points = Highscore.getPoints();
		this.names = Highscore.getNames();
	}

	@Override
	protected void paint(Graphics2D g) {
		this.entityPaintIterator.reset();
		while (this.entityPaintIterator.hasNext()) {
			this.entityPaintIterator.next().paint(g);
		}
		g.setFont(new Font("Monospace", 0, 20));
		g.setColor(new Color(255, 255, 255));
		for(int i = 0; i<this.points.length; i++){
			g.drawString(String.valueOf(i+1), 50, 27*(i+1));
			g.drawString(this.points[i] + "", 110, 27*(i+1));
			g.drawString(this.names[i], 260, 27*(i+1));
		}
		g.setColor(new Color(0, 0, 0));
		g.drawString("Hauptmen\u00fc", GameConfig.windowWidth/2-55, GameConfig.windowHeight-43);
	}

	@Override
	protected void update() {
		if ((Keyboard.isKeyDown(KeyEvent.VK_ENTER) || Keyboard.isKeyDown(KeyEvent.VK_SPACE)) && this.animationStatus == 0) {
			this.animationStatus = 1;
		}
		if(this.animationStatus == 1) {
			if(this.cursor.getX() <= GameConfig.windowWidth) {
				this.cursor.setPosition(this.cursor.getX() + (int) cursorMoveSpeed, this.cursor.getY());
				this.cursorMoveSpeed += 0.1;
			} else this.animationStatus = 2;
		} else if(this.animationStatus == 2) {
			PlayerSession.reset(); //The player now entered his highscore, reset the PlayerSession now.
			this.parent.setOverlay(new MainMenuScreen(this.parent));
		}
	}
}
