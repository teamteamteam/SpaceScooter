package de.teamteamteam.spacescooter.entity;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.Random;

import de.teamteamteam.spacescooter.screen.Screen;
import de.teamteamteam.spacescooter.utility.GameConfig;

public class EnemyThree extends Enemy{

	private double newY;
	private double ySpeed = 0.4;
	
	public EnemyThree(int x, int y) {
		super(x, y);
		Random random = new Random();
		this.setImage("images/nyancat.png");
		this.setShootSpeed(4);
		this.setShootDelay(42);
		this.setShootSpawn(-10, 10);
		this.setHealthPoints(5);
		this.setPosition(GameConfig.windowWidth, random.nextInt(GameConfig.windowHeight - this.getHeight()));
		this.newY = this.getY();
	}
	
	@Override
	public void update() {
		super.update();
		this.setPosition(this.getX()-1, this.getY());
		if(this.getX() < 0-getWidth()){
			this.remove();
			Screen.currentScreen.addEntity(new EnemyThree(0, 0));
		}
		if(!this.isAlive()){
			Screen.currentScreen.addEntity(new EnemyThree(0, 0));
		}
		LinkedList<Entity> list = Screen.currentScreen.getEntities();
		Iterator<Entity> i = list.iterator();
		while (i.hasNext()) {
			Entity entity = i.next();
			if(entity instanceof Player){
				Player player = (Player) entity;
				if(this.y < player.getY()){
					this.newY += ySpeed;
					this.setPosition(this.getX(), (int) newY);
				}else if(this.y > player.getY()){
					this.newY -= ySpeed;
					this.setPosition(this.getX(), (int) newY);
				}
			}
		}
	}
	
}
