package de.teamteamteam.spacescooter.entity.obstacle;

/**
 * The first stone/meteor like object in the game!
 */
public class StoneOne extends MovingObstacle {

	public StoneOne(int x, int y) {
		super(x, y);
		this.setImage("images/stones/stone01.png");
		this.setCollisionDamage(9001);
	}

}
