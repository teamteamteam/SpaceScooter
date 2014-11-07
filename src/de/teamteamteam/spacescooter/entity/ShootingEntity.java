package de.teamteamteam.spacescooter.entity;

import de.teamteamteam.spacescooter.entity.shot.Shot;

public abstract class ShootingEntity extends LivingEntity {

	private boolean canShoot = true;
	
	private int shootDelay;
	private int currentShootDelay;
	private int shootSpawnX;
	private int shootSpawnY;
	private int shootDirection;
	private int damageValue = 5;
	private int shootSpeed;
	private String primaryShotImage;
	
	public ShootingEntity(int x, int y) {
		super(x, y);
		this.currentShootDelay = 0;
	}

	public void update() {
		super.update();
		if(this.currentShootDelay > 0) this.currentShootDelay--;
	}
	
	protected void shoot() {
		if(this.canShoot == true) {
			if(this.currentShootDelay == 0) {
				this.createShot();
				this.currentShootDelay = this.shootDelay;
			}
		}
	}

	public void createShot() {
		new Shot(
				this.getX() + this.shootSpawnX,
				this.getY() + this.shootSpawnY,
				this.shootDirection,
				this.shootSpeed,
				this.damageValue,
				this.primaryShotImage
				);
	}

	public void setCanShoot(boolean canShoot) {
		this.canShoot = canShoot;
	}
	
	public boolean canShoot() {
		return this.canShoot;
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
	
	public void resetShootDelay() {
		this.currentShootDelay = 0;
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
	
	public void setPrimaryShotImage(String filename){
	    this.primaryShotImage = filename;
	}


}
