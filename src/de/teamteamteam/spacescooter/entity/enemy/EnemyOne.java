package de.teamteamteam.spacescooter.entity.enemy;

import de.teamteamteam.spacescooter.entity.explosion.ExplosionOne;

public class EnemyOne extends Enemy {

	public EnemyOne(int x, int y) {
		super(x, y);
		this.setImage("images/nyancat.png");
		this.setPrimaryShotImage("images/shots/laser_red.png");
		this.setShootSpeed(2);
		this.setShootDelay(62);
		this.setShootSpawn(-8, 10);
		this.setShootDamage(5);
		this.setCollisionDamage(5);
		this.setHealthPoints(100); // NyanCats are strong!
		this.setScore(10);
	}

	@Override
	public void update() {
		super.update();
		this.transpose(-1, 0);
	}

	@Override
	public void explode() {
		new ExplosionOne(this.getCenteredX(), this.getCenteredY());
	}
	
}
