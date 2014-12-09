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
import de.teamteamteam.spacescooter.gui.ImageEntity;
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
	private ImageEntity select;
	
	/**
	 * Create the shop screen.
	 */
	public ShopScreen(Screen parent) {
		super(parent);
		this.img = Loader.getBufferedImageByFilename("images/shopbackground.png");
		new Button(GameConfig.windowWidth/2-125, 500);
		damage = new ShopOffer(100, 150, 15, PlayerSession.getBaseShotUpgradesBought(), "Schaden  5C");
		shield = new ShopOffer(100, 225, 15, PlayerSession.getBaseShieldUpgradesBought(), "Schild     10C");
		life = new ShopOffer(100, 300, 15, PlayerSession.getBaseHealthUpgradesBought(), "Leben     10C");
		new ImageEntity(GameConfig.windowWidth / 2 - 120, 365, "images/shop/shoprocket.png");
		new ImageEntity(GameConfig.windowWidth / 2 + 30, 365, "images/shop/shopbeam.png");
		if(PlayerSession.getSecondsecondaryWeapon() == 1){
			System.out.println("1");
			select = new ImageEntity(GameConfig.windowWidth / 2 - 130, 355, "images/shop/select.png");
		}else{
			System.out.println("2");
			select = new ImageEntity(GameConfig.windowWidth / 2 + 20, 355, "images/shop/select.png");
		}
		player = new Player(50, 149);
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
		g.drawString("Credits: " + String.valueOf(PlayerSession.getCredits()), GameConfig.windowWidth/2-45, 100);
		g.drawString("Rocket", GameConfig.windowWidth / 2 - 110, 390);
		g.drawString("Beam", GameConfig.windowWidth / 2 + 45, 390);
		g.setColor(new Color(0, 0, 0));
		g.drawString("Weiter", GameConfig.windowWidth/2-55, 533);
	}

	@Override
	protected void update() {
		
		/**
		 * Control in the menu.
		 */
		if(Keyboard.isKeyDown(KeyEvent.VK_DOWN) && !this.keyPressed && this.animationStatus == 0) {
			this.keyPressed = true;
			if(this.menuPoint<4){
				this.menuPoint++;
				if(menuPoint == 3){
					this.player.setPosition(GameConfig.windowWidth/2-180, 390);
				}else if(menuPoint == 4){
					this.player.setPosition(GameConfig.windowWidth/2-170, 508);
				}else{
					this.player.setPosition(this.player.getX(), 149+(this.menuPoint*75));
				}
			}
		}else if(Keyboard.isKeyDown(KeyEvent.VK_UP) && !this.keyPressed && this.animationStatus == 0) {
			this.keyPressed = true;
			if(this.menuPoint>0) {
				this.menuPoint--;
				if(menuPoint == 3){
					this.player.setPosition(GameConfig.windowWidth/2-180, 390);
				}else{
					this.player.setPosition(50, 149+(this.menuPoint*75));
				}
			}
			
			/**
			 * Selection.
			 */
		}else if ( (Keyboard.isKeyDown(KeyEvent.VK_SPACE) || Keyboard.isKeyDown(KeyEvent.VK_ENTER)) && !this.keyPressed && this.animationStatus == 0) {
			this.keyPressed = true;
			switch (this.menuPoint) {
			case 0:
				if(PlayerSession.getCredits() >= 5 && damage.getBought() < damage.getMax()){
					damage.buy();
					PlayerSession.addBaseShotDamage(5);
					PlayerSession.incrementBaseShotUpgradesBought();
					PlayerSession.removeCredits(5);
				}
				break;
			case 1:
				if(PlayerSession.getCredits() >= 10 && shield.getBought() < shield.getMax()){
					shield.buy();
					PlayerSession.addBaseShieldPoints(10);
					PlayerSession.incrementBaseShieldUpgradesBought();
					PlayerSession.removeCredits(10);
				}
				break;
			case 2:
				if(PlayerSession.getCredits() >= 10 && life.getBought() < life.getMax()){
					life.buy();
					PlayerSession.addBaseHealthPoints(10);
					PlayerSession.incrementBaseHealthUpgradesBought();
					PlayerSession.removeCredits(10);
				}
				break;
			case 3:
				if(PlayerSession.getSecondsecondaryWeapon() == 1){
					select.setPosition(GameConfig.windowWidth / 2 + 20, 355);
					PlayerSession.setSecondsecondaryWeapon(2);
				}else{
					select.setPosition(GameConfig.windowWidth / 2 - 130, 355);
					PlayerSession.setSecondsecondaryWeapon(1);
				}
				break;
			case 4:
				this.animationStatus = 1;
				break;
			}
		}
		if(!Keyboard.isKeyDown(KeyEvent.VK_DOWN) && !Keyboard.isKeyDown(KeyEvent.VK_UP) && !Keyboard.isKeyDown(KeyEvent.VK_ENTER) && !Keyboard.isKeyDown(KeyEvent.VK_SPACE)) {
			this.keyPressed = false;
		}
		
		/**
		 * Animation.
		 */
		if(this.animationStatus == 1) {
			if(this.player.getX() <= GameConfig.windowWidth) {
				this.player.setPosition(this.player.getX() + (int) playerMoveSpeed, this.player.getY());
				this.playerMoveSpeed += 0.1;
			} else this.animationStatus = 2;
		} else if(this.animationStatus == 2) {
			this.parent.setOverlay(new GameScreen(this.parent));
		}
	}

}
