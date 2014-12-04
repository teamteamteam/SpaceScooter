package de.teamteamteam.spacescooter.entity.shot;

import de.teamteamteam.spacescooter.entity.Entity;

public class BeamImage extends Entity {

	private int lifetime = 0;
	
	
	/**
	 *BeamImage show the image of the beam for a short time, without causing damage.
	 */
	public BeamImage(int x, int y) {
		super(x, y);
		this.setImage("images/shots/beam.png");
	}

	@Override
	public void update() {
		this.lifetime++;
		if(this.lifetime>30){
			this.remove();
		}
	}

}
