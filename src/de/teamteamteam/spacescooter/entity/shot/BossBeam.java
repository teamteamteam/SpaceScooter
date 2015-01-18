package de.teamteamteam.spacescooter.entity.shot;

import de.teamteamteam.spacescooter.entity.spi.Collidable;

public class BossBeam extends Shot{

	private int lifetime = 0;
	
	public BossBeam(int x, int y) {
		super(x-240, y, Shot.LEFT, 0, 1, "images/shots/bossBeam.png");
	}
	
	@Override
	public void update() {
		this.lifetime++;
		if(this.lifetime>100){
			this.remove();
		}
	}
	
	@Override
	public void collideWith(Collidable entity) {}

	
	public void positionUpdate(int y_delta){
		this.transpose(0, y_delta);
	}
}
