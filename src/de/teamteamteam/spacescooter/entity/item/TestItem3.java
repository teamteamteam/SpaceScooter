package de.teamteamteam.spacescooter.entity.item;

import de.teamteamteam.spacescooter.entity.Player;

public class TestItem3 extends Items{
	
	public static int chance = 3;
	
	public TestItem3(int x, int y) {
		super(x, y);
		this.setImage("images/items/item3.png");
	}

	@Override
	public void itemCollected(Player player) {
		player.setShootDamage(player.getShootDamage()+5);
	}
}
