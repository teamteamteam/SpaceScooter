package de.teamteamteam.spacescooter.screen;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;

import de.teamteamteam.spacescooter.control.Keyboard;
import de.teamteamteam.spacescooter.entity.Player;
import de.teamteamteam.spacescooter.entity.PlayerStatus;
import de.teamteamteam.spacescooter.gui.Button;
import de.teamteamteam.spacescooter.gui.Credits;
import de.teamteamteam.spacescooter.utility.GameConfig;
import de.teamteamteam.spacescooter.utility.Loader;

public class ShopScreen extends Screen {

	private BufferedImage img;
	private float playerMoveSpeed = 0;
	private int menuPoint = 0;
	private boolean keyPressed = false;
	private Player player;
	private int animationStatus = 0; //0 = Animation noch nicht gestartet, 1 = Animation laeuft, 2 = Animation beendet
	
	public ShopScreen(Screen parent) {
		super(parent);
		this.img = Loader.getBufferedImageByFilename("images/testbackground.png");
		new Button(GameConfig.windowWidth/2-125, 250);
		new Button(GameConfig.windowWidth/2-125, 350);
		new Button(GameConfig.windowWidth/2-125, 450);
		player = new Player(GameConfig.windowWidth/2-170, 259);
		player.setCanMove(false);
		player.setCanShoot(false);
	}

	@Override
	protected void paint(Graphics2D g) {
		g.drawImage(this.img, 0, 0, null);
		this.entityPaintIterator.reset();
		while (this.entityPaintIterator.hasNext()) {
			this.entityPaintIterator.next().paint(g);
		}
		g.setFont(new Font("Monospace", 0, 20));
		g.setColor(new Color(255, 255, 255));
		g.drawString("Credits: " + String.valueOf(Credits.getCredits()), GameConfig.windowWidth/2-30, 200);
		g.setColor(new Color(0, 0, 0));
		g.drawString("Schaden", GameConfig.windowWidth/2-30, 282);
		g.drawString("Leben", GameConfig.windowWidth/2-50, 382);
		g.drawString("Hauptmen\u00fc", GameConfig.windowWidth/2-50, 482);
	}

	@Override
	protected void update() {
		if(Keyboard.isKeyDown(KeyEvent.VK_DOWN) && !this.keyPressed && this.animationStatus == 0) {
			this.keyPressed = true;
			if(this.menuPoint<2){
				this.menuPoint++;
				this.player.setPosition(this.player.getX(), 259+(this.menuPoint*100));
			}
		}else if(Keyboard.isKeyDown(KeyEvent.VK_UP) && !this.keyPressed && this.animationStatus == 0) {
			this.keyPressed = true;
			if(this.menuPoint>0) {
				this.menuPoint--;
				this.player.setPosition(this.player.getX(), 259+(this.menuPoint*100));
			}
		}else if(Keyboard.isKeyDown(KeyEvent.VK_ENTER) && !this.keyPressed && this.animationStatus == 0) {
			this.keyPressed = true;
			switch (this.menuPoint) {
			case 0:
				if(Credits.getCredits() >= 5){
					PlayerStatus.ShootDamage += 5;
					Credits.setCredits(Credits.getCredits() - 5);
				}
				break;
			case 1:
				if(Credits.getCredits() >= 10){
					PlayerStatus.HealthPoints += 10;
					Credits.setCredits(Credits.getCredits() - 10);
				}
				break;
			case 2:
				this.animationStatus = 1;
				break;
			}
		}
		if(!Keyboard.isKeyDown(KeyEvent.VK_DOWN) && !Keyboard.isKeyDown(KeyEvent.VK_UP) && !Keyboard.isKeyDown(KeyEvent.VK_ENTER)) {
			this.keyPressed = false;
		}
		
		if(this.animationStatus == 1) {
			if(this.player.getX() <= GameConfig.windowWidth) {
				this.player.setPosition(this.player.getX() + (int) playerMoveSpeed, this.player.getY());
				this.playerMoveSpeed += 0.1;
			} else this.animationStatus = 2;
		} else if(this.animationStatus == 2) {
			this.parent.setOverlay(new MainMenuScreen(this.parent));
		}
	}

}
