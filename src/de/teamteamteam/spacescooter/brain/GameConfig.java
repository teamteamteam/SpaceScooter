package de.teamteamteam.spacescooter.brain;

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
	public static int windowWidth = 800;
	
	/**
	 * Height of GameWindow.
	 */
	public static int windowHeight = 650;

	/**
	 * Title of the game window.
	 */
	public static String windowTitle = "SpaceScooter!";
	
	/**
	 * Whether or not anti aliasing will be used for shapes.
	 */
	public static boolean key_antialiasing = false;

	/**
	 * Whether or not to apply anti aliasing on text.
	 */
	public static boolean text_antialiasing = false;
	
	
	/**
	 * Private constructor, this class will never be instantiated.
	 */
	private GameConfig() {}
	
}
