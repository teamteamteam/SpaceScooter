package de.teamteamteam.spacescooter.entity;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.event.KeyEvent;

import de.teamteamteam.spacescooter.brain.GameConfig;
import de.teamteamteam.spacescooter.brain.PlayerSession;
import de.teamteamteam.spacescooter.control.Keyboard;
import de.teamteamteam.spacescooter.control.KeyboardListener;
import de.teamteamteam.spacescooter.entity.enemy.Enemy;
import de.teamteamteam.spacescooter.entity.explosion.MultiExplosion;
import de.teamteamteam.spacescooter.entity.obstacle.Obstacle;
import de.teamteamteam.spacescooter.entity.shot.Shot;
import de.teamteamteam.spacescooter.entity.spi.Collidable;
import de.teamteamteam.spacescooter.sound.SoundSystem;

/**
 * Class that represents the Player, and handle all the KeyboardActions
 */
public class Player extends ShootingEntity implements KeyboardListener {

	/**
	 * Keyboard instance used to register on for KeyboardEvents.
	 */
	private Keyboard keyboard;
	
	/**
	 * Ammunition of the secondary weapon.
	 */
	private int secondaryWeaponAmount;

	/**
	 * Cooldown countdown value to use in case
	 * the Player got hit.
	 */
	private int collisionCooldown;
	
	/**
	 * The actual countdown variable used to enforce the
	 * collision "timeout".
	 * (Player gets hit, blinks a while, gets solid again)
	 */
	private int currentCollisionCooldown;
	
	
	/**
	 * Constructor for initializing the Player on the GameScreen
	 */
	public Player(int x, int y) {
		super(x, y);
		this.secondaryWeaponAmount = 0;
		this.collisionCooldown = 150;
		this.currentCollisionCooldown = 0;
		this.setImage("images/ship.png");
		this.setPrimaryShotImage("images/shots/laser_blue.png");
		this.setShootDelay(20);
		this.setShootSpawn(50, 16);
		this.setShootDirection(Shot.RIGHT);
		this.setShootSpeed(10);
		this.setCollisionDamage(5);
		this.setScore(0);
		this.setHealthPoints(PlayerSession.getShipHealthPoints());
		this.setMaximumHealthPoints(PlayerSession.getShipHealthPoints());
		this.setShieldPoints(PlayerSession.getShipShieldPoints());
		this.setMaximumShieldPoints(PlayerSession.getShipShieldPoints());
		this.setShootDamage((PlayerSession.getShipShotDamage())/2);
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
		if(this.canMove() == false) return;
		super.update();
		//Collision cooldown handling
		if(this.currentCollisionCooldown > 0) {
			this.currentCollisionCooldown--;
		} else {
			this.setVulnerable(true);
			this.setDamaging(true);
		}
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
			if(this.secondaryWeaponAmount > 0){				
				if(PlayerSession.getSecondsecondaryWeapon() == 1) {
					this.shootRocket();
				}else if(PlayerSession.getSecondsecondaryWeapon() == 2) {
					this.shootBeam();
				}
			}
		}
	}
	
	/**
	 * Override paint method for custom effects
	 */
	@Override
	public void paint(Graphics2D g) {
		if(this.currentCollisionCooldown > 0) {
			g.setColor(Color.RED);
			g.drawRect(this.getX(), this.getY(), this.getWidth(), this.getHeight());
		}
		super.paint(g);
	}
	
	/**
	 * Implement damage immunity logic after collisions with Enemy or Obstacle Entities.
	 */
	@Override
	public void collideWith(Collidable entity) {
		super.collideWith(entity);
		if(this.currentCollisionCooldown == 0 && (entity instanceof Enemy || entity instanceof Obstacle)) {
			this.currentCollisionCooldown = this.collisionCooldown;
			this.setVulnerable(false);
			this.setDamaging(false);
		}
	}
	
	/**
	 * Explode method that trigger the ShootingEntity.explode() method and play a nice sound
	 */
	@Override
	public void explode() {
		SoundSystem.playSound("sounds/abgang.wav");
		new MultiExplosion(this.getCenteredX(), this.getCenteredY());
	}
	
	/**
	 * createShot method that trigger the ShootingEntity.createShot() method and play a nice sound
	 */
	@Override
	public void createShot(int x, int y) {
		super.createShot(x,y);
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
		
		//DEBUG: Get X and Y
		if(e.getKeyCode() == KeyEvent.VK_F6) {
			System.out.println("X: " + this.getX());
			System.out.println("Y: " + this.getY());
			System.out.println();
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
	
	@Override
	public void shoot() {
		if(this.canShoot() == true) {
			if(this.getCurrentShootDelay() == 0) {
				this.createShot(30,8);
				this.createShot(30,23);
				setCurrentShootDelay(this.getShootDelay());
			}
		}
	}

	
	@Override
	public void createRocket() {
		super.createRocket();
		this.removeSecondaryWeaponAmount();
	}
	
	@Override
	public void createBeam() {
		super.createBeam();
		this.removeSecondaryWeaponAmount();
	}
	
	/**
	 *  Get the current secondary weapon amount.
	 */
	public int getSecondaryWeaponAmount(){
		return secondaryWeaponAmount;
	}
	/**
	 *  Add one secondary weapon amount.
	 */
	public void addSecondaryWeaponAmount(){
		secondaryWeaponAmount++;
	}
	
	/**
	 *  Remove one secondary weapon amount.
	 */
	public void removeSecondaryWeaponAmount(){
		secondaryWeaponAmount--;
	}
	
	
}
