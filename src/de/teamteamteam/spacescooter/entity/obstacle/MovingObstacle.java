package de.teamteamteam.spacescooter.entity.obstacle;

public abstract class MovingObstacle extends Obstacle {

	/**
	 * Delta the Obstacle moves at on X-Axis.
	 * Defaults to -1.
	 */
	private int xDelta;
	
	/**
	 * Delta the Obstacle moves at on Y-Axis.
	 * Defaults to 0.
	 */
	private int yDelta;
	
	
	/**
	 * Default constructor.
	 */
	public MovingObstacle(int x, int y) {
		super(x, y);
		this.setMoveDeltas(-1, 0);
	}

	/**
	 * Make the Obstacle move at its defined X- and Y-Delta.
	 */
	public void update() {
		this.transpose(this.xDelta, this.yDelta);
	}

	/**
	 * Set the deltas used to move this Obstacle.
	 */
	public void setMoveDeltas(int xDelta, int yDelta) {
		this.xDelta = xDelta;
		this.yDelta = yDelta;
	}
	
}
