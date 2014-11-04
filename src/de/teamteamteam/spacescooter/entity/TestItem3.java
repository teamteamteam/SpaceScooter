package de.teamteamteam.spacescooter.entity;

public class TestItem3 extends Items{
	
	public static int chance = 3;
	
	public TestItem3(int x, int y) {
		super(x, y);
		this.setImage("images/Item3.png");
	}

	@Override
	public void itemCollected(Player player) {
		player.setDamageValue(player.getDamageValue()+5);
	}
}
