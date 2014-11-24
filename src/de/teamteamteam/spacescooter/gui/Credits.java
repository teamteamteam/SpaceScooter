package de.teamteamteam.spacescooter.gui;

/**
 * Static class accounting the credits a player earned during the game.
 */
public class Credits {
	
	/**
	 * Credit points for the shop
	 */
	private static int credits = 0;
	
	
	/**
	 * Private constructor.
	 */
	private Credits() {	}
	
	
	/**
	 * Get the current amount of credits.
	 */
	public static int getCredits() {
		return credits;
	}

	/**
	 * Set the current amount of credits.
	 */
	public static void setCredits(int credits) {
		Credits.credits = credits;
	}
	
	/**
	 * Add a number of credits to the credits account.
	 */
	public static void add(int credits) {
		Credits.credits += credits;
	}
	
	/**
	 * Remove a number of credits to the credits account.
	 */
	public static void remove(int credits) {
		Credits.credits -= credits;
	}
}
