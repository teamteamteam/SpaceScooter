package de.teamteamteam.spacescooter.entity;

import de.teamteamteam.spacescooter.entity.shot.Shot;

/**
 * The ShootingEntity is a LivingEntity that is able to fire Shots.
 * It handles the generic shooting logic concerning the delay between shots,
 * the damage the fired shot will cause and some more details.
 */
public abstract class ShootingEntity extends LivingEntity {

	/**
	 * Whether the ShootingEntity is currently able to fire Shots.
	 */
	private boolean canShoot = true;
	
	/**
	 * The tick delay to wait until the next Shot can be fired.
	 * This value is pretty much constant.
	 */
	private int shootDelay;
	
	/**
	 * The current tick delay to wait until the next Shot can be fired.
	 * This value is used to enforce the actual delay defined in shootDelay.
	 */
	private int currentShootDelay;
	
	/**
	 * The X delta to pass to the Shot, so it spawns at the 
	 * right position relative to the ShootingEntity.
	 */
	private int shootSpawnX;

	/**
	 * The Y delta to pass to the Shot, so it spawns at the 
	 * right position relative to the ShootingEntity.
	 */
	private int shootSpawnY;
	
	/**
	 * The direction the fired Shot will travel into.
	 * Use Shot.LEFT or Shot.RIGHT.
	 */
	private int shootDirection;
	
	/**
	 * The damage the fired Shots will cause.
	 */
	private int shootDamage;
	
	/**
	 * The speed the fired Shots will travel at.
	 */
	private int shootSpeed;
	
	/**
	 * The image to use for the primary Shots.
	 */
	private String primaryShotImage;
	
	
	/**
	 * Constructor.
	 * Initializes the currentShootDelay.
	 */
	public ShootingEntity(int x, int y) {
		super(x, y);
		this.currentShootDelay = 0;
	}

	/**
	 * Update logic making sure that the currentShootDelay is updated.
	 */
	public void update() {
		super.update();
		if(this.currentShootDelay > 0) this.currentShootDelay--;
	}
	
	/**
	 * Fire a Shot. This method handles all the delay stuff, so use
	 * this method in your code.
	 */
	public void shoot() {
		if(this.canShoot == true) {
			if(this.currentShootDelay == 0) {
				this.createShot();
				this.currentShootDelay = this.shootDelay;
			}
		}
	}

	/**
	 * Enable or disable the ShootingEntitys fire ability.
	 */
	public void setCanShoot(boolean canShoot) {
		this.canShoot = canShoot;
	}
	
	/**
	 * Returns true if the ShootingEntity is able to fire.
	 */
	public boolean canShoot() {
		return this.canShoot;
	}
	
	/**
	 * Configure the shoot direction.
	 * Use Shot.LEFT or Shot.RIGHT
	 */
	public void setShootDirection(int direction) {
		this.shootDirection = direction;
	}
	
	/**
	 * Set the speed the fired Shots will travel at.
	 */
	public void setShootSpeed(int speed) {
		this.shootSpeed = speed;
	}
	
	/**
	 * Set the ShootingEntites shoot delay.
	 */
	public void setShootDelay(int shootDelay) {
		this.shootDelay = shootDelay;
	}
	
	/**
	 * Use this to reset the current shoot cooldown once.
	 */
	public void resetShootDelay() {
		this.currentShootDelay = 0;
	}
	
	/**
	 * Set the delta position the Shots shall spawn at.
	 * Use this to make sure the shots come out where you want it.
	 */
	public void setShootSpawn(int x, int y) {
		this.shootSpawnX = x;
		this.shootSpawnY = y;
	}
	
	/**
	 * Set the damage value the fired shots will cause.
	 */
	public void setShootDamage(int shootDamage){
		this.shootDamage = shootDamage;
	}
	
	/**
	 * Get the current damage the fired shots will cause.
	 */
	public int getShootDamage(){
		return this.shootDamage;
	}

	/**
	 * Set the image for primary shots by filename.
	 * The Loader will be used internally to fetch the image.
	 */
	public void setPrimaryShotImage(String filename){
	    this.primaryShotImage = filename;
	}

	/**
	 * Internal method to actually spawn the fired Shots.
	 */
	private void createShot() {
		new Shot(
			this.getX() + this.shootSpawnX,
			this.getY() + this.shootSpawnY,
			this.shootDirection,
			this.shootSpeed,
			this.shootDamage,
			this.primaryShotImage
		);
	}
}
