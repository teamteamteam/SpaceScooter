package de.teamteamteam.spacescooter.screen;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;

import de.teamteamteam.spacescooter.brain.GameConfig;
import de.teamteamteam.spacescooter.brain.PlayerSession;
import de.teamteamteam.spacescooter.brain.StaticValue;
import de.teamteamteam.spacescooter.control.Keyboard;
import de.teamteamteam.spacescooter.entity.Player;
import de.teamteamteam.spacescooter.gui.Button;
import de.teamteamteam.spacescooter.gui.ShopOffer;
import de.teamteamteam.spacescooter.utility.Loader;

public class ShopScreen extends Screen {

	private BufferedImage img;
	private float playerMoveSpeed = 0;
	private int menuPoint = 0;
	private boolean keyPressed = false;
	private Player player;
	private int animationStatus = 0; //0 = Animation noch nicht gestartet, 1 = Animation laeuft, 2 = Animation beendet
	private ShopOffer damage;
	private ShopOffer shield;
	private ShopOffer life;
	
	public ShopScreen(Screen parent) {
		super(parent);
		this.img = Loader.getBufferedImageByFilename("images/testbackground.png");
		new Button(GameConfig.windowWidth/2-125, 450);
		damage = new ShopOffer(100, 160, 15, StaticValue.damage, "Schaden");
		shield = new ShopOffer(100, 260, 15, StaticValue.shield, "Schild");
		life = new ShopOffer(100, 360, 15, StaticValue.life, "Leben");
		player = new Player(50, 159);
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
		g.drawString("Credits: " + String.valueOf(PlayerSession.getCredits()), GameConfig.windowWidth/2-30, 100);
		g.setColor(new Color(0, 0, 0));
		g.drawString("Hauptmen\u00fc", GameConfig.windowWidth/2-55, 482);
	}

	@Override
	protected void update() {
		if(Keyboard.isKeyDown(KeyEvent.VK_DOWN) && !this.keyPressed && this.animationStatus == 0) {
			this.keyPressed = true;
			if(this.menuPoint<3){
				this.menuPoint++;
				if(menuPoint == 3){
					this.player.setPosition(GameConfig.windowWidth/2-170, this.player.getY());
				}
				this.player.setPosition(this.player.getX(), 159+(this.menuPoint*100));
			}
		}else if(Keyboard.isKeyDown(KeyEvent.VK_UP) && !this.keyPressed && this.animationStatus == 0) {
			this.keyPressed = true;
			if(this.menuPoint>0) {
				this.menuPoint--;
				this.player.setPosition(50, this.player.getY());
				this.player.setPosition(this.player.getX(), 159+(this.menuPoint*100));
			}
		}else if(Keyboard.isKeyDown(KeyEvent.VK_ENTER) && !this.keyPressed && this.animationStatus == 0) {
			this.keyPressed = true;
			///////////////////////////////////////////////////////////////
			switch (this.menuPoint) {
			case 0:
				if(PlayerSession.getCredits() >= 5 && damage.getBought() < damage.getMax()){
					damage.buy();
					StaticValue.shotDamage += 5;
					StaticValue.damage++;
					PlayerSession.removeCredits(5);
				}
				break;
			case 1:
				if(PlayerSession.getCredits() >= 10 && shield.getBought() < shield.getMax()){
					shield.buy();
					StaticValue.shieldPoints += 10;
					StaticValue.shield++;
					PlayerSession.removeCredits(10);
				}
				break;
			case 2:
				if(PlayerSession.getCredits() >= 10 && life.getBought() < life.getMax()){
					life.buy();
					StaticValue.healthPoints += 10;
					StaticValue.life++;
					PlayerSession.removeCredits(10);
				}
				break;
			case 3:
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
