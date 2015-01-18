package de.teamteamteam.spacescooter.entity.shot;

import de.teamteamteam.spacescooter.entity.Entity;
import de.teamteamteam.spacescooter.utility.Random;

public class BossShotWave extends Entity{

	private int lifetime = 0;
	private int rand;
	private boolean hardmode;
	
	public BossShotWave(int x, int y, boolean hardmode) {
		super(x, y);
		this.rand = Random.nextInt(2);
		this.hardmode = hardmode;
	}

	@Override
	public void update() {
		this.lifetime++;
		if(!hardmode) new BossShot(getX(), getY(), lifetime, rand);
		else{
			new BossShot(getX(), getY(), lifetime, 0);
			new BossShot(getX(), getY(), lifetime, 1);
		}
		if(lifetime == 100) this.remove();
	}

	public void positionUpdate(int y_delta) {
		this.transpose(0, y_delta);		
	}
	
	
}
