package de.teamteamteam.spacescooter.entities;

import java.util.ArrayList;

public abstract class Entity implements Updateable, Paintable {
	
	public static ArrayList<Entity> entities = null;
	private static boolean isEnemy = false;
	
	/**
	 * We need to initialize the ArrayList, so the EntityUpdateThread won't beat us.
	 */
	static {
		Entity.entities = new ArrayList<Entity>();
	}

	
	public static boolean isEnemy() {
		return isEnemy;
	}

	protected void setEnemy(boolean isEnemy) {
		this.isEnemy = isEnemy;
	}
	
	/**
	 * Constructor.
	 * All entities are within a static array list for our convenience.
	 */
	public Entity() {
		Entity.entities.add(this);
	}
	
}
