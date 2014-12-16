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
import de.teamteamteam.spacescooter.gui.ShopOffer;

public class ShopScreen extends Screen {

	private ImageEntity cursor;
	private float cursorMoveSpeed = 0;
	private int menuPoint = 0;
	private boolean keyPressed = false;
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
		new ImageEntity(0, 0, "images/shopbackground.png");
		new Button(GameConfig.windowWidth/2-125, 500);
		damage = new ShopOffer(90, 150, PlayerSession.getBaseShotUpgradesBought(), "Schaden", 5);
		shield = new ShopOffer(90, 225, PlayerSession.getBaseShieldUpgradesBought(), "Schild", 10);
		life = new ShopOffer(90, 300, PlayerSession.getBaseHealthUpgradesBought(), "Leben", 10);
		new ImageEntity(GameConfig.windowWidth / 2 - 120, 365, "images/shop/shoprocket.png");
		new ImageEntity(GameConfig.windowWidth / 2 + 30, 365, "images/shop/shopbeam.png");
		if(PlayerSession.getSecondaryWeapon() == 1){
			select = new ImageEntity(GameConfig.windowWidth / 2 - 130, 355, "images/shop/select.png");
		}else{
			select = new ImageEntity(GameConfig.windowWidth / 2 + 20, 355, "images/shop/select.png");
		}
		this.cursor = new ImageEntity(40, 149, "images/ship.png");
	}

	@Override
	protected void paint(Graphics2D g) {
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
					this.cursor.setPosition(GameConfig.windowWidth/2-180, 390);
				}else if(menuPoint == 4){
					this.cursor.setPosition(GameConfig.windowWidth/2-170, 508);
				}else{
					this.cursor.setPosition(this.cursor.getX(), 149+(this.menuPoint*75));
				}
			}
		}else if(Keyboard.isKeyDown(KeyEvent.VK_UP) && !this.keyPressed && this.animationStatus == 0) {
			this.keyPressed = true;
			if(this.menuPoint>0) {
				this.menuPoint--;
				if(menuPoint == 3){
					this.cursor.setPosition(GameConfig.windowWidth/2-180, 390);
				}else{
					this.cursor.setPosition(40, 149+(this.menuPoint*75));
				}
			}
			
			/**
			 * Selection.
			 */
		}else if((Keyboard.isKeyDown(KeyEvent.VK_SPACE) || Keyboard.isKeyDown(KeyEvent.VK_ENTER)) && !this.keyPressed && this.animationStatus == 0) {
			this.keyPressed = true;
			switch (this.menuPoint) {
			case 0:
				if(PlayerSession.getCredits() >= damage.getCurrentPrice() && damage.getBought() < damage.getMax()){
					PlayerSession.addBaseShotDamage(2);
					PlayerSession.incrementBaseShotUpgradesBought();
					PlayerSession.removeCredits(damage.getCurrentPrice());
					damage.buy();
				}
				break;
			case 1:
				if(PlayerSession.getCredits() >= shield.getCurrentPrice() && shield.getBought() < shield.getMax()){
					PlayerSession.addBaseShieldPoints(10);
					PlayerSession.incrementBaseShieldUpgradesBought();
					PlayerSession.removeCredits(shield.getCurrentPrice());
					shield.buy();
				}
				break;
			case 2:
				if(PlayerSession.getCredits() >= life.getCurrentPrice() && life.getBought() < life.getMax()){
					PlayerSession.addBaseHealthPoints(10);
					PlayerSession.incrementBaseHealthUpgradesBought();
					PlayerSession.removeCredits(life.getCurrentPrice());
					life.buy();
				}
				break;
			case 3:
				if(PlayerSession.getSecondaryWeapon() == 1){
					select.setPosition(GameConfig.windowWidth / 2 + 20, 355);
					PlayerSession.setSecondaryWeapon(2);
				}else{
					select.setPosition(GameConfig.windowWidth / 2 - 130, 355);
					PlayerSession.setSecondaryWeapon(1);
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
			if(this.cursor.getX() <= GameConfig.windowWidth) {
				this.cursor.setPosition(this.cursor.getX() + (int) cursorMoveSpeed, this.cursor.getY());
				this.cursorMoveSpeed += 0.1;
			} else this.animationStatus = 2;
		} else if(this.animationStatus == 2) {
			this.parent.setOverlay(new GameScreen(this.parent));
		}
	}

}
