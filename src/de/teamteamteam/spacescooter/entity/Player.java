package de.teamteamteam.spacescooter.entity;

import java.awt.event.KeyEvent;
import de.teamteamteam.spacescooter.control.Keyboard;
import de.teamteamteam.spacescooter.utility.GameConfig;

public class Player extends ShootingEntity {

	private boolean canMove = true;

	public Player(int x, int y) {
		super(x, y);
		this.setImage("images/ship.png");
		this.setShootDelay(40);
		this.setShootSpawn(50, 16);
		this.setShootDirection(Shot.RIGHT);
		this.setShootSpeed(4);
		this.setHealthPoints(100);
	}

	public void update() {
		if(this.canMove){	
			super.update();
			int off = 3;
			if(Keyboard.isKeyDown(KeyEvent.VK_UP) && this.y > 0) {
				this.y -= off;
			}
			if(Keyboard.isKeyDown(KeyEvent.VK_DOWN) && this.y < (GameConfig.windowHeight - this.getImage().getHeight())) {
				this.y += off;
			}
			if(Keyboard.isKeyDown(KeyEvent.VK_LEFT) && this.x > 0) {
				this.x -= off;
			}
			if(Keyboard.isKeyDown(KeyEvent.VK_RIGHT) && this.x < (GameConfig.windowWidth - this.getImage().getWidth())) {
				this.x += off;
			}
			if(Keyboard.isKeyDown(KeyEvent.VK_SPACE)) {
				this.shoot();
			}
		}
	}

	public void setCanMove(boolean canMove){
		this.canMove = canMove;
	}
	
}
