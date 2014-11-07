package de.teamteamteam.spacescooter.entity.item;

import de.teamteamteam.spacescooter.entity.Player;

public class TestItem4 extends Items{
	
	public static int chance = 4;
	
	public TestItem4(int x, int y) {
		super(x, y);
		this.setImage("images/items/item4.png");
	}

	@Override
	public void itemCollected(Player player) {
		player.setShootDamage(player.getShootDamage()+5);
	}
}
