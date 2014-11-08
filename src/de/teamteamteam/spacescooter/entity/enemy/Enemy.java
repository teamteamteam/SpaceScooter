package de.teamteamteam.spacescooter.entity.enemy;
import java.util.Random;

import de.teamteamteam.spacescooter.entity.ShootingEntity;
import de.teamteamteam.spacescooter.entity.shot.Shot;

public abstract class Enemy extends ShootingEntity {

	public Enemy(int x, int y) {
		super(x, y);
		Random r = new Random();
		this.name = "EnemyOne";
		this.willShoot = r.nextBoolean();
		this.setShootDirection(Shot.LEFT);
		this.setShootDamage(5);
	}

	protected String name;
	protected boolean willShoot;
	
	public void update() {
		super.update();
		if(willShoot == true)
			this.shoot();
	}


}

