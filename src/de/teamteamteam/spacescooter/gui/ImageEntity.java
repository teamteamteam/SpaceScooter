package de.teamteamteam.spacescooter.gui;

import de.teamteamteam.spacescooter.entity.Entity;

public class ImageEntity extends Entity{

	/**
	 * This only show an image. 
	 */
	public ImageEntity(int x, int y, String filename) {
		super(x, y);
		setImage(filename);
	}

	public void update(){}
}
