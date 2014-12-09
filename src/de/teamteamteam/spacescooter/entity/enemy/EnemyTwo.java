package de.teamteamteam.spacescooter.entity.enemy;

import de.teamteamteam.spacescooter.entity.explosion.ExplosionTwo;

public class EnemyTwo extends Enemy{

	public EnemyTwo(int x, int y) {
		super(x, y);
		this.setImage("images/enemy02.png");
		this.setPrimaryShotImage("images/shots/laser_green.png");
		this.setShootSpeed(4);
		this.setShootDelay(120);
		this.setShootSpawn(-10, 10);
		this.setShootDamage(5);
		this.setCollisionDamage(5);
		this.setHealthPoints(5);
		this.setScore(20);
	}
	
	@Override
	public void update() {
		super.update();
		this.setPosition(this.getX()-1, this.getY());
	}

	@Override
	public void explode() {
		new ExplosionTwo(this.getCenteredX(), this.getCenteredY());
	}
	
}
