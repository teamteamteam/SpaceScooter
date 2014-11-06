package de.teamteamteam.spacescooter.screen;

import java.awt.Graphics2D;

/**
 * This is the SuperScreen.
 * It is the root of our Screen hierarchy and therefore exists at all times.
 * Its overlay represents things like the MainMenuScreen, the actual GameScreen,
 * and many more.
 */
public class SuperScreen extends Screen {

	/**
	 * Default constructor.
	 */
	public SuperScreen(Screen parent) {
		super(null);
	}

	/**
	 * Blank implementation of the paint method.
	 */
	@Override
	public void paint(Graphics2D g) {
		//nothing to paint, we're so meta meta.
	}

	/**
	 * Blank implementation of the update method.
	 */
	@Override
	public void update() {
		//dummy method
	}

}
