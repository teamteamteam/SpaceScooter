package de.teamteamteam.spacescooter.entity;

import java.util.Random;

import de.teamteamteam.spacescooter.screen.Screen;


public class Explosion extends LivingEntity {
	
	private boolean isActive = true;
	
	public Explosion(int x, int y) {
		super(x, y);
		this.setImage("images/explosion_proto.png");
		this.setPosition(x - (this.getWidth()/2), y - (this.getHeight()/2));
		Random rand = new Random();	
		if (rand.nextInt(99) <= 70) {
			Screen.currentScreen.addEntity(new ExplosionOne(x, y));
		} else {
			Screen.currentScreen.addEntity(new ExplosionTwo(x, y));
		}
	}
	
	public Explosion(final int x, final int y, final int count, final int height, final int width) {
		super(x, y);
		this.setImage("images/explosion_proto.png");
		this.setPosition(x, y);
		Thread explosionThread = new Thread(new Runnable() {	//not yet compatible
			public void run() {
				Random rnd = new Random();
				for (int i = 0; i <= count; i++) {
					new Explosion(x + (int) (width*rnd.nextDouble()), y + (int) (height*rnd.nextDouble()));
					try {
						Thread.sleep(5);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
					if (i == count - 1) {
						isActive = false;
					}
				}
			}
		});
		explosionThread.start();
		if(!this.isActive) {
			this.remove();
		}
	}

	public void update() {
		
	}
}
