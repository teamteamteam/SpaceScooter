package de.teamteamteam.spacescooter.entity.explosion;

import de.teamteamteam.spacescooter.utility.Random;

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
	 * Just be a single explosion.
	 */
	public MultiExplosion(int x, int y) {
		super(x, y);
	}
	
	/**
	 * Decide every 10 ticks whether or not to spawn a random explosion.
	 */
	@Override
	public void update() {
		if(this.counter % 10 == 0) {
			if(Random.nextBoolean()) {
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
		int x_offset = Random.nextInt(35);
		if(Random.nextBoolean()) x_offset *= -1;
		int y_offset = Random.nextInt(35);
		if(Random.nextBoolean()) y_offset *= -1;
		int explosionType = Random.nextInt(2);
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
