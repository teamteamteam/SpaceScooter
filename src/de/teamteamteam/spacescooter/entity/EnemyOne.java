package de.teamteamteam.spacescooter.entity;

import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

public class EnemyOne extends Enemy {

	private static BufferedImage img;
	
	static {
		try {
			img = ImageIO.read(Player.class.getClassLoader().getResourceAsStream("images/nyancat.png"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public EnemyOne(int x, int y) {
		super(x, y);
		this.setImage(EnemyOne.img);
		this.setShootSpeed(2);
		this.setShootDelay(42);
		this.setShootSpawn(0, 32);
	}

	@Override
	public void update() {
		super.update();
	}
	
}
