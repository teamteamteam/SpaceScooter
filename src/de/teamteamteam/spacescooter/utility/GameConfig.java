package de.teamteamteam.spacescooter.utility;

/**
 * This static class contains important game configuration.
 */
public class GameConfig {

	/**
	 * Whether debug output (and more) is enabled or disabled.
	 */
	public static boolean DEBUG = false;
	
	/**
	 * Width of GameWindow.
	 */
	public static int windowWidth;
	
	/**
	 * Height of GameWindow.
	 */
	public static int windowHeight;

	/**
	 * Title of the game window.
	 */
	public static String windowTitle = "SpaceScooter!";
	
	
	/**
	 * Private constructor, this class will never be instantiated.
	 */
	private GameConfig() {}
	
}
