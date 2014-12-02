package de.teamteamteam.spacescooter.entity;

import de.teamteamteam.spacescooter.brain.GameConfig;
import de.teamteamteam.spacescooter.brain.PlayerSession;
import de.teamteamteam.spacescooter.entity.enemy.Enemy;
import de.teamteamteam.spacescooter.entity.shot.Shot;
import de.teamteamteam.spacescooter.entity.spi.Collidable;
import de.teamteamteam.spacescooter.entity.spi.Hittable;

/**
 * A LivingEntity is an Entity that is able to take damage and to collide with
 * stuff. (See CollidableEntity) It can collide with other Collidable Entities.
 * It knows about its collision box, which is based on width and height. Also,
 * it contains the generic logic about health points and shield points and takes
 * care of damage calculations.
 */
public abstract class LivingEntity extends CollidableEntity implements Hittable {

	/**
	 * The LivingEntities current health points.
	 */
	private int healthPoints;

	/**
	 * The LivingEntities current shield points.
	 */
	private int shieldPoints;

	/**
	 * The LivingEntities maximum health points.
	 */
	private int maximumHealthPoints;

	/**
	 * The LivingEntities maximum shield points.
	 */
	private int maximumShieldPoints;

	/**
	 * The score points awarded if the LivingEntity dies.
	 */
	private int scorePoints;
	
	/**
	 * Whether the LivingEntity will take damage.
	 */
	private boolean damagable;
	
	
	/**
	 * Default constructor. Initializes sane defaults.
	 */
	public LivingEntity(int x, int y) {
		super(x, y);
		this.setDamagable(true);
	}

	/**
	 * Handle collisions based on what the LivingEntity collided with. Triggers
	 * damage calculations for itself only. Override to add specific behaviour,
	 * e.g. a Player picking up Items.
	 */
	public void collideWith(Collidable entity) {
		if (entity instanceof Shot) {
			Shot s = (Shot) entity;
			if (this instanceof Enemy && s.getDirection() == Shot.LEFT)
				return;
			if (this instanceof Player && s.getDirection() == Shot.RIGHT)
				return;
			this.takeDamage(s.getDamageValue());
		}
		if (entity instanceof Player && (!(this instanceof Player))) {
			Player player = (Player) entity;
			this.takeDamage(player.getCollisionDamage());
		}
		if (entity instanceof Enemy && (!(this instanceof Enemy))) {
			Enemy enemy = (Enemy) entity;
			this.takeDamage(enemy.getCollisionDamage());
		}
	}

	/**
	 * Determine whether the LivingEntity is still alive.
	 */
	public boolean isAlive() {
		return healthPoints > 0;
	}

	/**
	 * Process incoming damage by calculating remaining health points and shield
	 * points. Triggers die() method on death.
	 */
	public void takeDamage(int damage) {
		//Skip taking damage if not vulnerable.
		if(!this.getVulnerable())
			return;
		// Skip everything if already dead.
		if (this.isAlive() == false)
			return;
		if (this.shieldPoints > 0) {
			if (this.shieldPoints < damage) {
				this.healthPoints = (damage - this.shieldPoints);
				this.shieldPoints = 0;
			} else {
				this.shieldPoints -= damage;
			}
		} else {
			this.healthPoints -= damage;
		}
		if (this.isAlive() == false) {
			// Set the correct values for gui indicators
			this.healthPoints = 0;
			this.shieldPoints = 0;
			this.die();
		}
	}

	/**
	 * The default way the LivingEntity explodes. Override this method for a
	 * different explosion behaviour.
	 */
	public abstract void explode();

	/**
	 * The default way the LivingEntity dies. Override this method for a
	 * different death behaviour.
	 */
	public void die() {
		if (GameConfig.DEBUG) {
			System.out.println(this + " ist gestorben. RIP");
		}
		PlayerSession.addScore(scorePoints);
		this.explode();
		this.remove();
	}

	/**
	 * Set the current health points.
	 */
	public void setHealthPoints(int hp) {
		this.healthPoints = hp;
	}
	
	/**
	 * Add the given number of points to the current health points.
	 * Capped at maximum health points.
	 */
	public void addHealthPoints(int hp) {
		this.healthPoints += hp;
		if(this.healthPoints > this.maximumHealthPoints) {
			this.healthPoints = this.maximumHealthPoints;
		}
	}

	/**
	 * Get the current health points.
	 */
	public int getHealthPoints() {
		return this.healthPoints;
	}
	
	/**
	 * Get the current health percentage.
	 * Returns an int in [0,100], divide by 100 for double percentage.
	 */
	public int getHealthPercentage() {
		if(this.maximumHealthPoints == 0) return 0;
		return (this.healthPoints * 100) / this.maximumHealthPoints;
	}

	/**
	 * Set the current shield points.
	 */
	public void setShieldPoints(int sp) {
		this.shieldPoints = sp;
	}
	
	/**
	 * Add the given number of points to the current shield points.
	 * Capped at maximum shield points.
	 */
	public void addShieldPoints(int sp) {
		this.shieldPoints += sp;
		if(this.shieldPoints > this.maximumShieldPoints) {
			this.shieldPoints = this.maximumShieldPoints;
		}
	}

	/**
	 * Get the current shield points.
	 */
	public int getShieldPoints() {
		return this.shieldPoints;
	}
	
	/**
	 * Get the current shield percentage.
	 * Returns an int in [0,100], divide by 100 for double percentage.
	 */
	public int getShieldPercentage() {
		if(this.maximumShieldPoints == 0) return 0;
		return (this.shieldPoints * 100) / this.maximumShieldPoints;
	}
	
	/**
	 * Set the maximum health points.
	 */
	public void setMaximumHealthPoints(int maxhp) {
		this.maximumHealthPoints = maxhp;
	}

	/**
	 * Get the maximum health points.
	 */
	public int getMaximumHealthPoints() {
		return this.maximumHealthPoints;
	}

	/**
	 * Set the maximum shield points.
	 */
	public void setMaximumShieldPoints(int maxsp) {
		this.maximumShieldPoints = maxsp;
	}

	/**
	 * Get the maximum shield points.
	 */
	public int getMaximumShieldPoints() {
		return this.maximumShieldPoints;
	}
	
	/**
	 * Set the current score points.
	 */
	public void setScore(int s) {
		this.scorePoints = s;
	}
	
	/**
	 * Set whether the LivingEntity is damagable.
	 * This defaults to true on construction and will only be changed manually.
	 */
	public void setDamagable(boolean damagable) {
		this.damagable = damagable;
	}
	
	/**
	 * Whether or not the LivingEntity is damagable.
	 * This defaults to true on construction and will only be changed manually.
	 */
	public boolean getVulnerable() {
		return this.damagable;
	}

}
