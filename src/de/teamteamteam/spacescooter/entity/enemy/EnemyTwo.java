package de.teamteamteam.spacescooter.entity.enemy;

import java.util.Random;

import de.teamteamteam.spacescooter.utility.GameConfig;

public class EnemyTwo extends Enemy{

	public EnemyTwo(int x, int y) {
		super(x, y);
		Random random = new Random();
		this.setImage("images/enemy02.png");
		this.setShootSpeed(4);
		this.setShootDelay(42);
		this.setShootSpawn(-10, 10);
		this.setHealthPoints(5);
		this.setCollisionDamage(this.getHealthPoints());
		this.setPosition(GameConfig.windowWidth, random.nextInt(GameConfig.windowHeight - this.getHeight()));
	}
	
	@Override
	public void update() {
		super.update();
		this.setPosition(this.getX()-1, this.getY());
		if(this.getX() < 0-getWidth()){
			this.remove();
			new EnemyTwo(0, 0);
		}
		if(!this.isAlive()){
			new EnemyTwo(0, 0);
		}
	}
	
}
