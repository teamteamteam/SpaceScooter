package de.teamteamteam.spacescooter.entity;

import java.awt.event.KeyEvent;

import de.teamteamteam.spacescooter.control.Keyboard;
import de.teamteamteam.spacescooter.control.KeyboardListener;
import de.teamteamteam.spacescooter.entity.shot.Shot;
import de.teamteamteam.spacescooter.sound.SoundSystem;
import de.teamteamteam.spacescooter.utility.GameConfig;

public class Player extends ShootingEntity implements KeyboardListener {
	
	private Keyboard keyboard = null;
	
	public Player(int x, int y) {
		super(x, y);
		this.setImage("images/ship.png");
		this.setPrimaryShotImage("images/shots/laser_blue.png");
		this.setShootDamage(5);
		this.setShootDelay(20);
		this.setShootSpawn(50, 16);
		this.setShootDirection(Shot.RIGHT);
		this.setShootSpeed(10);
		this.setHealthPoints(100);
		this.registerOnKeyboard(Keyboard.getInstance());
	}

	private void registerOnKeyboard(Keyboard keyboard) {
		this.keyboard = keyboard;
		this.keyboard.addListener(this);
	}

	public void update() {
		if(this.canMove()) {
			super.update();
			int offset = 3;
			if(Keyboard.isKeyDown(KeyEvent.VK_UP) && this.getY() > 0) {
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

	@Override
	public void explode() {
		super.explode();
		SoundSystem.playSound("sounds/abgang.wav");
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


}
