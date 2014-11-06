package de.teamteamteam.spacescooter.entity.item;

import de.teamteamteam.spacescooter.entity.Player;

public class TestItem1 extends Items{
		
	public TestItem1(int x, int y) {
		super(x, y);
		this.setImage("images/items/item.png");
	}

	@Override
	public void itemCollected(Player player) {
		player.setDamageValue(player.getDamageValue()+5);
	}
}
