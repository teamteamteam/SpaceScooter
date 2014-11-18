package de.teamteamteam.spacescooter.entity.item;

import de.teamteamteam.spacescooter.entity.Player;

public class ItemCredit extends Item {
	
	public static int chance = 2;
	
	public ItemCredit(int x, int y) {
		super(x, y);
		this.setImage("images/items/itemCredit.png");
	}

	@Override
	public void itemCollected(Player player) {
		player.setShootDamage(player.getShootDamage()+5);
	}
}
