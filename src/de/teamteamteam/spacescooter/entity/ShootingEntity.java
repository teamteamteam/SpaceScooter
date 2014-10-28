package de.teamteamteam.spacescooter.entity;

import de.teamteamteam.spacescooter.screen.Screen;

public abstract class ShootingEntity extends LivingEntity {

	private int shootDelay;
	private int currentShootDelay;
	private int shootSpawnX;
	private int shootSpawnY;
	
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
	
	public void setShootDelay(int shootDelay) {
		this.shootDelay = shootDelay;
	}
	
	public void setShootSpawn(int x, int y) {
		this.shootSpawnX = x;
		this.shootSpawnY = y;
	}
}
