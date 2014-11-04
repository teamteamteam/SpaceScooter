package de.teamteamteam.spacescooter.entity;

import java.awt.event.KeyEvent;
import de.teamteamteam.spacescooter.control.Keyboard;
import de.teamteamteam.spacescooter.utility.GameConfig;

public class Player extends ShootingEntity {
	
	private boolean shoot = false;
	private boolean canMove = true;

	public Player(int x, int y) {
		super(x, y);
		this.setImage("images/ship.png");
		this.setShootDelay(5);
		this.setShootSpawn(50, 16);
		this.setShootDirection(Shot.RIGHT);
		this.setShootSpeed(4);
		this.setHealthPoints(10000);
		inputThread.start();
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
			if(shoot == true) {
				this.shoot();
			}
		}
			
	}

	public void setCanMove(boolean canMove){
		this.canMove = canMove;
	}
	
	public void setShoot(boolean shoot){
		this.shoot = shoot;
	}
	
	Thread inputThread = new Thread(new Runnable() {
        public void run() {
        	int down = 0;
            while (true) {
            	setShootDelay(5);
            	
            	
            	if(Keyboard.isKeyDown(KeyEvent.VK_SPACE)) {
            		do {
            			if (Keyboard.isKeyUp(KeyEvent.VK_SPACE)) 
            				System.out.print("lol");
            			setShoot(true);
            			
            			setShootDelay(20);
            		} while(Keyboard.isKeyDown(KeyEvent.VK_SPACE));
            	} else {
            		setShootDelay(5);
            		setShoot(false);
            	}
            }
        }
    });
	
}
