package de.teamteamteam.spacescooter.entity;

public class EnemyOne extends Enemy {

	public EnemyOne(int x, int y) {
		super(x, y);
		this.setImage("images/nyancat.png");
		this.setShootSpeed(2);
		this.setShootDelay(42);
		this.setShootSpawn(-8, 10);
		this.setHealthPoints(5);
		this.setCollisionDamage(100);
	}

	@Override
	public void update() {
		super.update();
	}
	
}
