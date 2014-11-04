package de.teamteamteam.spacescooter.entity;

import de.teamteamteam.spacescooter.screen.Screen;
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

	@Override
	protected void shoot() {
		if(this.currentShootDelay == 0) {
			Screen.currentScreen.addEntity(new SingleRedShot(this.x + this.shootSpawnX, this.y + this.shootSpawnY, this.shootDirection, this.shootSpeed, this.damageValue));
			this.currentShootDelay = this.shootDelay;
		}
	}

}

