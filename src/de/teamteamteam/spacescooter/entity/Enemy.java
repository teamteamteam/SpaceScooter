package de.teamteamteam.spacescooter.entity;

public abstract class Enemy extends ShootingEntity {

	public Enemy(int x, int y) {
		super(x, y);
	}

	protected String name;
	protected boolean willShoot;
	
	public void update() {
		super.update();
		if(willShoot)
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

