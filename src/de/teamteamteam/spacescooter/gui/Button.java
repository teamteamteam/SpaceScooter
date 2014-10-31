package de.teamteamteam.spacescooter.gui;

import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

import de.teamteamteam.spacescooter.entity.Entity;
import de.teamteamteam.spacescooter.entity.Player;

public class Button extends Entity{

	private static BufferedImage img;
	
	static{
		try {
			img = ImageIO.read(Player.class.getClassLoader().getResourceAsStream("images/button.png"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public Button(int x, int y) {
		super(x, y);
		this.setImage(img);
	}

	@Override
	public void update() {
	}


}
