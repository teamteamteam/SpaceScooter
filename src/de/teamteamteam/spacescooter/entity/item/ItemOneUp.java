package de.teamteamteam.spacescooter.entity.item;

import de.teamteamteam.spacescooter.entity.Player;

public class ItemOneUp extends Item {
	
	public ItemOneUp(int x, int y) {
		super(x, y);
		this.setImage("images/items/itemOneUp.png");
	}

	/**
	 * Since we do not actually have a concept of "lives" yet, the
	 * player gets full health restored.
	 */
	@Override
	public void itemCollected(Player player) {
		System.out.println("1 UP");
		player.setHealthPoints(player.getMaximumHealthPoints());
	}
}
