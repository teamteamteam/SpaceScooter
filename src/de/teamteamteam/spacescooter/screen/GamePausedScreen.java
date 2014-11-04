package de.teamteamteam.spacescooter.screen;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.util.Iterator;
import java.util.LinkedList;

import de.teamteamteam.spacescooter.control.Keyboard;
import de.teamteamteam.spacescooter.entity.Entity;
import de.teamteamteam.spacescooter.entity.Player;
import de.teamteamteam.spacescooter.gui.Button;
import de.teamteamteam.spacescooter.utility.GameConfig;
import de.teamteamteam.spacescooter.utility.Loader;

public class GamePausedScreen extends Screen {

	private BufferedImage img;
	private Player player;
	private float playerMoveSpeed = 0;
	private int colorValue = 0;
	private boolean colorValueIncrease = true;
	private int menuPoint = 0;
	private int animationStatus = 0; //0 = Noch nicht gestartet, 1 = Animation läuft, 2 = Animation beendet
	
	public GamePausedScreen(Screen parent) {
		super(parent);
		this.img = Loader.getBufferedImageByFilename("images/pausebackground.png");
		new Button(GameConfig.windowWidth/2-125, 300);
		new Button(GameConfig.windowWidth/2-125, 400);
		player = new Player(GameConfig.windowWidth/2-170, 309);
		player.setCanMove(false);
		player.setCanShoot(false);
	}

	@Override
	protected void paint(Graphics2D g) {
		g.drawImage(this.img, 0, 0, null);
		LinkedList<Entity> list = this.getEntities();
		Iterator<Entity> i = list.iterator();
		while (i.hasNext()) {
			i.next().paint(g);
		}
		g.setFont(new Font("Monospace", 0, 100));
		g.setColor(new Color(75 + colorValue, 175 + colorValue, 175 + colorValue));
		g.drawString("Pause", GameConfig.windowWidth/2-145, 200);
		g.setFont(new Font("Monospace", 0, 20));
		g.setColor(new Color(0, 0, 0));
		g.drawString("Weiter", GameConfig.windowWidth/2-30, 332);
		g.drawString("Hauptmenü", GameConfig.windowWidth/2-50, 432);
	}

	@Override
	protected void update() {
		LinkedList<Entity> list = this.getEntities();
		Iterator<Entity> i = list.iterator();
		while (i.hasNext()) {
			i.next().update();
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
			player.setPosition(player.getX(), 409);
		}
		if(Keyboard.isKeyDown(KeyEvent.VK_UP) && this.animationStatus == 0) {
			this.menuPoint = 0;
			player.setPosition(player.getX(), 309);
		}
		
		if(Keyboard.isKeyDown(KeyEvent.VK_ENTER)) {
			this.animationStatus = 1;
		}
		if(this.animationStatus == 1) {
			if(player.getX() <= GameConfig.windowWidth) {
				player.setPosition(player.getX() + (int)playerMoveSpeed, player.getY());
				playerMoveSpeed += 0.1;
			} else this.animationStatus = 2;
		} else if(this.animationStatus == 2) {
			switch (this.menuPoint) {
			case 0:
				this.parent.setOverlay(null);
				break;
			case 1:
				this.parent.setOverlay(new MainMenuScreen(this.parent));
				break;
			}
		}
	}

}
