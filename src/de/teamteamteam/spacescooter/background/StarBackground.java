package de.teamteamteam.spacescooter.background;

/**
 * The StarBackground of the first level
 */
public class StarBackground extends ScrollingBackground {
	
	/**
	 * Constructor for the background
	 */
	public StarBackground(int x, int y) {
		super(x, y);
		this.setImage("images/starbackground.png");
		this.setScrollingSpeed(-2);
	}

}
