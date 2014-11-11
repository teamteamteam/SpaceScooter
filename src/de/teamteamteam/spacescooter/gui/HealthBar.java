package de.teamteamteam.spacescooter.gui;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;

import de.teamteamteam.spacescooter.datastructure.ConcurrentIterator;
import de.teamteamteam.spacescooter.entity.Entity;
import de.teamteamteam.spacescooter.entity.Player;
import de.teamteamteam.spacescooter.screen.Screen;

public class HealthBar extends Entity {

	private int width = 150;
	private int height = 14;
	private int health = 0;
	
	private ConcurrentIterator<Entity> entityIterator;
	
	public HealthBar(int x, int y) {
		super(x, y);
		this.entityIterator = Screen.currentScreen.createEntityIterator();
	}

	public void paint(Graphics2D g) {
		Player player = null;
		this.entityIterator.reset();
		while(this.entityIterator.hasNext()) {
			Entity e = this.entityIterator.next();
			if(e instanceof Player){
				player = ((Player) e);
			}
		}
		this.health = ((this.width) * player.getHealthPoints());
		g.setColor(Color.WHITE);
		g.setFont(new Font("Monospace", 0, 16));
		g.drawString("Health:", this.getX(), this.getY()+12);
		if (player.getHealthPoints() <= 15) {
			g.setColor(Color.RED);
		} else if (player.getHealthPoints() <= 50) {
			g.setColor(Color.YELLOW);
		} else {
			g.setColor(Color.GREEN);
		}
		g.fillRect(this.getX()+70, this.getY(), this.health / 100, this.height);
		g.setColor(Color.WHITE);
		g.drawRect(this.getX()+70, this.getY(), this.width, this.height);
	}
	
	public void update() {}
	
}
