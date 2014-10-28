package de.teamteamteam.spacescooter.entity;

import java.util.Random;

public abstract class Enemy extends ShootingEntity {

	public Enemy(int x, int y) {
		super(x, y);
		Random r = new Random();
		this.name = "EnemyOne";
		this.willShoot = r.nextBoolean();
		this.setShootDirection(Shot.LEFT);
	}

	protected String name;
	protected boolean willShoot;
	
	public void update() {
		super.update();
		if(willShoot == true)
			this.shoot();
	}
	
	public void collideWith(Collidable e) {
		if(e instanceof Shot) {
			Shot s = (Shot) e;
			//TODO: Expand with shield logic and stuff.
			this.healthPoints -= s.getDamageValue();
		}
	}

}

