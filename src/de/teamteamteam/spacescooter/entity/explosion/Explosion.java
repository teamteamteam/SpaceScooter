package de.teamteamteam.spacescooter.entity.explosion;

import java.util.Random;

import de.teamteamteam.spacescooter.entity.Entity;


public class Explosion extends Entity {
	
	/**
	 * Time to 'live' in update ticks. Will be decreased with each update()-call.
	 */
	private int timeToLive;
	
	/**
	 * Attributes for the 'spawn more' constructor.
	 */
	private int count;
	private int height;
	private int width;
	/**
	 * Instance of Random, so we get good random numbers.
	 */
	private Random random;
	
	/**
	 * Just be a single explosion.
	 */
	public Explosion(int x, int y) {
		super(x, y);
		this.setImage("images/explosion_proto.png");
		this.setPosition(x - (this.getWidth()/2), y - (this.getHeight()/2));
		Random rand = new Random();	
		if (rand.nextInt(99) <= 70) {
			new ExplosionOne(x, y);
		} else {
			new ExplosionTwo(x, y);
		}
	}
	
	/**
	 * Be an explosion that spawns even more explosions! :O
	 */
	public Explosion(int x, int y, int count, int width, int height) {
		super(x, y);
		System.out.println("Explosion with: " + count);
		this.setImage("images/explosion_proto.png");
		this.setPosition(x, y);
		this.random = new Random();
		this.count = 10;
		this.width = width;
		this.height = height;
		this.timeToLive = 100 * this.count; //Just a random value of ticks the explosion will take to spawn more explosions.
	}

	/**
	 * Here, a countdown is happening. Every X ticks, a new Explosion is born.
	 */
	public void update() {
		this.timeToLive--;
		if(this.timeToLive <= 0) this.remove();
		if(this.count > 0 && this.timeToLive % this.count == 0) {
			new Explosion(this.getX() + (int) (this.width * this.random.nextDouble()), this.getY() + (int) (this.height * this.random.nextDouble()));
			this.count--;
		}
	}
}
