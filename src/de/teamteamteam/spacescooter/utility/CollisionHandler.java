package de.teamteamteam.spacescooter.utility;

import java.awt.Rectangle;

import de.teamteamteam.spacescooter.datastructure.ConcurrentIterator;
import de.teamteamteam.spacescooter.entity.Entity;
import de.teamteamteam.spacescooter.entity.Player;
import de.teamteamteam.spacescooter.entity.enemy.Enemy;
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
		ConcurrentIterator<Entity> iteratorOne = Screen.currentScreen.getEntityIterator();
		ConcurrentIterator<Entity> iteratorTwo = Screen.currentScreen.getEntityIterator();
		while(iteratorOne.hasNext()) {
			Entity entityOne = iteratorOne.next();
			//Only check Player and Enemy for the active side of a collision.
			if(!((entityOne instanceof Player) || (entityOne instanceof Enemy))) continue;
			Collidable collidableOne = (Collidable) entityOne;
			//Loop to check collisions against entityOne.
			iteratorTwo.reset();
			while(iteratorTwo.hasNext()) {
				Entity entityTwo = iteratorTwo.next();
				//We want all Collidables on the passive side of the collision.
				if(!(entityTwo instanceof Collidable)) continue;
				Collidable collidableTwo = (Collidable) entityTwo;
				//skip checks against itself
				if(entityTwo.equals(entityOne)) continue;
				//Weed out Player and Enemy from the passive side
				if(entityTwo instanceof Player || entityTwo instanceof Enemy) continue;
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
	 * TODO: Do not use Rectangles, since there are so many created that it gets messy.
	 */
	private static boolean doCollide(Collidable cOne, Collidable cTwo) {
		Rectangle one = new Rectangle(cOne.getX() , cOne.getY(), cOne.getWidth(), cOne.getHeight());
		Rectangle two = new Rectangle(cTwo.getX() , cTwo.getY(), cTwo.getWidth(), cTwo.getHeight());
		return one.intersects(two);
	}
	
}
