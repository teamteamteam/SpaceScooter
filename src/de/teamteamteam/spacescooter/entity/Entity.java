package de.teamteamteam.spacescooter.entity;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

import de.teamteamteam.spacescooter.utility.GameConfig;

public abstract class Entity implements Updateable, Paintable {
	
	/**
	 * Entity position
	 */
	protected int x;
	protected int y;
	private BufferedImage img;
	
	/**
	 * Constructor.
	 * All entities are within a static array list for our convenience.
	 */
	public Entity(int x, int y) {
		this.x = x;
		this.y = y;
	}
	
	public int getX() {
		return this.x;
	}

	public int getY() {
		return this.y;
	}
	
	public int getWidth() {
		return this.img.getWidth();
	}

	public int getHeight() {
		return this.img.getHeight();
	}
	
	public void setPosition(int x, int y) {
		this.x = x;
		this.y = y;
	}
	
	public BufferedImage getImage() {
		return this.img;
	}

	public void setImage(BufferedImage img) {
		this.img = img;
	}
	
	public void paint(Graphics2D g) {
		//DEBUG ONLY
		if(GameConfig.DEBUG) {
			g.setColor(new Color(255,0,0));
			g.drawRect(this.x, this.y, this.getWidth(), this.getHeight());
		}
		
		g.drawImage(this.img, this.x, this.y, null);
	}
}
