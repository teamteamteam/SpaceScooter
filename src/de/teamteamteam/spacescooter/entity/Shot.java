package de.teamteamteam.spacescooter.entity;

import java.awt.image.BufferedImage;

public abstract class Shot extends CollidableEntity {

	protected int damageValue;
	protected int collisionCount;
	
	
	public Shot(int x, int y, BufferedImage img) {
		super(x - img.getWidth() / 2, y - img.getHeight() / 2);
		this.collisionCount = 1;
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
