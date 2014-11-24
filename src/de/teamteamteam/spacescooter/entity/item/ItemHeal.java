package de.teamteamteam.spacescooter.entity.item;

import de.teamteamteam.spacescooter.entity.Player;

public class ItemHeal extends Item {
	
	public ItemHeal(int x, int y) {
		super(x, y);
		this.setImage("images/items/itemHeal.png");
	}

	@Override
	public void itemCollected(Player player) {
		player.increaseHealthPoints(15);
	}
}
