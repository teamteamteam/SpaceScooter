package de.teamteamteam.spacescooter.gui;

import de.teamteamteam.spacescooter.entity.Entity;

//TODO: Create an ImageEntity to do this.
public class ShopOfferValue extends Entity{

	public ShopOfferValue(int x, int y, String filename) {
		super(x, y);
		setImage(filename);
	}

	public void update(){}
}
