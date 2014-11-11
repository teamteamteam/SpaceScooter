package de.teamteamteam.spacescooter.gui;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;

import de.teamteamteam.spacescooter.entity.Entity;
import de.teamteamteam.spacescooter.entity.Player;
import de.teamteamteam.spacescooter.screen.GameScreen;

public class ShieldBar extends Entity {

	private int width = 150;
	private int height = 14;
	private int shield = 0;
	private int shieldwidth = 0;
	
	public ShieldBar(int x, int y) {
		super(x, y);
	}

	public void paint(Graphics2D g) {
		Player player = GameScreen.getPlayer();
		try {
			this.shield = player.getShieldPercent();
			this.shieldwidth = ((this.width) * this.shield) / 100;
		} catch(Exception e) {
			this.shieldwidth = 0;
		}
		g.setColor(Color.WHITE);
		g.setFont(new Font("Monospace", 0, 16));
		g.drawString("Shield:", this.getX(), this.getY()+12);
		g.setColor(Color.BLUE);
		g.fillRect(this.getX()+70, this.getY(), this.shieldwidth, this.height);
		g.setColor(Color.WHITE);
		g.drawRect(this.getX()+70, this.getY(), this.width, this.height);
	}
	
	public void update() {}
	
}
