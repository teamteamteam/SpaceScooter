package de.teamteamteam.spacescooter.entities;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.Shape;
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
	 * Default hitbox for every entity
	 */
	protected Shape HitBox;
	
	/**
	 * Constructor.
	 * All entities are within a static array list for our convenience.
	 */
	public Entity() {
		Entity.entities.add(this);
		
		//
	}
	
	public void update() {
		
	}

	public void paint(Graphics g) {
		
	}
	
}
