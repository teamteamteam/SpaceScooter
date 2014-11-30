package de.teamteamteam.spacescooter.entity.enemy;

import de.teamteamteam.spacescooter.brain.GameConfig;
import de.teamteamteam.spacescooter.entity.explosion.ExplosionTwo;
import de.teamteamteam.spacescooter.utility.Random;

public class EnemyTwo extends Enemy{

	public EnemyTwo(int x, int y) {
		super(x, y);
		this.setImage("images/enemy02.png");
		this.setPrimaryShotImage("images/shots/laser_green.png");
		this.setShootSpeed(4);
		this.setShootDelay(42);
		this.setShootSpawn(-10, 10);
		this.setShootDamage(5);
		this.setCollisionDamage(5);
		this.setHealthPoints(5);
		this.setScore(20);
		this.setCollisionDamage(this.getHealthPoints());
	}
	
	@Override
	public void update() {
		super.update();
		this.setPosition(this.getX()-1, this.getY());
		if(this.getX() < 0-getWidth()){
			this.remove();
		}
	}

	@Override
	public void explode() {
		new ExplosionTwo(this.getCenteredX(), this.getCenteredY());
	}
	
}
