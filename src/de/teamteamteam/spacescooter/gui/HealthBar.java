package de.teamteamteam.spacescooter.gui;

import java.awt.Color;
import java.awt.Graphics2D;

import de.teamteamteam.spacescooter.datastructure.ConcurrentIterator;
import de.teamteamteam.spacescooter.entity.Entity;
import de.teamteamteam.spacescooter.entity.Player;
import de.teamteamteam.spacescooter.screen.Screen;

public class HealthBar extends Entity {

	private int width = 100;
	private int height = 24;
	
	public HealthBar(int x, int y) {
		super(x, y);
	}

	public void paint(Graphics2D g) {
		Player player = null;
		ConcurrentIterator<Entity> entities = Screen.currentScreen.getEntityIterator();
		while(entities.hasNext()) {
			Entity e = entities.next();
			if(e instanceof Player){
				player = ((Player) e);
			}
		}
		g.setColor(Color.GREEN);
		g.fillRect(this.getX(), this.getY(), (this.width / 100) * player.getHealthPoints(), this.height);
		g.setColor(Color.WHITE);
		g.drawRect(this.getX(), this.getY(), this.width, this.height);
	}
	
	public void update() {}
	
}
