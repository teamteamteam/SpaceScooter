package de.teamteamteam.spacescooter.utility;

/**
 * Provides a single Random instance for everyone who needs it.
 * The purpose of this is to avoid creating tons of instances that will
 * fall to the garbage collector only seconds later.
 */
public class Random {
	
	/**
	 * Internal java.util.Random instance.
	 */
	private static java.util.Random random = new java.util.Random();

	
	/**
	 * Private constructor.
	 */
	private Random() {}


	/**
	 * Get the java.util.Random instance.
	 */
	public static java.util.Random getRandom() {
		return Random.random;
	}
	
	/**
	 * Wrap of java.util.Random.nextInt().
	 */
	public static int nextInt(int boundary) {
		return Random.random.nextInt(boundary);
	}
	
	/**
	 * Wrap of java.util.Random.nextBoolean().
	 */
	public static boolean nextBoolean() {
			return Random.random.nextBoolean();
	}
}
