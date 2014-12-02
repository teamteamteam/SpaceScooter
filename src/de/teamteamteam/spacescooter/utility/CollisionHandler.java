package de.teamteamteam.spacescooter.utility;

import de.teamteamteam.spacescooter.datastructure.ConcurrentIterator;
import de.teamteamteam.spacescooter.entity.Entity;
import de.teamteamteam.spacescooter.entity.Player;
import de.teamteamteam.spacescooter.entity.enemy.Enemy;
import de.teamteamteam.spacescooter.entity.item.Item;
import de.teamteamteam.spacescooter.entity.shot.Shot;
import de.teamteamteam.spacescooter.entity.spi.Collidable;
import de.teamteamteam.spacescooter.screen.Screen;

/**
 * This class contains helper methods to handle collisions efficiently.
 */
public class CollisionHandler {

	/**
	 * Private constructor, this class will not be instantiated.
	 */
	private CollisionHandler() {}
	
	
	/**
	 * Use a list of entities to filter out the ones that can collide with each other,
	 * then intelligently check them against each other to reduce the amount of checks.
	 */
	public static void handleCollisions() {
		ConcurrentIterator<Entity> iteratorOne = Screen.currentScreen.getCollisionIteratorOne();
		ConcurrentIterator<Entity> iteratorTwo = Screen.currentScreen.getCollisionIteratorTwo();
		iteratorOne.reset();
		while(iteratorOne.hasNext()) {
			Entity entityOne = iteratorOne.next();
			if(!(entityOne instanceof Collidable)) continue;
			Collidable collidableOne = (Collidable) entityOne;
			if(!collidableOne.canCollide()) continue;
			//Loop to check collisions against entityOne.
			iteratorTwo.reset();
			while(iteratorTwo.hasNext()) {
				Entity entityTwo = iteratorTwo.next();
				if(!(entityTwo instanceof Collidable)) continue;
				//Ignore certain combinations at all:
				if(entityOne instanceof Enemy && entityTwo instanceof Enemy) continue;
				if(entityOne instanceof Player && entityTwo instanceof Player) continue;
				if(entityOne instanceof Shot && entityTwo instanceof Shot) continue;
				if(entityOne instanceof Item && entityTwo instanceof Shot) continue;
				if(entityOne instanceof Shot && entityTwo instanceof Item) continue;
				Collidable collidableTwo = (Collidable) entityTwo;
				if(!collidableTwo.canCollide()) continue;
				//skip checks against itself
				if(entityTwo.equals(entityOne)) continue;
				//check for the actual collision
				if(CollisionHandler.doCollide(collidableOne, collidableTwo)) {
					CollisionHandler.handleCollision(collidableOne, collidableTwo);
				}
			}
		}
	}

	/**
	 * Handle the collision between the two given Collidables by telling each
	 * of them, who it collided with.
	 */
	private static void handleCollision(Collidable collidableOne,
			Collidable collidableTwo) {
		collidableOne.collideWith(collidableTwo);
		collidableTwo.collideWith(collidableOne);
	}

	/**
	 * Check if two Collidables actually collided.
	 */
	private static boolean doCollide(Collidable cOne, Collidable cTwo) {
		int x1 = cOne.getX();
		int x2 = cOne.getX() + cOne.getWidth();
		int x3 = cTwo.getX();
		int x4 = cTwo.getX() + cTwo.getWidth();
		int total_width = cOne.getWidth() + cTwo.getWidth();
		boolean x_overlap = total_width > Math.abs(Math.max(x2, x4) - Math.min(x1, x3));
		
		int y1 = cOne.getY();
		int y2 = cOne.getY() + cOne.getHeight();
		int y3 = cTwo.getY();
		int y4 = cTwo.getY() + cTwo.getHeight();
		int total_height = cOne.getHeight() + cTwo.getHeight();
		boolean y_overlap = total_height > Math.abs(Math.max(y2, y4) - Math.min(y1, y3));
		
		return x_overlap && y_overlap;
	}
	
}
