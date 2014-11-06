package de.teamteamteam.spacescooter.thread;

import de.teamteamteam.spacescooter.screen.Screen;

/**
 * This UpdateThread triggers the hierarchical Screen structure,
 * resulting in constant game ticks.
 */
public class UpdateThread extends TimedThread {
	
	/**
	 * The SuperScreen instance to trigger.
	 */
	private Screen superScreen;

	/**
	 * Constructor. Sets the ThreadName.
	 */
	public UpdateThread() {
		this.setName("UpdateThread");
	}
	
	/**
	 * Setter for the SuperScreen.
	 */
	public void setSuperScreen(Screen superScreen) {
		this.superScreen = superScreen;
	}
	
	/**
	 * On every tick, hit the SuperScreens doUpdate() method.
	 */
	public void work() {
		this.superScreen.doUpdate();
	}

}
