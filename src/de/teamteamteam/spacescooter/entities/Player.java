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
	
	public void update(long millisecondsSinceLastCall) {
		System.out.println(millisecondsSinceLastCall / (1000.0/60.0));
		int offset = (int) ((3.0F/16.0F) * millisecondsSinceLastCall);
		if(Keyboard.isKeyDown(KeyEvent.VK_UP)) {
			this.y -= offset;
		}
		if(Keyboard.isKeyDown(KeyEvent.VK_DOWN)) {
			this.y += offset;
		}
		if(Keyboard.isKeyDown(KeyEvent.VK_LEFT)) {
			this.x -= offset;
		}
		if(Keyboard.isKeyDown(KeyEvent.VK_RIGHT)) {
			this.x += offset;
		}

	}

	public void paint(Graphics g) {
		g.drawImage(img, this.x, this.y, null);
	}

}
