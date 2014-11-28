package de.teamteamteam.spacescooter.entity.enemy;

import de.teamteamteam.spacescooter.entity.explosion.ExplosionOne;

public class EnemyOne extends Enemy {

	public EnemyOne(int x, int y) {
		super(x, y);
		this.setImage("images/nyancat.png");
		this.setPrimaryShotImage("images/shots/laser_red.png");
		this.setShootSpeed(2);
		this.setShootDelay(42);
		this.setShootSpawn(-8, 10);
		this.setShootDamage(5);
		this.setCollisionDamage(5);
		this.setHealthPoints(5);
		this.setCollisionDamage(this.getHealthPoints());
		this.setScore(10);
	}

	@Override
	public void update() {
		super.update();
	}

	@Override
	public void explode() {
		new ExplosionOne(this.getCenteredX(), this.getCenteredY());
	}
	
}
