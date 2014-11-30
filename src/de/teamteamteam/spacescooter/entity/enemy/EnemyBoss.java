package de.teamteamteam.spacescooter.entity.enemy;

import de.teamteamteam.spacescooter.brain.GameConfig;
import de.teamteamteam.spacescooter.entity.explosion.MultiExplosion;
import de.teamteamteam.spacescooter.gui.BossHealthBar;
import de.teamteamteam.spacescooter.utility.Random;

public class EnemyBoss extends Enemy{
	
	private int move = 1;
	
	public EnemyBoss(int x, int y) {
		super(x, y);
		this.setImage("images/boss.png");
		this.setPrimaryShotImage("images/shots/ballshot.png");
		this.setShootSpeed(5);
		this.setShootDelay(50);
		this.setShootSpawn(-10, 30);
		this.setShootDamage(20);
		this.setHealthPoints(300);
		this.setCollisionDamage(50);
		this.setScore(5000);
		this.willShoot = true;
		new BossHealthBar(240, 5, this);
	}
	
	/**
	 * This enemy spawns an Item on its death and causes another enemy to appear.
	 */
	@Override
	public void die() {
		super.die();
	}
	
	/**
	 * Custom MultiExplosion for this enemy.
	 */
	@Override
	public void explode() {
		new MultiExplosion(this.getCenteredX(), this.getCenteredY());
	}
	// ToDo: fetch all position variables from gameconfig instead of hardcoded vars
	@Override
	public void update() {
		super.update();
		this.setPosition(750, this.getY()+move);
		if(this.getY() == 51){
			move = 1;
		}
		if(this.getY() == 560){
			move = -1;
		}
		if(Random.nextInt(1000) < 5) new EnemyBossMinion(730, this.getY());
		if(Random.nextInt(1000) < 50) {
			createCustomShot(-10, 3, 8, 5, "images/shots/laser_red.png");
			createCustomShot(-10, 59, 8, 5, "images/shots/laser_red.png");
		}
	}
	
}
