package de.teamteamteam.spacescooter.entity;

import java.awt.image.BufferedImage;

public abstract class Shot extends CollidableEntity {

	protected int damageValue;
	protected int collisionCount;
	
	
	public Shot(int x, int y) {
		super(x, y);
		this.collisionCount = 1;
	}
	
	public void setImage(BufferedImage img) {
		super.setImage(img);
		this.setPosition(this.x - this.getImage().getWidth() / 2, this.y - this.getImage().getHeight() / 2);
	}
	
	public void collideWith(Collidable entity) {
		this.collisionCount--;
		if(this.collisionCount == 0) {
			//scoot is over
		}
	}
	
	public int getDamageValue() {
		return this.damageValue;
	}
}
