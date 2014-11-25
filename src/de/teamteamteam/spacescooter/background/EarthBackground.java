package de.teamteamteam.spacescooter.background;

public class EarthBackground extends ScrollingBackground {

	public EarthBackground(int x, int y) {
		super(x, y);
		this.setImage("images/earthbackground.png");
		this.setScrollingSpeed(-4);
	}

}
