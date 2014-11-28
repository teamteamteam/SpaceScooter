package de.teamteamteam.spacescooter.entity.item;

import de.teamteamteam.spacescooter.entity.Player;

public class ItemShield extends Item {
	
	public ItemShield(int x, int y) {
		super(x, y);
		this.setImage("images/items/itemShield.png");
	}

	/**
	 * Adds 5 shield points to the player in case he upgraded
	 * his ship to have a shield.
	 */
	@Override
	public void itemCollected(Player player) {
		player.addShieldPoints(5);
	}
}
