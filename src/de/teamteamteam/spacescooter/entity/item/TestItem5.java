package de.teamteamteam.spacescooter.entity.item;

import de.teamteamteam.spacescooter.entity.Player;

public class TestItem5 extends Item {

public static int chance = 2;
	
	public TestItem5(int x, int y) {
		super(x, y);
		this.setImage("images/items/item5.png");
	}

	@Override
	public void itemCollected(Player player) {
		
	}

}
