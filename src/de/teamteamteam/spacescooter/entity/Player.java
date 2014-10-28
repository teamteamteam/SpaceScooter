package de.teamteamteam.spacescooter.entity;

import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.io.IOException;
import javax.imageio.ImageIO;

import de.teamteamteam.spacescooter.control.Keyboard;
import de.teamteamteam.spacescooter.utility.GameConfig;

public class Player extends ShootingEntity {

	private static BufferedImage img;
	
	static {
		try {
			img = ImageIO.read(Player.class.getClassLoader().getResourceAsStream("images/ship.png"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public Player(int x, int y) {
		super(x, y);
		this.setImage(Player.img);
		this.setShootDelay(40);
		this.setShootSpawn(50, 16);
		this.setShootDirection(Shot.RIGHT);
		this.setShootSpeed(2);
	}

	public void update() {
		super.update();
		int off = 3;
		if(Keyboard.isKeyDown(KeyEvent.VK_UP) && this.y > 0) {
			this.y -= off;
		}
		if(Keyboard.isKeyDown(KeyEvent.VK_DOWN) && this.y < (GameConfig.windowHeight - Player.img.getHeight())) {
			this.y += off;
		}
		if(Keyboard.isKeyDown(KeyEvent.VK_LEFT) && this.x > 0) {
			this.x -= off;
		}
		if(Keyboard.isKeyDown(KeyEvent.VK_RIGHT) && this.x < (GameConfig.windowWidth - Player.img.getWidth())) {
			this.x += off;
		}
		if(Keyboard.isKeyDown(KeyEvent.VK_SPACE)) {
			this.shoot();
		}
	}

}
