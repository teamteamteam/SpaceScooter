package de.teamteamteam.spacescooter.entity.item;

import de.teamteamteam.spacescooter.entity.Player;

public class ItemIncreaseDamage extends Item {
		
	public ItemIncreaseDamage(int x, int y) {
		super(x, y);
		//TODO: Chane Image
		this.setImage("images/items/item.png");
	}

	@Override
	public void itemCollected(Player player) {
		player.setShootDamage(player.getShootDamage()+5);
	}

}
