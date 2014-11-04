package de.teamteamteam.spacescooter.entity;

import java.awt.event.KeyEvent;
import de.teamteamteam.spacescooter.control.Keyboard;
import de.teamteamteam.spacescooter.utility.GameConfig;
import de.teamteamteam.spacescooter.screen.Screen;

public class Player extends ShootingEntity {
	
	protected boolean shoot = false;
	private boolean canMove = true;


	public Player(int x, int y) {
		super(x, y);
		this.setImage("images/ship.png");
		this.setShootDelay(5);
		this.setShootSpawn(50, 16);
		this.setShootDirection(Shot.RIGHT);
		this.setShootSpeed(10);
		this.setHealthPoints(100);
	}

	public void update() {
		if(this.canMove) {
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
			if(Keyboard.isKeyDown(KeyEvent.VK_SPACE) && shoot==false) {
				shoot = true;
				this.shoot();
			}
			if(!Keyboard.isKeyDown(KeyEvent.VK_SPACE) && shoot==true) {
				shoot = false;
			}
		}
		
		
	}

	@Override
	protected void shoot() {
		if(this.currentShootDelay == 0) {
			Screen.currentScreen.addEntity(new SingleBlueShot(this.x + this.shootSpawnX, this.y + this.shootSpawnY, this.shootDirection, this.shootSpeed, this.damageValue));
			this.currentShootDelay = this.shootDelay;
		}
	}

	public void setCanMove(boolean canMove){
		this.canMove = canMove;
	}
	
}
