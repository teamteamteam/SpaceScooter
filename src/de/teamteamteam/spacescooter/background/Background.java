package de.teamteamteam.spacescooter.background;

import java.awt.Graphics;
import java.util.ArrayList;

public abstract class Background {

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
	
	public void paint(Graphics g) {
		
	}
	
	public void update() {
		//scroll shit
	}
	
}
