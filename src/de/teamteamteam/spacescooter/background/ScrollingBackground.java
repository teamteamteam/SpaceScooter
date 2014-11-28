package de.teamteamteam.spacescooter.background;

import java.awt.Graphics2D;

import de.teamteamteam.spacescooter.brain.GameConfig;

public class ScrollingBackground extends Background {

	/**
	 * Speed the background scrolls through with.
	 */
	private int scrollingSpeed;
	
	/**
	 * Internal offset counter to determine how much the Background scrolled.
	 */
	private int offset;
	
	
	/**
	 * Default constructor.
	 */
	public ScrollingBackground(int x, int y) {
		super(x, y);
		this.offset = 0;
	}
	

	/**
	 * Set the scrolling speed to use for this Background.
	 */
	public void setScrollingSpeed(int scrollingSpeed) {
		this.scrollingSpeed = scrollingSpeed;
	}
	
	/**
	 * Apply the scrolling effect by updating an internal offset counter.
	 * Reset it when we scrolled for one image length.
	 */
	public void update() {
		this.offset += this.scrollingSpeed;
		if(Math.abs(this.offset) > this.getImage().getWidth()) {
			this.offset += this.getImage().getWidth();
		}
	}
	
	/**
	 * Paint the Background. Concatenate the image if necessary, but still try
	 * to save ressources.
	 * TODO: Huge performance improvements using the tons of param version of drawImage!
	 */
	public void paint(Graphics2D g) {
		int i = 0;
		int wWidth = GameConfig.windowWidth;
		while((i*this.getImage().getWidth()+this.offset) < wWidth) {
			g.drawImage(this.getImage(), (i*this.getImage().getWidth()+this.offset), this.getY(), null);
			i++;
		}
	}

}
