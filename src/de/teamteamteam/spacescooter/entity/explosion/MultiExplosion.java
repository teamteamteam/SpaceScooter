package de.teamteamteam.spacescooter.entity.explosion;

import java.util.Random;

/**
 * Extends the functionality of the simple ExplosionOne to randomly
 * spawn more explosions in the area.
 */
public class MultiExplosion extends ExplosionOne {

	/**
	 * Internal tick counter.
	 */
	private int counter;
	
	/**
	 * Instance of Random, so we get good random numbers.
	 */
	private Random random;

	/**
	 * Just be a single explosion.
	 */
	public MultiExplosion(int x, int y) {
		super(x, y);
		this.random = new Random();
	}
	
	/**
	 * Decide every 10 ticks whether or not to spawn a random explosion.
	 */
	@Override
	public void update() {
		if(this.counter % 10 == 0) {
			if(this.random.nextBoolean()) {
				this.spawnExplosion();
			}
		}
		this.counter++;
		super.update();
	}

	/**
	 * Randomly spawn a new random explosion at a random location.
	 */
	private void spawnExplosion() {
		int x_offset = this.random.nextInt(35);
		if(this.random.nextBoolean()) x_offset *= -1;
		int y_offset = this.random.nextInt(35);
		if(this.random.nextBoolean()) y_offset *= -1;
		int explosionType = this.random.nextInt(2);
		switch(explosionType) {
			case 0:
				new ExplosionOne(this.getX() + x_offset, this.getY() + y_offset);
				break;
			case 1:
				new ExplosionTwo(this.getX() + x_offset, this.getY() + y_offset);
				break;
			default:
				break;
		}
	}
}
