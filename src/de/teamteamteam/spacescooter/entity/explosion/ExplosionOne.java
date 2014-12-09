package de.teamteamteam.spacescooter.entity.explosion;

import de.teamteamteam.spacescooter.entity.Animation;
import de.teamteamteam.spacescooter.sound.SoundSystem;

public class ExplosionOne extends Animation {

	public ExplosionOne(int x, int y) {
		super(x, y);
		String[] images = {
			"images/explosions/01/explosion1.png",
			"images/explosions/01/explosion2.png",
			"images/explosions/01/explosion3.png",
			"images/explosions/01/explosion4.png",
			"images/explosions/01/explosion5.png",
			"images/explosions/01/explosion6.png",
			"images/explosions/01/explosion7.png"
		};
		this.configure(images, 10);
		this.setPosition(x - (this.getImageWidth()/2), y - (this.getImageHeight()/2));
		SoundSystem.playSound("sounds/bad_explosion1.wav");
	}
}
