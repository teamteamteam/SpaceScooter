package de.teamteamteam.spacescooter.entity;

public abstract class Enemy extends CollidableEntity {

	protected String name;
	protected boolean willShoot;
	
	@Override
	public void update() {
		if(willShoot)
			this.shoot();
	}
	
	protected abstract void shoot();
	



}

