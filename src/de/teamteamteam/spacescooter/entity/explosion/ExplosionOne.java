package de.teamteamteam.spacescooter.entity.explosion;

import de.teamteamteam.spacescooter.entity.Entity;

public class ExplosionOne extends Entity {

	private int count = 71;
	
	public ExplosionOne(int x, int y) {
		super(x, y);
		this.setImage("images/explosions/explosion_proto.png");
		this.setPosition(x - (this.getWidth()/2), y - (this.getHeight()/2));
	}

	public void update() {
		if (count >= 0) {
			count--;
		} else {
			this.remove();
		}
		switch (count) {
		case 70:
			this.setImage("images/explosions/01/explosion1.png");
			break;
		case 60:
			this.setImage("images/explosions/01/explosion2.png");
			break;
		case 50:
			this.setImage("images/explosions/01/explosion3.png");
			break;
		case 40:
			this.setImage("images/explosions/01/explosion4.png");
			break;
		case 30:
			this.setImage("images/explosions/01/explosion5.png");
			break;
		case 20:
			this.setImage("images/explosions/01/explosion6.png");
			break;
		case 10:
			this.setImage("images/explosions/01/explosion7.png");
			break;
		}
	}
}
