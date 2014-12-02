package de.teamteamteam.spacescooter.entity.obstacle;

/**
 * Yet another stony obstacle.
 */
public class StoneTwo extends MovingObstacle {

	public StoneTwo(int x, int y) {
		super(x, y);
		this.setImage("images/stones/stone02.png");
		this.setCollisionDamage(9001);
	}

}
