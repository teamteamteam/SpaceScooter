package de.teamteamteam.spacescooter.entity.enemy;

import de.teamteamteam.spacescooter.brain.PlayerSession;
import de.teamteamteam.spacescooter.entity.ShootingEntity;
import de.teamteamteam.spacescooter.entity.obstacle.Obstacle;
import de.teamteamteam.spacescooter.entity.shot.Shot;
import de.teamteamteam.spacescooter.entity.spi.Collidable;
import de.teamteamteam.spacescooter.sound.SoundSystem;
import de.teamteamteam.spacescooter.utility.Random;
/**
 * The Enemy class that handles all the Enemies out there.
 * Including bosses and minibosses.
 * This class inherits all the properties of the enemies to every subclass
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
		
		// enemy has fully left the screen ..to the left!
		if(this.getX() + this.getImageWidth() < 0){
			this.remove();
			return;
		}
		
		if(willShoot == true){
			this.shoot();
		}
	}
	
	/**
	 * Enemies are smart enough to dodge Obstacles.
	 */
	@Override
	public void collideWith(Collidable entity) {
		if(entity instanceof Obstacle) return;
		super.collideWith(entity);
	}
	
	/**
	 * Every enemy that dies awards the Player one credit and points.
	 */
	@Override
	public void die() {
		PlayerSession.addCredits(1);
		super.die();
	}
	
	@Override
	public void createShot() {
		super.createShot();
		SoundSystem.playSound("sounds/shot_fired2.wav");
	}


}

