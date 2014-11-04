package de.teamteamteam.spacescooter.entity;

public class SingleBlueShot extends Shot {

	public SingleBlueShot(int x, int y, int shootDirection, int shootSpeed, int damageValue) {
		super(x, y, shootDirection, shootSpeed, damageValue);
		this.setImage("images/shot02.png");
	}
}
