package de.teamteamteam.spacescooter.entity.explosion;

import de.teamteamteam.spacescooter.entity.Animation;

public class ExplosionTwo extends Animation {

	public ExplosionTwo(int x, int y) {
		super(x, y);
		String[] images = {
			"images/explosions/02/explosion2_1.png",
			"images/explosions/02/explosion2_2.png",
			"images/explosions/02/explosion2_3.png",
			"images/explosions/02/explosion2_4.png",
			"images/explosions/02/explosion2_5.png",
			"images/explosions/02/explosion2_6.png",
			"images/explosions/02/explosion2_7.png",
			"images/explosions/02/explosion2_8.png",
			"images/explosions/02/explosion2_9.png",
			"images/explosions/02/explosion2_10.png",
			"images/explosions/02/explosion2_11.png",
			"images/explosions/02/explosion2_12.png",
			"images/explosions/02/explosion2_13.png",
			"images/explosions/02/explosion2_14.png",
			"images/explosions/02/explosion2_15.png",
			"images/explosions/02/explosion2_16.png"
		};
		this.configure(images, 10);
		this.setPosition(x - (this.getWidth()/2), y - (this.getHeight()/2));
	}

}
