package de.teamteamteam.spacescooter.entity;

import de.teamteamteam.spacescooter.screen.Screen;

public abstract class ShootingEntity extends LivingEntity {

	protected int shootDelay;
	protected int currentShootDelay;
	protected int shootSpawnX;
	protected int shootSpawnY;
	
	public ShootingEntity(int x, int y) {
		super(x, y);
		this.shootDelay = 10;
		this.currentShootDelay = 0;
	}

	public void update() {
		if(this.currentShootDelay > 0) this.currentShootDelay--;
	}
	
	protected void shoot() {
		if(this.currentShootDelay == 0) {
			Screen.currentScreen.addEntity(new SingleShot(this.x + this.shootSpawnX, this.y + this.shootSpawnY));
			this.currentShootDelay = this.shootDelay;
		}
	}
}
