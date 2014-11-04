package de.teamteamteam.spacescooter.entity;

import java.awt.Color;
import java.awt.Graphics2D;

public class HealthBar extends Entity {

	private int width = 100;
	private int height = 20;
	private Graphics2D g;
	
	public HealthBar(int x, int y) {
		super(x, y);
	
	}

	public void paint(Graphics2D g) {
		this.g = g;
		this.g.setColor(new Color(0,255,0));
		this.g.fillRect(this.x, this.y, this.width, this.height);
	}
	
	public void update() {
		//this.g.setColor(new Color(0,0,255));
	}
	
	public int getWidth() {
		return 0;
	}
	
	public int getHeight() {
		return 0;
	}
}
