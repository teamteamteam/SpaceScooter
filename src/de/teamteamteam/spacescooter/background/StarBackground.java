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
	
	public void update() {
		this.offset -= 15;
		if(Math.abs(this.offset) > this.img.getWidth()) {
			this.offset += this.img.getWidth();
		}
	}
	
	public void paint(Graphics g) {
		g.drawImage(this.img, 0+this.offset, 0, this.img.getWidth(), this.img.getHeight(), null);
		g.drawImage(this.img, this.img.getWidth()+this.offset, 0, this.img.getWidth(), this.img.getHeight(), null);
	}
	
}
