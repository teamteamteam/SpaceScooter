package de.teamteamteam.spacescooter.entity.shot;

import de.teamteamteam.spacescooter.entity.spi.Collidable;

public class Beam extends Shot {

	private int i;
	
	public Beam(int x, int y, int shootDirection, int shootSpeed, int damageValue, String filename) {
		super(x, y-35, shootDirection, shootSpeed, damageValue, filename);
		this.setImage("images/shots/beam.png");
		this.i = 0;
	}
	
	@Override
	public void update() {
		this.i++;
		if(this.i>1){
			this.remove();
		}
	}
	
	@Override
	public void collideWith(Collidable entity) {}
}
