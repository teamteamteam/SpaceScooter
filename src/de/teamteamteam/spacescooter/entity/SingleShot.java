package de.teamteamteam.spacescooter.entity;

public class SingleShot extends Shot {

	public SingleShot(int x, int y, int shootDirection, int shootSpeed, int damageValue) {
		super(x, y, shootDirection, shootSpeed, damageValue);
		this.setImage("images/shot.png");
	}
}
