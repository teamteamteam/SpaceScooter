package de.teamteamteam.spacescooter.gui;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;

import de.teamteamteam.spacescooter.entity.Entity;

public class ImageEntity extends Entity{

	private String text = null;
	private int size;
	private Color color;
	
	/**
	 * This only show an image or a String if filename is null and drawString was called. 
	 */
	public ImageEntity(int x, int y, String filename) {
		super(x, y);
		if(filename != null) setImage(filename);
	}

	/**
	 * Set the string, text size and color to paint a text.
	 */
	public void drawString(String text, int size, Color color){
		this.text = text;
		this.size = size;
		this.color = color;
	}
	
	public String getText(){
		return text;
	}
	
	/**
	 * Paint the image or paint a String if filename is null.
	 */
	@Override
	public void paint(Graphics2D g) {
		if(this.text == null) super.paint(g);
		else{
			g.setFont(new Font("Monospace", 0, size));
			g.setColor(color);
			g.drawString(text, getX(), getY());
		}
	}
	
	@Override
	public void update(){}
}
