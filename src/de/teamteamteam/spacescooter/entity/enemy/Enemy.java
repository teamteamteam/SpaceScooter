package de.teamteamteam.spacescooter.entity.enemy;

import de.teamteamteam.spacescooter.entity.ShootingEntity;
import de.teamteamteam.spacescooter.entity.shot.Shot;
import de.teamteamteam.spacescooter.sound.SoundSystem;
import de.teamteamteam.spacescooter.utility.Random;

public abstract class Enemy extends ShootingEntity {

	public Enemy(int x, int y) {
		super(x, y);
		this.name = "EnemyOne";
		this.willShoot = Random.nextBoolean();
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
	
	@Override
	public void createShot() {
		super.createShot();
		SoundSystem.playSound("sounds/shot_fired2.wav");
	}


}

