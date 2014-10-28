package de.teamteamteam.spacescooter.entity;

import java.awt.image.BufferedImage;

public abstract class Shot extends CollidableEntity {

	public static final int RIGHT = 1;
	public static final int LEFT= -1;
	
	protected int damageValue;
	protected int collisionCount;
	
	private int speed;
	private int direction;
	
	public Shot(int x, int y, int shootDirection, int shootSpeed) {
		super(x, y);
		this.direction = shootDirection;
		this.speed = shootSpeed;
		this.collisionCount = 1;
	}
	
	public void setImage(BufferedImage img) {
		super.setImage(img);
		this.setPosition(this.x - this.getImage().getWidth() / 2, this.y - this.getImage().getHeight() / 2);
	}
	
	public void collideWith(Collidable entity) {
		this.collisionCount--;
		if(this.collisionCount == 0) {
			//TODO: scoot is over
		}
	}
	
	public int getDamageValue() {
		return this.damageValue;
	}
	
	public void update() {
		this.x += this.direction * this.speed;
	}
	
}
