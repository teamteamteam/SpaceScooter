package de.teamteamteam.spacescooter.entity.item;

import de.teamteamteam.spacescooter.entity.Player;

public class ItemOneUp extends Item {

public static int chance = 4;
	
	public ItemOneUp(int x, int y) {
		super(x, y);
		this.setImage("images/items/itemOneUp.png");
	}

	@Override
	public void itemCollected(Player player) {
		player.setShieldPoints(player.getShieldPoints() + 5);
	}
}
