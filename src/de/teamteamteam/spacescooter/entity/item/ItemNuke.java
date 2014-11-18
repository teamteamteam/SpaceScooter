package de.teamteamteam.spacescooter.entity.item;

import de.teamteamteam.spacescooter.entity.Player;

public class ItemNuke extends Item {

public static int chance = 2;
	
	public ItemNuke(int x, int y) {
		super(x, y);
		this.setImage("images/items/itemNuke.png");
	}

	@Override
	public void itemCollected(Player player) {
		System.out.println("Gotta Nuke 'em All!");
	}

}
