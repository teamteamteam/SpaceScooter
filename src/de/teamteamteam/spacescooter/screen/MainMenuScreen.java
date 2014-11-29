package de.teamteamteam.spacescooter.screen;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.event.KeyEvent;

import de.teamteamteam.spacescooter.background.StarBackground;
import de.teamteamteam.spacescooter.brain.GameConfig;
import de.teamteamteam.spacescooter.control.Keyboard;
import de.teamteamteam.spacescooter.entity.Player;
import de.teamteamteam.spacescooter.gui.Button;
import de.teamteamteam.spacescooter.sound.SoundSystem;

/**
 * This Screen show the games main menu.
 */
public class MainMenuScreen extends Screen {

	private Player player;
	private float playerMoveSpeed = 0;
	private int colorValue = 0;
	private boolean colorValueIncrease = true;
	private int menuPoint = 0;
	private boolean keyPressed = false;
	private int animationStatus = 0; //0 = Animation noch nicht gestartet, 1 = Animation laeuft, 2 = Animation beendet
	
	public MainMenuScreen(Screen parent) {
		super(parent);
		new StarBackground(0, 0);
		new Button(GameConfig.windowWidth/2-125, 200);
		new Button(GameConfig.windowWidth/2-125, 275);
		new Button(GameConfig.windowWidth/2-125, 350);
		new Button(GameConfig.windowWidth/2-125, 425);
		new Button(GameConfig.windowWidth/2-125, 500);
		player = new Player(GameConfig.windowWidth/2-170, 209);
		player.setCanMove(false);
		player.setCanShoot(false);
		SoundSystem.playSound("music/ScooterFriendsTurbo8Bit.wav");
	}

	@Override
	public void paint(Graphics2D g) {
		this.entityPaintIterator.reset();
		while (this.entityPaintIterator.hasNext()) {
			this.entityPaintIterator.next().paint(g);
		}
		g.setFont(new Font("Monospace", 0, 70));
		g.setColor(new Color(75 + colorValue, 175 + colorValue, 175 + colorValue));
		g.drawString("SpaceScooter", GameConfig.windowWidth/2-225, 125);
		g.setFont(new Font("Monospace", 0, 20));
		g.setColor(new Color(0, 0, 0));
		g.drawString("Neues Spiel", GameConfig.windowWidth/2-50, 232);
		g.drawString("Hilfe", GameConfig.windowWidth/2-20, 308);
		g.drawString("Highscore", GameConfig.windowWidth/2-45, 382);
		g.drawString("Credits", GameConfig.windowWidth/2-33, 458);
		g.drawString("Beenden", GameConfig.windowWidth/2-38, 532);
	}

	@Override
	public void update() {
		this.entityUpdateIterator.reset();
		while (this.entityUpdateIterator.hasNext()) {
			this.entityUpdateIterator.next().update();
		}
		
		if(colorValueIncrease) {
			colorValue += 2; 
			if(colorValue > 70) colorValueIncrease = false;
		} else {
			colorValue -= 2;
			if(colorValue < -70) colorValueIncrease = true;
		}
				
		if(Keyboard.isKeyDown(KeyEvent.VK_DOWN) && !keyPressed && animationStatus == 0) {
			keyPressed = true;
			if(menuPoint<4){
				menuPoint++;
				player.setPosition(player.getX(), 209+(menuPoint*75));
			}
		} else if(Keyboard.isKeyDown(KeyEvent.VK_UP) && !keyPressed && animationStatus == 0) {
			keyPressed = true;
			if(menuPoint>0) {
				menuPoint--;
				player.setPosition(player.getX(), 209+(menuPoint*75));
			}
		} else if(!Keyboard.isKeyDown(KeyEvent.VK_DOWN) && !Keyboard.isKeyDown(KeyEvent.VK_UP)) {
			keyPressed = false;
		}
		
		// make a selection
		if(Keyboard.isKeyDown(KeyEvent.VK_ENTER) || Keyboard.isKeyDown(KeyEvent.VK_SPACE)) {
			animationStatus = 1;
		}
		
		if(animationStatus == 1) {
			if(player.getX() <= GameConfig.windowWidth) {
				player.setPosition(player.getX() + (int) playerMoveSpeed, player.getY());
				playerMoveSpeed += 0.1;
			} else animationStatus = 2;
		} else if(animationStatus == 2) {
			switch (menuPoint) {
			case 0:
				this.parent.setOverlay(new GameScreen(this.parent, "levels/test.level"));
				break;
			case 1:
				this.parent.setOverlay(new ShopScreen(this.parent));
				break;
			case 2:
				break;
			case 3:
				break;
			case 4:
				System.exit(0);
				break;
			}
		}
	}

}
