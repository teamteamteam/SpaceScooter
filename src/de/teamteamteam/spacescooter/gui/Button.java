package de.teamteamteam.spacescooter.gui;

import de.teamteamteam.spacescooter.entity.Entity;

/**
 * This entity represents a simple button, as seen in all the menu screens.
 */
public class Button extends Entity {

	
	public Button(int x, int y) {
		super(x, y);
		this.setImage("images/button.png");
	}

	public void update() {
	}


}
