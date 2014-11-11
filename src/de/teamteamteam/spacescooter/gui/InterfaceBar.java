package de.teamteamteam.spacescooter.gui;

import java.awt.Color;
import java.awt.Graphics2D;

import de.teamteamteam.spacescooter.entity.Entity;;

public class InterfaceBar extends Entity {

	private int width = 800;
	private int height = 50;
	
	public InterfaceBar(int x, int y) {
		super(x, y);
	}

	public void paint(Graphics2D g) {
		g.setColor(Color.BLACK);
		g.fillRect(this.getX(), this.getY(), this.width, this.height);
	}
	
	public void update() {}
	
}
