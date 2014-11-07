package de.teamteamteam.spacescooter.entity;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

import de.teamteamteam.spacescooter.screen.Screen;
import de.teamteamteam.spacescooter.utility.GameConfig;
import de.teamteamteam.spacescooter.utility.Loader;

/**
 * The basic Entity class where all the logic of objects begins.
 * Every Entity is Updateable and Paintable.
 * It knows about its X/Y Position and its default image.
 * Also, it is able to paint itself in the default way.
 */
public abstract class Entity implements Updateable, Paintable {
	
	/**
	 * Entity position.
	 */
	private int x;
	private int y;
	
	/**
	 * Entity width and height.
	 */
	private int width;
	private int height;
	
	/**
	 * Internal reference to the entities image.
	 */
	private BufferedImage img;
	
	
	/**
	 * Constructor.
	 * All entities are within a static array list for our convenience.
	 */
	public Entity(int x, int y) {
		this.setPosition(x, y);
		Screen.currentScreen.addEntity(this);
	}
	
	
	/**
	 * Get the X-Position of the Entity.
	 */
	public int getX() {
		return this.x;
	}

	/**
	 * Get the Y-Position of the Entity.
	 */
	public int getY() {
		return this.y;
	}
	
	/**
	 * Set the Entities new absolute position.
	 */
	public void setPosition(int x, int y) {
		this.x = x;
		this.y = y;
	}

	/**
	 * Moves the entity by the given X/Y deltas.
	 * Example: X = X + x_delta, Y = Y + y_delta.
	 */
	public void transpose(int x_delta, int y_delta) {
		this.x += x_delta;
		this.y += y_delta;
	}
	
	/**
	 * Get the Entities width.
	 */
	public int getWidth() {
		return this.width;
	}
	
	/**
	 * Get the Entities height.
	 */
	public int getHeight() {
		return this.height;
	}

	/**
	 * Set the entities width and height.
	 */
	public void setDimensions(int width, int height) {
		this.width = width;
		this.height = height;
	}
	
	/**
	 * Gets the Entities BufferedImage.
	 */
	public BufferedImage getImage() {
		return this.img;
	}

	/**
	 * Sets the Entities BufferedImage by fetching it through the Loader.
	 */
	public void setImage(String filename) {
		this.img = Loader.getBufferedImageByFilename(filename);
		//set the entities dimensions using the dimensions of the image.
		this.setDimensions(this.img.getWidth(), this.img.getHeight());
	}
	
	/**
	 * The default way to paint the Entity.
	 * Simply draw the Entities image on its current position.
	 */
	public void paint(Graphics2D g) {
		if(GameConfig.DEBUG) {
			g.setColor(new Color(255,0,0));
			g.drawRect(this.x, this.y, this.width, this.height);
		}
		g.drawImage(this.img, this.x, this.y, null);
	}
	
	/**
	 * Removes entity from the game by telling the current Screen
	 * to remove it from its list.
	 */
	public void remove() {
		Screen.currentScreen.removeEntity(this);
	}
}
