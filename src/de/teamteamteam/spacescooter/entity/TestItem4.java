package de.teamteamteam.spacescooter.entity;

public class TestItem4 extends Items{
	
	public static int chance = 4;
	
	public TestItem4(int x, int y) {
		super(x, y);
		this.setImage("images/Item4.png");
	}

	@Override
	public void itemCollected(Player player) {
		player.setDamageValue(player.getDamageValue()+5);
	}
}
