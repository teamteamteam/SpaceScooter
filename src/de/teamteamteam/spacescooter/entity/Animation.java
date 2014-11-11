package de.teamteamteam.spacescooter.entity;

/**
 * A very simple image slideshow animation.
 * Given a list of images and an interval, it does a (fast) slideshow
 * through all the images, changing them after <interval> ticks.
 */
public class Animation extends Entity {

	/**
	 * The list of images to display
	 */
	private String[] images;
	
	/**
	 * Internal counter to check whether the interval is over.
	 */
	private int counter;
	
	/**
	 * Interval parameter - change the image after each interval.
	 */
	private int interval;
	
	/**
	 * Index of the current image that is displayed.
	 */
	private int currentImage;
	
	
	/**
	 * Default constructor.
	 */
	public Animation(int x, int y) {
		super(x, y);
	}

	
	/**
	 * Set the list of images to display plus the interval.
	 */
	public void configure(String[] images, int interval) {
		this.images = images;
		this.interval = interval;
		this.counter = 0;
		this.currentImage = 0;
		this.setImage(this.images[this.currentImage]);
	}
	
	/**
	 * Increase the counter every tick, then check whether the interval is over.
	 * If the interval is over, check whether there are more images to display.
	 * If there is one, display the next image, else it is just over and the
	 * Animation removes itself.
	 */
	public void update() {
		this.counter++;
		if(this.counter % this.interval == 0) {
			this.currentImage++;
			if(this.currentImage == this.images.length) {
				this.remove();
			} else {
				this.setImage(this.images[this.currentImage]);
			}
		}
	}
	

}
