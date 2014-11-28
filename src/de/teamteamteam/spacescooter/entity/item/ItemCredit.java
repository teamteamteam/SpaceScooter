package de.teamteamteam.spacescooter.entity.item;

import de.teamteamteam.spacescooter.brain.PlayerSession;
import de.teamteamteam.spacescooter.entity.Player;

public class ItemCredit extends Item {
	
	public ItemCredit(int x, int y) {
		super(x, y);
		this.setImage("images/items/itemCredit.png");
	}

	@Override
	public void itemCollected(Player player) {
		PlayerSession.addCredits(10);
	}
}
