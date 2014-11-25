package de.teamteamteam.spacescooter.entity;

import java.awt.event.KeyEvent;

import de.teamteamteam.spacescooter.control.Keyboard;
import de.teamteamteam.spacescooter.control.KeyboardListener;
import de.teamteamteam.spacescooter.entity.item.Item;
import de.teamteamteam.spacescooter.entity.shot.Shot;
import de.teamteamteam.spacescooter.entity.spi.Collidable;
import de.teamteamteam.spacescooter.sound.SoundSystem;
import de.teamteamteam.spacescooter.utility.GameConfig;

/**
 * Class that represents the Player, and handle all the KeyboardActions
 */
public class Player extends ShootingEntity implements KeyboardListener {

	/**
	 * the Player's Keyboard
	 */
	private Keyboard keyboard = null;
	
	/**
	 * the Players Health Points in percent
	 */
	private double healthPercent = 0;
	
	/**
	 * the Players ShieldPoints in percent
	 */
	private double shieldPercent = 0;
	
	/**
	 * the Players Rocket Ammunition
	 */
	private int rocketAmount = 10;
	
	/**
	 * the Players Beam Ammunition
	 */
	private int beamAmount = 10;

	
	/**
	 * Constructor for initializing the Player on the GameScreen
	 */
	public Player(int x, int y) {
		super(x, y);
		this.setImage("images/ship.png");
		this.setPrimaryShotImage("images/shots/laser_blue.png");
		this.setShootDamage(StaticValue.ShootDamage);
		this.setShootDelay(20);
		this.setShootSpawn(50, 16);
		this.setShootDirection(Shot.RIGHT);
		this.setShootSpeed(10);
		this.setCollisionDamage(10);
		this.setShieldPoints(StaticValue.ShieldPoints);
		this.setHealthPoints(StaticValue.HealthPoints);
		this.registerOnKeyboard(Keyboard.getInstance());
	}

	/**
	 * Method for register the Player on the Keyboard 
	 */
	private void registerOnKeyboard(Keyboard keyboard) {
		this.keyboard = keyboard;
		this.keyboard.addListener(this);
	}

	/**
	 * Standard update method
	 */
	public void update() {
		if (StaticValue.HealthPoints != 0) {
			this.healthPercent = ((double) this.getHealthPoints() / (double) StaticValue.HealthPoints) * 100;
		}
		if (StaticValue.ShieldPoints != 0) {
			this.shieldPercent = ((double) this.getShieldPoints() / (double) StaticValue.ShieldPoints) * 100;
		}
		if(this.canMove()) {
			super.update();
			int offset = 3;
			if(Keyboard.isKeyDown(KeyEvent.VK_UP) && this.getY() > 51) {
				this.transpose(0, -1 * offset);
			}
			if(Keyboard.isKeyDown(KeyEvent.VK_DOWN) && this.getY() < (GameConfig.windowHeight - this.getImage().getHeight())) {
				this.transpose(0, offset);
			}
			if(Keyboard.isKeyDown(KeyEvent.VK_LEFT) && this.getX() > 0) {
				this.transpose(-1 * offset, 0);
			}
			if(Keyboard.isKeyDown(KeyEvent.VK_RIGHT) && this.getX() < (GameConfig.windowWidth - this.getImage().getWidth())) {
				this.transpose(offset, 0);
			}
			//continuous fire takes place here
			if(Keyboard.isKeyDown(KeyEvent.VK_SPACE)) {
				this.shoot();
			}
			if(Keyboard.isKeyDown(KeyEvent.VK_Y)) {
				if(rocketAmount > 0){
					this.shootRocket();
				}
			}
			if(Keyboard.isKeyDown(KeyEvent.VK_X)) {
				if(this.beamAmount > 0){
					this.shootBeam();
				}
			}
		}
	}

	/**
	 * Determine what will happen if a Player collides with an Item.
	 */
	@Override
	public void collideWith(Collidable entity) {
		super.collideWith(entity);
		if(this instanceof Player && entity instanceof Item){
			//Item item = (Item) entity;
			//Apply cool item effects here ...
		}
	}
	
	/**
	 * Explode method that trigger the ShootingEntity.explode() method and play a nice sound
	 */
	@Override
	public void explode() {
		super.explode();
		SoundSystem.playSound("sounds/abgang.wav");
	}
	
	/**
	 * createShot method that trigger the ShootingEntity.createShot() method and play a nice sound
	 */
	@Override
	public void createShot() {
		super.createShot();
		SoundSystem.playSound("sounds/shot_fired1.wav");
	}
	
	/**
	 * On cleanup, unregister from the keyboard.
	 */
	@Override
	public void remove() {
		if(this.keyboard != null) {
			this.keyboard.removeListener(this);
			this.keyboard = null;
		}
		super.remove();
	}

	/**
	 * keyPressed method, comes in handy when a key on the keyboard is pressed
	 */
	public void keyPressed(KeyEvent e) {
		//spontaneous fire happens here
		if(e.getKeyCode() == KeyEvent.VK_SPACE) {
			this.shoot();
		}
	}

	/**
	 * keyReleased method, reacts if a key on the keyboard is released
	 */
	public void keyReleased(KeyEvent e) {
		//space up -> reset shot cooldown
		if(e.getKeyCode() == KeyEvent.VK_SPACE) {
			this.resetShootDelay();
		}
	}

	/**
	 * empty keyTyped method, maybe useful for cheatcodes later
	 */
	public void keyTyped(KeyEvent e) {}
	
	/**
	 * return the Healthpercentage of the Player
	 */
	public int getHealthPercent() {
		return (int) this.healthPercent;
	}
	
	/**
	 * return the Shieldpercentage of the Player
	 */
	public int getShieldPercent() {
		return (int) this.shieldPercent;
	}

	/**
	 * method for increasing the HealthPoints with the Heal-Item
	 */
	public void increaseHealthPoints(int inc) {
		if (this.getHealthPoints() <= (StaticValue.HealthPoints - 15)) {
			this.setHealthPoints(getHealthPoints() + inc);
		} else {
			this.setHealthPoints(StaticValue.HealthPoints);
		}
	}
	
	/**
	 * method for increasing the ShieldPoints with the Shield-Item
	 */
	public void increaseShieldPoints(int inc) {
		if (this.getShieldPoints() <= (StaticValue.ShieldPoints - 5)) {
			this.setShieldPoints(getShieldPoints() + inc);
		} else {
			this.setShieldPoints(StaticValue.ShieldPoints);
		}
	}
	
	/**
	 *  Get the current rocket amount.
	 */
	public int getRocketAmount(){
		return rocketAmount;
	}
	
	public int getBeamAmount(){
		return beamAmount;
	}
	
	/**
	 *  Add one rocket.
	 */
	public void addRocketAmount(){
		rocketAmount++;
	}
	
	public void addBeamAmount(){
		beamAmount++;
	}
	
	/**
	 *  Remove one rocket.
	 */
	public void removeRocketAmount(){
		rocketAmount--;
	}
	
	public void removeBeamAmount(){
		beamAmount--;
	}
	
}
