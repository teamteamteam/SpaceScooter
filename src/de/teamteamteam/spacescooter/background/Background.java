package de.teamteamteam.spacescooter.background;

import java.util.ArrayList;

import de.teamteamteam.spacescooter.entities.Entity;

public abstract class Background extends Entity {

	public static ArrayList<Background> backgrounds = null;
	
	/**
	 * We need to initialize the ArrayList, so the EntityUpdateThread won't beat us.
	 */
	static {
		Background.backgrounds = new ArrayList<Background>();
	}
	
	public Background() {
		Background.backgrounds.add(this);
	}
	
}
