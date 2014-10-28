package de.teamteamteam.spacescooter.background;

import java.util.ArrayList;

import de.teamteamteam.spacescooter.entity.Entity;

public abstract class Background extends Entity {

	public static ArrayList<Background> backgrounds = null;
	
	/**
	 * We need to initialize the ArrayList, so the EntityUpdateThread won't beat us.
	 */
	static {
		Background.backgrounds = new ArrayList<Background>();
	}
	
	public Background(int x, int y) {
		super(x, y);
		Background.backgrounds.add(this);
	}
	
}
