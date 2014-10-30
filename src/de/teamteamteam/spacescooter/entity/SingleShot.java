package de.teamteamteam.spacescooter.entity;

public class SingleShot extends Shot {

	public SingleShot(int x, int y, int shootDirection, int shootSpeed) {
		super(x, y, shootDirection, shootSpeed);
		this.setImage("images/shot.png");
		this.setDamageValue(5);
	}

}
