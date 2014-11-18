package de.teamteamteam.spacescooter.entity;

import java.awt.event.KeyEvent;

import de.teamteamteam.spacescooter.control.Keyboard;
import de.teamteamteam.spacescooter.control.KeyboardListener;
import de.teamteamteam.spacescooter.entity.item.Item;
import de.teamteamteam.spacescooter.entity.shot.Shot;
import de.teamteamteam.spacescooter.entity.spi.Collidable;
import de.teamteamteam.spacescooter.sound.SoundSystem;
import de.teamteamteam.spacescooter.utility.GameConfig;

public class Player extends ShootingEntity implements KeyboardListener {
	
	private Keyboard keyboard = null;
	private double healthPercent = 0;
	private double shieldPercent = 0;
	
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

	private void registerOnKeyboard(Keyboard keyboard) {
		this.keyboard = keyboard;
		this.keyboard.addListener(this);
	}

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
	
	@Override
	public void explode() {
		super.explode();
		SoundSystem.playSound("sounds/abgang.wav");
	}
	
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

	public void keyPressed(KeyEvent e) {
		//spontaneous fire happens here
		if(e.getKeyCode() == KeyEvent.VK_SPACE) {
			this.shoot();
		}
	}

	public void keyReleased(KeyEvent e) {
		//space up -> reset shot cooldown
		if(e.getKeyCode() == KeyEvent.VK_SPACE) {
			this.resetShootDelay();
		}
	}

	public void keyTyped(KeyEvent e) {}
	
	public int getHealthPercent() {
		return (int) this.healthPercent;
	}
	
	public int getShieldPercent() {
		return (int) this.shieldPercent;
	}
	
	public void increaseHealthPoints(int inc) {
		if (this.getHealthPoints() <= 85) {
			this.setHealthPoints(getHealthPercent() + inc);
		} else {
			this.setHealthPoints(100);
		}
	}
	
}
