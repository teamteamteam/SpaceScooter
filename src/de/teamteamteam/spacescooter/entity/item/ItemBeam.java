package de.teamteamteam.spacescooter.entity.item;

import de.teamteamteam.spacescooter.entity.Player;

public class ItemBeam extends Item {
		
	public ItemBeam(int x, int y) {
		super(x, y);
		this.setImage("images/items/itemBeam.png");
	}

	@Override
	public void itemCollected(Player player) {
		player.addSecondaryWeaponAmount();
	}

}
