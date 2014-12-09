package de.teamteamteam.spacescooter.entity.item;

import de.teamteamteam.spacescooter.entity.Player;

public class ItemRocket extends Item {
		
	public ItemRocket(int x, int y) {
		super(x, y);
		this.setImage("images/items/itemRocket.png");
	}

	@Override
	public void itemCollected(Player player) {
		player.addSecondaryWeaponAmount();
	}

}
