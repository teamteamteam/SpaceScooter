package de.teamteamteam.spacescooter.gui;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;

import de.teamteamteam.spacescooter.entity.Entity;
import de.teamteamteam.spacescooter.datastructure.Score;

public class ScoreBar extends Entity {
	
	public ScoreBar(int x, int y) {
		super(x, y);
	}

	public void paint(Graphics2D g) {
		g.setColor(Color.WHITE);
		g.setFont(new Font("Monospace", 0, 24));
		g.drawString("Score:", this.getX(), this.getY());
		g.drawString(String.format("%08d", Integer.parseInt("" + Score.getScore())), this.getX()+77, this.getY());
	}
	
	public void update() {}
	
}
