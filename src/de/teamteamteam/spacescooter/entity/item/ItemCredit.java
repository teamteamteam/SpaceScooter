package de.teamteamteam.spacescooter.entity.item;

import de.teamteamteam.spacescooter.brain.Credits;
import de.teamteamteam.spacescooter.entity.Player;

public class ItemCredit extends Item {
	
	public ItemCredit(int x, int y) {
		super(x, y);
		this.setImage("images/items/itemCredit.png");
	}

	@Override
	public void itemCollected(Player player) {
		Credits.add(10);
	}
}
