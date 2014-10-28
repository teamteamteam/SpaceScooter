package de.teamteamteam.spacescooter.entity;

import java.awt.Color;
import java.awt.Graphics;

public class TestEntity extends Entity {

	private Color color;

	public int x;
	public int y;
	
	public TestEntity() {
		super();
		this.color = new Color(255, 0, 0);
	}

	public void paint(Graphics g) {
		g.setColor(this.color);
		g.fillRect(300, 300, 10, 10);
	}

	public void update() {
		
	}

}
