package de.teamteamteam.spacescooter.entity.enemy;

import java.util.Random;

import de.teamteamteam.spacescooter.datastructure.ConcurrentIterator;
import de.teamteamteam.spacescooter.entity.Entity;
import de.teamteamteam.spacescooter.entity.Player;
import de.teamteamteam.spacescooter.entity.item.Items;
import de.teamteamteam.spacescooter.screen.Screen;
import de.teamteamteam.spacescooter.utility.GameConfig;

public class EnemyThree extends Enemy{

	private double newY;
	private double ySpeed = 0.4;
	private Random random;
	
	public EnemyThree(int x, int y) {
		super(x, y);
		random = new Random();
		this.setImage("images/enemy02.png");
		this.setPrimaryShotImage("images/shots/laser_red.png");
		this.setShootSpeed(4);
		this.setShootDelay(42);
		this.setShootSpawn(-10, 10);
		this.setHealthPoints(15);
		this.setCollisionDamage(this.getHealthPoints());
		this.setPosition(GameConfig.windowWidth, random.nextInt(GameConfig.windowHeight - this.getHeight()));
		this.newY = this.getY();
	}
	
	/**
	 * This enemy spawns an Item on its death and causes another enemy to appear.
	 */
	@Override
	public void die() {
		if(random.nextInt(10) < 5) Items.create(getX(), getY());
		new EnemyThree(0, 0);
		super.die();
	}
	
	@Override
	public void update() {
		super.update();
		this.setPosition(this.getX()-1, this.getY());
		if(this.getX() < 0-getWidth()){
			this.remove();
			new EnemyThree(0, 0);
		}
		ConcurrentIterator<Entity> i =  Screen.currentScreen.getEntityIterator();
		while (i.hasNext()) {
			Entity entity = i.next();
			if(entity instanceof Player){
				Player player = (Player) entity;
				if(this.getY() < player.getY()){
					this.newY += ySpeed;
					this.setPosition(this.getX(), (int) newY);
				}else if(this.getY() > player.getY()){
					this.newY -= ySpeed;
					this.setPosition(this.getX(), (int) newY);
				}
			}
		}
	}
	
}
