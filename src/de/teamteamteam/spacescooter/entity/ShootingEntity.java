package de.teamteamteam.spacescooter.entity;

import de.teamteamteam.spacescooter.screen.Screen;

public abstract class ShootingEntity extends LivingEntity {

	protected int shootDelay;
	protected int currentShootDelay;
	protected int shootSpawnX;
	protected int shootSpawnY;
	protected int shootDirection;
	protected int damageValue = 5;
	protected int shootSpeed;
	
	public ShootingEntity(int x, int y) {
		super(x, y);
		this.currentShootDelay = 0;
	}

	public void update() {
		super.update();
		if(this.currentShootDelay > 0) this.currentShootDelay--;
	}
	
	protected void shoot() {
		if(this.currentShootDelay == 0) {
			Screen.currentScreen.addEntity(new SingleShot(this.x + this.shootSpawnX, this.y + this.shootSpawnY, this.shootDirection, this.shootSpeed, this.damageValue));
			this.currentShootDelay = this.shootDelay;
		}
	}
	
	public void setShootDirection(int direction) {
		this.shootDirection = direction;
	}
	
	public void setShootSpeed(int speed) {
		this.shootSpeed = speed;
	}
	
	public void setShootDelay(int shootDelay) {
		this.shootDelay = shootDelay;
	}
	
	public void setShootSpawn(int x, int y) {
		this.shootSpawnX = x;
		this.shootSpawnY = y;
	}
	
	public void setDamageValue(int damageValue){
		this.damageValue = damageValue;
	}
	
	public int getDamageValue(){
		return this.damageValue;
	}
}
