package de.teamteamteam.spacescooter.brain;

/**
 * This class is responsible for keeping track of all the data a player session 
 * usually contains, such as earned score points, earned credits, activated ship upgrades
 * and more.
 */
public class PlayerSession {

	/**
	 * The players current score.
	 */
	private static int score;
	
	/**
	 * The players current amount of credits.
	 */
	private static int credits;

	/**
	 * Damage value of the normal Shots the ship fires.
	 * This can be changed by upgrades in the shop.
	 */
	private static int baseShotDamage;

	/**
	 * The ships default maximum shield points.
	 * This can be changed by upgrades in the shop.
	 */
	private static int baseShieldPoints;

	/**
	 * The ships default maximum health points.
	 * This can be changed by upgrades in the shop.
	 */
	private static int baseHealthPoints;
	
	/**
	 * The number of health upgrades the player bought for the ship.
	 */
	private static int baseHealthUpgadesBought;

	/**
	 * The number of shield upgrades the player bought for the ship.
	 */
	private static int baseShieldUpgadesBought;
	
	/**
	 * The number of shot damage upgrades the player bought for the ship.
	 */
	private static int baseShotUpgadesBought;
	
	/**
	 * The secondary weapon of the ship.
	 * 1 = Rocket.
	 * 2 = Beam.
	 */
	private static int secondaryWeapon;
	
	/**
	 * The next level to load for the player.
	 */
	private static String nextLevel;
	
	
	/**
	 * Private constructor, this class will never be instantiated.
	 */
	private PlayerSession() {}
	
	
	/**
	 * Get the current amount of credits the player has.
	 */
	public static int getCredits() {
		return PlayerSession.credits;
	}
	
	/**
	 * Add a number of credits to the players credits.
	 */
	public static void addCredits(int credits) {
		PlayerSession.credits += credits;
	}

	/**
	 * Remove a number of credits from the players credits.
	 * This is capped at zero.
	 */
	public static void removeCredits(int credits) {
		PlayerSession.credits -= credits;
		if(PlayerSession.credits < 0) {
			PlayerSession.credits = 0;
		}
	}

	/**
	 * Get the current player score.
	 */
	public static int getScore() {
		return PlayerSession.score;
	}
	
	/**
	 * Add a number of points to the players score.
	 * There is a maximum of X points to reach, score is capped then.
	 */
	public static void addScore(int points) {
		PlayerSession.score += points;
		if(PlayerSession.score > GameConfig.maximumPlayerScore) {
			PlayerSession.score = GameConfig.maximumPlayerScore;
		}
	}

	/**
	 * Remove a number of points from the players score.
	 * This is capped at zero.
	 */
	public static void removeScore(int points) {
		PlayerSession.score -= points;
		if(PlayerSession.score < 0) {
			PlayerSession.score = 0;
		}
	}
	
	/**
	 * Get the ships maximum health points.
	 */
	public static int getBaseHealthPoints() {
		return PlayerSession.baseHealthPoints;
	}
	
	/**
	 * Set the ships maximum health points.
	 */
	public static void setBaseHealthPoints(int baseHealthPoints) {
		PlayerSession.baseHealthPoints = baseHealthPoints;
	}
	
	/**
	 * Add to the ships maximum health points.
	 */
	public static void addBaseHealthPoints(int baseHealthPoints) {
		PlayerSession.baseHealthPoints += baseHealthPoints;
	}
	
	/**
	 * Get the ships maximum shield points.
	 */
	public static int getBaseShieldPoints() {
		return PlayerSession.baseShieldPoints;
	}
	
	/**
	 * Set the ships maximum shield points.
	 */
	public static void setBaseShieldPoints(int baseShieldPoints) {
		PlayerSession.baseShieldPoints = baseShieldPoints;
	}
	
	/**
	 * Add to the ships maximum shield points.
	 */
	public static void addBaseShieldPoints(int baseShieldPoints) {
		PlayerSession.baseShieldPoints += baseShieldPoints;
	}
	
	/**
	 * Get the ships shot damage value.
	 */
	public static int getBaseShotDamage() {
		return PlayerSession.baseShotDamage;
	}
	
	/**
	 * Set the ships shot damage value.
	 */
	public static void setBaseShotDamage(int baseShotDamage) {
		PlayerSession.baseShotDamage = baseShotDamage;
	}
	
	/**
	 * Add to the ships shot damage value.
	 */
	public static void addBaseShotDamage(int shipShotDamage) {
		PlayerSession.baseShotDamage += shipShotDamage;
	}

	/**
	 * Get the number of ship health upgrades the player bought in the shop.
	 */
	public static int getBaseHealthUpgradesBought() {
		return PlayerSession.baseHealthUpgadesBought;
	}
	
	/**
	 * Increment the number of ship health upgrades the player bought.
	 */
	public static void incrementBaseHealthUpgradesBought() {
		PlayerSession.baseHealthUpgadesBought++;
	}

	/**
	 * Get the number of ship shield upgrades the player bought in the shop.
	 */
	public static int getBaseShieldUpgradesBought() {
		return PlayerSession.baseShieldUpgadesBought;
	}

	/**
	 * Increment the number of ship shield upgrades the player bought.
	 */
	public static void incrementBaseShieldUpgradesBought() {
		PlayerSession.baseShieldUpgadesBought++;
	}
	
	/**
	 * Get the number of ship shot upgrades the player bought in the shop.
	 */
	public static int getBaseShotUpgradesBought() {
		return PlayerSession.baseShotUpgadesBought;
	}

	/**
	 * Increment the number of ship shot upgrades the player bought.
	 */
	public static void incrementBaseShotUpgradesBought() {
		PlayerSession.baseShotUpgadesBought++;
	}

	/**
	 * Get the secondary weapon.
	 */
	public static int getSecondsecondaryWeapon(){
		return PlayerSession.secondaryWeapon;
	}
	
	/**
	 * Set the secondary weapon.
	 */
	public static void setSecondsecondaryWeapon(int secondaryWeapon){
		PlayerSession.secondaryWeapon = secondaryWeapon;
	}
	
	/**
	 * Get the next Level the player will play.
	 */
	public static String getNextLevel() {
		return PlayerSession.nextLevel;
	}

	/**
	 * Set the next Level the player will play.
	 */
	public static void setNextLevel(String nextLevel) {
		PlayerSession.nextLevel = nextLevel;
	}
	
	/**
	 * This will reset all data from the players session.
	 * Used to initialize the session at the beginning of the game or
	 * after the player went game over and entered his name for the highscore.
	 * (So the next player can start a fresh session.)
	 */
	public static void reset() {
		PlayerSession.nextLevel = GameConfig.firstLevel;
		PlayerSession.score = 0;
		PlayerSession.secondaryWeapon = 1;
		PlayerSession.credits = 0;
		PlayerSession.baseHealthPoints = GameConfig.initialPlayerHealthPoints;
		PlayerSession.baseShieldPoints = GameConfig.initialPlayerShieldPoints;
		PlayerSession.baseShotDamage = GameConfig.initialPlayerShotDamage;
		PlayerSession.baseHealthUpgadesBought = 0;
		PlayerSession.baseShieldUpgadesBought = 0;
		PlayerSession.baseShotUpgadesBought = 0;
	}

}
