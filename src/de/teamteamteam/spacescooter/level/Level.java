package de.teamteamteam.spacescooter.level;

import de.teamteamteam.spacescooter.utility.Loader;

public final class Level {

	/**
	 * Internal LevelConfig containing all the details about how this Level will manage GamePlay.
	 */
	private LevelConfig config;


	/**
	 * Constructor creating a LevelConfig based on a given config file.
	 */
	public Level(String levelConfig) {
		this.config = Loader.getLevelConfigByFilename(levelConfig);
		System.out.println(this.config);
	}
	
	
	/**
	 * The magic will happen in here.
	 * Each time the Level will receive its updateTick,
	 * it will do some checks, increase an internal counter and do
	 * evil stuff like looking up what monsters to spawn and whatever else
	 * is neccessary to torture the player.
	 */
	public void handleUpdateTick() {
		
	}
	
}
