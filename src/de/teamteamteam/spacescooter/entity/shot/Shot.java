package de.teamteamteam.spacescooter.entity.shot;

import de.teamteamteam.spacescooter.entity.LivingEntity;
import de.teamteamteam.spacescooter.utility.GameConfig;

/**
 * This class represents a Shot in the game.
 * It takes care of its movements, contains information about its
 * look and damage, and ends its life once it is out of the visible screen.
 */
public class Shot extends LivingEntity {

	/**
	 * Valid value for shootDirection parameter.
	 * Makes the Shot travel to the right.
	 */
	public static final int RIGHT = 1;
	
	/**
	 * Valid value for shootDirection parameter.
	 * Makes the Shot travel to the left.
	 */
	public static final int LEFT= -1;
	
	/**
	 * How much damage a shot will do to a LivingEntity that gets hit by it
	 */
	private int damageValue;
	
	/**
	 * Speed at which the shot travels.
	 */
	private int speed;
	
	/**
	 * Direction in which the shot travels. May be Shot.LEFT or Shot.RIGHT.
	 */
	private int direction;
	
	/**
	 * Constructor. Takes all information needed to create a new Shot object ready to hit things.
	 */
	public Shot(int x, int y, int shootDirection, int shootSpeed, int damageValue, String filename) {
		super(x, y);
		this.direction = shootDirection;
		this.speed = shootSpeed;
		this.damageValue = damageValue;
		this.setImage(filename);
		this.setPosition(this.getX() - this.getImage().getWidth() / 2, this.getY() - this.getImage().getHeight() / 2);
	}

	/**
	 * Returns the damage this shot does to LivingEntities it hits.
	 */
	public int getDamageValue() {
		return this.damageValue;
	}
	
	/**
	 * Set a new damage value for this shot.
	 */
	public void setDamageValue(int dmg) {
		this.damageValue = dmg;
	}

	/**
	 * Make the shot travel in the right direction.
	 * Remove the shot once out of the visible area.
	 */
	public void update() {
		this.transpose(this.direction * this.speed, 0);
		//remove the shot in case it is out of the game window.
		if ((this.getX() + this.getWidth()) < 0 || this.getX() > GameConfig.windowWidth
				|| (this.getY() + this.getHeight()) < 0
				|| this.getY() > GameConfig.windowHeight) {
			this.remove();
		}
	}
	
}
