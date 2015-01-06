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
	 * Internal counter for update ticks. Used as a measurement to stop/start scrolling.
	 */
	private int updateTickCount;

	/**
	 * This guy is used as a speed divisor - it is used in a modulo operation.
	 * 1 - Scrolling at full speed, 101 - not scrolling at all
	 * 2 - Scrolling at half speed.
	 */
	private int updateFrameDivider;
	
	/**
	 * Delta telling in which direction the scrolling speed will transition.
	 * (Basically, this is being added to the updateFrameDivider)
	 * Can be 0 (not changing), -1 or 1.
	 */
	private int updateFrameDelta;
	
	
	/**
	 * Default constructor.
	 */
	public ScrollingBackground(int x, int y) {
		super(x, y);
		this.offset = 0;
		this.updateTickCount = 1;
		this.updateFrameDivider = 101;
		this.updateFrameDelta = 0;
	}
	

	/**
	 * Set the scrolling speed to use for this Background.
	 */
	public void setScrollingSpeed(int scrollingSpeed) {
		this.scrollingSpeed = scrollingSpeed;
	}
	
	/**
	 * 
	 */
	public void startScrolling() {
		this.updateFrameDelta = -1;
		this.updateFrameDivider = 10;
	}
	
	/**
	 * 
	 */
	public void stopScrolling() {
		this.updateFrameDelta = 1;
	}
	
	/**
	 * Tell whether the Background is currently scrolling.
	 */
	public boolean isScrolling() {
		return this.updateFrameDivider == 1;
	}
	
	/**
	 * Tell whether the Background is currently changing the scrolling state.
	 */
	public boolean scrollingStateChanging() {
		return this.updateFrameDelta != 0;
	}
	
	/**
	 * Apply the scrolling effect by updating an internal offset counter.
	 * Reset it when we scrolled for one image length.
	 */
	public void update() {
		this.updateTickCount++;
		if(this.updateTickCount == 100) this.updateTickCount = 1;
		if(this.updateFrameDelta != 0 && this.updateTickCount % 10 == 0) {
			this.updateFrameDivider += this.updateFrameDelta;
			if(this.updateFrameDelta == -1 && this.updateFrameDivider == 1) {
				this.updateFrameDelta = 0;
			}
			if(this.updateFrameDelta == 1 && this.updateFrameDivider == 10) {
				this.updateFrameDelta = 0;
				this.updateFrameDivider = 101;
			}
		}
		if(this.updateTickCount % this.updateFrameDivider == 0) {
			this.offset += this.scrollingSpeed;
			if(Math.abs(this.offset) > this.getImage().getWidth()) {
				this.offset += this.getImage().getWidth();
			}
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
