package de.teamteamteam.spacescooter.entity.shot;

import de.teamteamteam.spacescooter.entity.spi.Collidable;

public class Beam extends Shot{

	int i =0;
	
	public Beam(int x, int y, int shootDirection, int shootSpeed, int damageValue, String filename) {
		super(x, y-35, shootDirection, shootSpeed, damageValue, filename);
		this.setImage("images/shots/beam.png");
	}
	
	@Override
	public void update() {
		i++;
		if(i>10){
			this.remove();
		}
	}
	
	@Override
	public void collideWith(Collidable entity) {
	}
}
