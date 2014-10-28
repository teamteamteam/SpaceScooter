package de.teamteamteam.spacescooter.entity;

import java.awt.Rectangle;
import java.util.LinkedList;

import de.teamteamteam.spacescooter.screen.Screen;

public abstract class CollidableEntity extends LivingEntity implements Collidable{

	private int collisionDamage;
	
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
	
	/**
	 * Handle collisions based on what we collide with.
	 */
	public void collideWith(Collidable entity) {
		if(entity instanceof Shot) {
			Shot s = (Shot) entity;
			this.takeDamage(s.getDamageValue());
		}
		if(entity instanceof Enemy) {
			//We take the collision damage from the enemy
			Enemy enemy = (Enemy) entity;
			this.takeDamage(enemy.getCollisionDamage());
		}
		if(entity instanceof Player) {
			//We take the collision damage from the enemy
			Player player = (Player) entity;
			this.takeDamage(player.getCollisionDamage());
		}
	}

	
	public void setCollisionDamage(int d) {
		this.collisionDamage = d;
	}
	
	public int getCollisionDamage() {
		return this.collisionDamage;
	}
}
