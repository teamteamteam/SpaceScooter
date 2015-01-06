package de.teamteamteam.spacescooter.entity;

/**
 * Image that will position itself centered on the given position and lives for a
 * short period of time.
 */
public class FlashImage extends Entity {

	/**
	 * Internal lifetime counter.
	 */
	private int lifetime;

	/**
	 * Internal tick counter.
	 */
	private int tickCounter;
	
	/**
	 * Default constructor.
	 */
	public FlashImage(int x, int y, String imagefilename, int lifetime) {
		super(x, y);
		this.setImage(imagefilename);
		this.setPosition(x - (this.getImageWidth()/2), y - (this.getImageHeight()/2));
		this.lifetime = lifetime;
	}

	/**
	 * Just wait for the time to run out and remove itself after that.
	 */
	public void update() {
		this.tickCounter++;
		if(tickCounter == 10) {
			this.lifetime--;
			if(this.lifetime == 0) {
				this.remove();
			}
			this.tickCounter = 0;
		}
	}

}
