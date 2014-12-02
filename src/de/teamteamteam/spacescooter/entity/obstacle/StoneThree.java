package de.teamteamteam.spacescooter.entity.obstacle;

/**
 * Yet another stony obstacle.
 */
public class StoneThree extends MovingObstacle {

	public StoneThree(int x, int y) {
		super(x, y);
		this.setImage("images/stones/stone03.png");
		this.setCollisionDamage(9001);
	}

}
