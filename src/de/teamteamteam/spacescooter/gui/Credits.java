package de.teamteamteam.spacescooter.gui;

public class Credits {
	/**
	 * Credit points for the shop
	 */
	private static int credits = 1000;

	public static int getCredits() {
		return credits;
	}

	public static void setCredits(int credits) {
		Credits.credits = credits;
		System.out.println(credits);
	}
}
