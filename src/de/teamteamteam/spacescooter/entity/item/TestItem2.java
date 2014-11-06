package de.teamteamteam.spacescooter.entity.item;

import de.teamteamteam.spacescooter.entity.Player;

public class TestItem2 extends Items{
	
	public static int chance = 2;
	
	public TestItem2(int x, int y) {
		super(x, y);
		this.setImage("images/items/item2.png");
	}

	@Override
	public void itemCollected(Player player) {
		player.setDamageValue(player.getDamageValue()+5);
	}
}
