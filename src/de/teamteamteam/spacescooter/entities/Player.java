package de.teamteamteam.spacescooter.entities;

import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;

import javax.imageio.ImageIO;

import de.teamteamteam.spacescooter.controls.Keyboard;

public class Player extends Entity {

	private int x;
	private int y;
	
	private static BufferedImage img;
	
	static {
		try {
			InputStream res = Player.class.getClassLoader().getResourceAsStream("images/nyancat.png");
			if(res == null) {
				System.out.println("Kein res :/");
			}
			img = ImageIO.read(res);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public Player() {
		this.x = 200;
		this.y = 300;
	}
	
	@Override
	public void update() {
		if(Keyboard.isKeyDown(KeyEvent.VK_UP)) {
			this.y--;
		}
		if(Keyboard.isKeyDown(KeyEvent.VK_DOWN)) {
			this.y++;
		}
		if(Keyboard.isKeyDown(KeyEvent.VK_LEFT)) {
			this.x--;
		}
		if(Keyboard.isKeyDown(KeyEvent.VK_RIGHT)) {
			this.x++;
		}

	}

	@Override
	public void paint(Graphics g) {
		g.drawImage(img, this.x, this.y, null);
	}

}
