package de.teamteamteam.spacescooter.screen;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;

import de.teamteamteam.spacescooter.brain.GameConfig;
import de.teamteamteam.spacescooter.brain.PlayerSession;
import de.teamteamteam.spacescooter.control.Keyboard;
import de.teamteamteam.spacescooter.entity.Player;
import de.teamteamteam.spacescooter.gui.Button;
import de.teamteamteam.spacescooter.utility.Loader;

/**
 * This is the GameOverScreen, which is displayed once the player
 * died and the game is over.
 * It allows to start a new game or going to the MainMenuScreen.
 */
public class GameOverScreen extends Screen {

	private BufferedImage img;
	private Player player;
	private float playerMoveSpeed = 0;
	private int colorValue = 0;
	private boolean colorValueIncrease = true;
	private int menuPoint = 0;
	private int animationStatus = 0; //0 = Noch nicht gestartet, 1 = Animation läuft, 2 = Animation beendet
	
	public GameOverScreen(Screen parent) {
		super(parent);
		this.img = Loader.getBufferedImageByFilename("images/pausebackground.png");
		new Button(GameConfig.windowWidth/2-125, 300);
		new Button(GameConfig.windowWidth/2-125, 400);
		player = new Player(GameConfig.windowWidth/2-170, 309);
		player.setCanMove(false);
		player.setCanShoot(false);
		
		//Reset the player session for the next player.
		//TODO: Make sure highscore "enter name" stuff happened before!
		PlayerSession.reset();
	}

	@Override
	protected void paint(Graphics2D g) {
		g.drawImage(this.img, 0, 0, null);
		this.entityPaintIterator.reset();
		while (this.entityPaintIterator.hasNext()) {
			this.entityPaintIterator.next().paint(g);
		}
		g.setFont(new Font("Monospace", 0, 100));
		g.setColor(new Color(75 + colorValue, 175 + colorValue, 175 + colorValue));
		g.drawString("Game Over", GameConfig.windowWidth/2-290, 200);
		g.setFont(new Font("Monospace", 0, 20));
		g.setColor(new Color(0, 0, 0));
		g.drawString("Wiederholen", GameConfig.windowWidth/2-60, 332);
		g.drawString("Hauptmen\u00fc", GameConfig.windowWidth/2-60, 432);
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
				
		if(Keyboard.isKeyDown(KeyEvent.VK_DOWN) && this.animationStatus == 0){
			this.menuPoint = 1;
			player.setPosition(player.getX(), 409);
		}
		if(Keyboard.isKeyDown(KeyEvent.VK_UP) && this.animationStatus == 0){
			this.menuPoint = 0;
			player.setPosition(player.getX(), 309);
		}
		
		if(Keyboard.isKeyDown(KeyEvent.VK_ENTER)) {
			this.animationStatus = 1;
		}
		if(this.animationStatus == 1) {
			if(player.getX() <= GameConfig.windowWidth) {
				player.setPosition(player.getX() + (int) playerMoveSpeed, player.getY());
				playerMoveSpeed += 0.1;
			} else this.animationStatus = 2;
		} else if(this.animationStatus == 2) {
			switch (this.menuPoint) {
			case 0:
				this.parent.setOverlay(new GameScreen(this.parent, "levels/test.level"));
				break;
			case 1:
				this.parent.setOverlay(new MainMenuScreen(this.parent));
				break;
			}
		}
	}

}
