package de.teamteamteam.spacescooter.entity.item;

import de.teamteamteam.spacescooter.entity.Player;

public class ItemIncreaseDamage extends Item {
		
	public ItemIncreaseDamage(int x, int y) {
		super(x, y);
		this.setImage("images/items/itemShotPowerUp.png");
	}

	/**
	 * Increase shoot damage of Player if not above 25.
	 */
	@Override
	public void itemCollected(Player player) {
		if(player.getShootDamage() > 25) {
			return;
		} else {
			player.setShootDamage(player.getShootDamage()+5);
		}
	}

}
