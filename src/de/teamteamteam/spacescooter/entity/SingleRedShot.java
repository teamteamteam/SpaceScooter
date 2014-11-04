package de.teamteamteam.spacescooter.entity;

public class SingleRedShot extends Shot {

	public SingleRedShot(int x, int y, int shootDirection, int shootSpeed, int damageValue) {
		super(x, y, shootDirection, shootSpeed, damageValue);
		this.setImage("images/shot04.png");
	}
}
