package de.teamteamteam.spacescooter.entity.enemy;

import de.teamteamteam.spacescooter.entity.Player;
import de.teamteamteam.spacescooter.entity.explosion.MultiExplosion;
import de.teamteamteam.spacescooter.entity.item.Item;
import de.teamteamteam.spacescooter.screen.GameScreen;
import de.teamteamteam.spacescooter.utility.Random;

public class EnemyThree extends Enemy{

	private double newY;
	private double ySpeed = 0.4;
	
	public EnemyThree(int x, int y) {
		super(x, y);
		this.setImage("images/enemy03.png");
		this.setPrimaryShotImage("images/shots/laser_red.png");
		this.setShootSpeed(4);
		this.setShootDelay(42);
		this.setShootSpawn(-10, 10);
		this.setShootDamage(5);
		this.setHealthPoints(15);
		this.setCollisionDamage(10);
		this.setScore(30);
		this.newY = this.getY();
	}
	
	/**
	 * This enemy spawns an Item on its death and causes another enemy to appear.
	 */
	@Override
	public void die() {
		if(Random.nextInt(10) < 5) Item.create(getX(), getY());
		super.die();
	}
	
	/**
	 * Custom MultiExplosion for this enemy.
	 */
	@Override
	public void explode() {
		new MultiExplosion(this.getCenteredX(), this.getCenteredY());
	}
	
	@Override
	public void update() {
		super.update();
		this.setPosition(this.getX()-1, this.getY());
		Player player = GameScreen.getPlayer();
		if(this.getY() < player.getY()){
			this.newY += ySpeed;
			this.setPosition(this.getX(), (int) newY);
		}else if(this.getY() > player.getY()){
			this.newY -= ySpeed;
			this.setPosition(this.getX(), (int) newY);
		}
	}
}
