package de.teamteamteam.spacescooter.background;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

import de.teamteamteam.spacescooter.entities.Player;


public class StarBackground extends Background {

	private static BufferedImage img;
	static {
		try {
			img = ImageIO.read(Player.class.getClassLoader().getResourceAsStream("images/starbackground.png"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private int offset = 0;
	
	public void update(long millisecondsSinceLastCall) {
		this.offset -= 5;
		if(Math.abs(this.offset) > StarBackground.img.getWidth()) {
			this.offset += StarBackground.img.getWidth();
		}
	}
	
	public void paint(Graphics g) {
		g.drawImage(StarBackground.img, 0+this.offset, 0, StarBackground.img.getWidth(), StarBackground.img.getHeight(), null);
		g.drawImage(StarBackground.img, StarBackground.img.getWidth()+this.offset, 0, StarBackground.img.getWidth(), StarBackground.img.getHeight(), null);
	}
	
}
