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
	public static boolean keyAntialiasing = false;

	/**
	 * Whether or not to apply anti aliasing on text.
	 */
	public static boolean textAntialiasing = false;
	
	/**
	 * The maximum number of points a player can reach.
	 */
	public static int maximumPlayerScore = 99999999;
	
	/**
	 * Initial health points the player will have.
	 */
	public static int initialPlayerHealthPoints = 100;
	
	/**
	 * Initial shield points the player ship will have.
	 */
	public static int initialPlayerShieldPoints = 0;
	
	/**
	 * Damage the player ships shots will cause initially.
	 */
	public static int initialPlayerShotDamage = 10;
	
	/**
	 * Private constructor, this class will never be instantiated.
	 */
	private GameConfig() {}
	
}
