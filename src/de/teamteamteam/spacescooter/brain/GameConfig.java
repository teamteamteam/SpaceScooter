package de.teamteamteam.spacescooter.brain;

/**
 * This static class contains important game configuration.
 * Contains initial constant values only.
 */
public final class GameConfig {

	/**
	 * Whether debug output (and more) is enabled or disabled.
	 */
	public static final boolean DEBUG = false;
	
	/**
	 * Width of GameWindow.
	 */
	public static final int windowWidth = 800;
	
	/**
	 * Height of GameWindow.
	 */
	public static final int windowHeight = 650;
	
	/**
	 * Offset where the X=0 coordinate of the actual game screen starts.
	 */
	public static final int gameScreenXOffset = 0;

	/**
	 * Offset where the Y=0 coordinate of the actual game screen starts.
	 * This is currently influenced by the 50px interface bar at the top.
	 */
	public static final int gameScreenYOffset = 50;
	
	/**
	 * Actual width of the game screen.
	 */
	public static final int gameScreenWidth = GameConfig.windowWidth;
	
	/**
	 * Actual height of the game screen.
	 * This is currently influenced by the 50px interface bar at the top.
	 */
	public static final int gameScreenHeight = GameConfig.windowHeight - 50;

	/**
	 * Title of the game window.
	 */
	public static final String windowTitle = "SpaceScooter!";
	
	/**
	 * Whether or not anti aliasing will be used for shapes.
	 */
	public static final boolean keyAntialiasing = false;

	/**
	 * Whether or not to apply anti aliasing on text.
	 */
	public static final boolean textAntialiasing = false;
	
	/**
	 * The maximum number of points a player can reach.
	 */
	public static final int maximumPlayerScore = 99999999;
	
	/**
	 * Initial health points the player will have.
	 */
	public static final int initialPlayerHealthPoints = 100;
	
	/**
	 * Initial shield points the player ship will have.
	 */
	public static final int initialPlayerShieldPoints = 0;
	
	/**
	 * Damage the player ships shots will cause initially.
	 */
	public static final int initialPlayerShotDamage = 10;
	
	/**
	 * The first level the game will start with.
	 */
	public static final String firstLevel = "levels/test.level";
	
	/**
	 * Private constructor, this class will never be instantiated.
	 */
	private GameConfig() {}
	
}
