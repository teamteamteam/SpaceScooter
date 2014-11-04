package de.teamteamteam.spacescooter.entity.explosion;

import de.teamteamteam.spacescooter.entity.Entity;

public class ExplosionTwo extends Entity {

	private int count = 141;
	
	public ExplosionTwo(int x, int y) {
		super(x, y);
		this.setImage("images/explosion_proto.png");
		this.setPosition(x - (this.getWidth()/2), y - (this.getHeight()/2));
	}
	
	public void update() {
		if (count >= 0) {
			count--;
		} else {
			this.remove();
		}
		switch (count) {
		case 150:
			this.setImage("images/explosion2_1.png");
			break;
		case 140:
			this.setImage("images/explosion2_2.png");
			break;
		case 130:
			this.setImage("images/explosion2_3.png");
			break;
		case 120:
			this.setImage("images/explosion2_4.png");
			break;
		case 110:
			this.setImage("images/explosion2_5.png");
			break;
		case 100:
			this.setImage("images/explosion2_6.png");
			break;
		case 90:
			this.setImage("images/explosion2_7.png");
			break;
		case 80:
			this.setImage("images/explosion2_8.png");
			break;
		case 70:
			this.setImage("images/explosion2_9.png");
			break;
		case 60:
			this.setImage("images/explosion2_10.png");
			break;
		case 50:
			this.setImage("images/explosion2_11.png");
			break;
		case 40:
			this.setImage("images/explosion2_12.png");
			break;
		case 30:
			this.setImage("images/explosion2_13.png");
			break;
		case 20: 
			this.setImage("images/explosion2_14.png");
			break;
		case 10: 
			this.setImage("images/explosion2_15.png");
			break;
		case 1:
			this.setImage("images/explosion2_16.png");
			break;
		}
	}

}
