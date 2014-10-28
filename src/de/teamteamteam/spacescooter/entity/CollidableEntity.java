package de.teamteamteam.spacescooter.entity;

import java.awt.Rectangle;
import java.util.LinkedList;

import de.teamteamteam.spacescooter.screen.Screen;

public abstract class CollidableEntity extends Entity implements Collidable{

	public CollidableEntity(int x, int y) {
		super(x, y);
	}

	public Rectangle getCollisionBox() {
		return new Rectangle(this.getX(), this.getY(), this.getWidth(), this.getHeight());
	}
	
	public void update() {
		LinkedList<Entity> entities = Screen.currentScreen.getEntities();
		for(Entity e : entities) {
			if(e.equals(this)) continue;
			if(!(e instanceof Collidable)) continue;
			CollidableEntity ce = (CollidableEntity) e;
			if(ce.getCollisionBox().intersects(this.getCollisionBox())) {
				this.collideWith(ce);
			}
		}
	}
	
}
