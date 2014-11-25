package de.teamteamteam.spacescooter.entity.enemy;

import de.teamteamteam.spacescooter.entity.ShootingEntity;
import de.teamteamteam.spacescooter.entity.shot.Shot;
import de.teamteamteam.spacescooter.sound.SoundSystem;
import de.teamteamteam.spacescooter.utility.Random;
/**
 * The Enemy class that handle all the Enemies out there.
 * Including bosses and minibosses.
 * This class inherit all the properties of the enemies to every subclass
 */
public abstract class Enemy extends ShootingEntity {

	/**
	 * Constructor for one Enemy, which will construct a ShootingEntity 
	 */
	public Enemy(int x, int y) {
		super(x, y);
		this.name = "EnemyOne";
		this.willShoot = Random.nextBoolean();
		this.setShootDirection(Shot.LEFT);
		this.setShootDamage(5);
	}

	/**
	 * Name of the Enemy
	 */
	protected String name;
	
	/**
	 * boolean value that will determine whether a new spawned enemy will shoot or not
	 */
	protected boolean willShoot;
	
	/**
	 * standard update method
	 */
	public void update() {
		super.update();
		if(willShoot == true){
			this.shoot();
		}
		if(this.getX() < 0-getWidth()){
			this.remove();
		}
	}
	
	@Override
	public void createShot() {
		super.createShot();
		SoundSystem.playSound("sounds/shot_fired2.wav");
	}


}

