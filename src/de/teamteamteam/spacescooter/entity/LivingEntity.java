package de.teamteamteam.spacescooter.entity;

public abstract class LivingEntity extends CollidableEntity {

	public LivingEntity(int x, int y) {
		super(x, y);
	}

	protected int healthPoints;
	protected int shieldPoints;
	
	public boolean isAlive() {
		return healthPoints > 0;
	}
}
