package de.teamteamteam.spacescooter.entities;

import java.awt.Graphics;
import java.util.ArrayList;

public abstract class Entity {
	
	public static ArrayList<Entity> entities = null;
	
	/**
	 * We need to initialize the ArrayList, so the EntityUpdateThread won't beat us.
	 */
	static {
		Entity.entities = new ArrayList<Entity>();
	}

	/**
	 * Constructor.
	 * All entities are within a static array list for our convenience.
	 */
	public Entity() {
		Entity.entities.add(this);
	}
	
	public abstract void update();

	public abstract void paint(Graphics g);
	
}
