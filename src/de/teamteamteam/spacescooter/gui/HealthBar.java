package de.teamteamteam.spacescooter.gui;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;

import de.teamteamteam.spacescooter.entity.Entity;
import de.teamteamteam.spacescooter.entity.Player;
import de.teamteamteam.spacescooter.screen.GameScreen;

public class HealthBar extends Entity {

	private int width = 150;
	private int height = 14;
	private int health = 0;
	private int healthwidth = 0;
	
	public HealthBar(int x, int y) {
		super(x, y);
	}

	public void paint(Graphics2D g) {
		Player player = GameScreen.getPlayer();
		try {
			this.health = player.getHealthPercentage();
			this.healthwidth = ((this.width) * this.health) / 100;
		} catch(Exception e) {
			this.healthwidth = 0;
		}
		g.setColor(Color.WHITE);
		g.setFont(new Font("Monospace", 0, 16));
		g.drawString("Health:", this.getX(), this.getY()+12);
		if (this.health <= 15) {
			g.setColor(Color.RED);
		} else if (this.health <= 50) {
			g.setColor(Color.YELLOW);
		} else {
			g.setColor(Color.GREEN);
		}
		g.fillRect(this.getX()+70, this.getY(), this.healthwidth, this.height);
		g.setColor(Color.WHITE);
		g.drawRect(this.getX()+70, this.getY(), this.width, this.height);
	}
	
	public void update() {}
	
}
