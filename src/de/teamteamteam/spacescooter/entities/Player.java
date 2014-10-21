package de.teamteamteam.spacescooter.entities;

import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

import de.teamteamteam.spacescooter.controls.Keyboard;

public class Player extends Entity {

	private int x;
	private int y;
	
	private static BufferedImage img;
	
	static {
		try {
			img = ImageIO.read(Player.class.getClassLoader().getResourceAsStream("images/nyancat.png"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public Player() {
		super();
		this.x = 200;
		this.y = 300;
	}
	
	@Override
	public void update() {
		int off = 3;
		if(Keyboard.isKeyDown(KeyEvent.VK_UP)) {
			this.y -= off;
		}
		if(Keyboard.isKeyDown(KeyEvent.VK_DOWN)) {
			this.y += off;
		}
		if(Keyboard.isKeyDown(KeyEvent.VK_LEFT)) {
			this.x -= off;
		}
		if(Keyboard.isKeyDown(KeyEvent.VK_RIGHT)) {
			this.x += off;
		}

	}

	@Override
	public void paint(Graphics g) {
		g.drawImage(img, this.x, this.y, null);
	}

}
