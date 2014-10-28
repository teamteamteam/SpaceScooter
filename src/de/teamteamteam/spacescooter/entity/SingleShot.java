package de.teamteamteam.spacescooter.entity;

import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

public class SingleShot extends Shot {

	private static BufferedImage img;
	
	static {
		try {
			img = ImageIO.read(Player.class.getClassLoader().getResourceAsStream("images/shot.png"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	

	public SingleShot(int x, int y) {
		super(x, y);
		this.setImage(img);
	}
	
	public void update() {
		this.x += 2;
	}

}
