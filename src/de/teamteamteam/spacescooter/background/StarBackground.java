package de.teamteamteam.spacescooter.background;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

import de.teamteamteam.spacescooter.entity.Player;


public class StarBackground extends Background {

	public StarBackground(int x, int y) {
		super(x, y);
	}

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
		this.offset -= 3;
		//System.out.println(this.offset);
		if(Math.abs(this.offset) > StarBackground.img.getWidth()) {
			this.offset += StarBackground.img.getWidth();
		}
	}
	
	public void paint(Graphics2D g) {
		g.drawImage(StarBackground.img, (0+this.offset), 0, null);
		g.drawImage(StarBackground.img, (StarBackground.img.getWidth()+this.offset), 0, null);
	}
	
}
