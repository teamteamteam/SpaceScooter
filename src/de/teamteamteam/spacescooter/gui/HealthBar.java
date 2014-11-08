package de.teamteamteam.spacescooter.gui;

import java.awt.Color;
import java.awt.Graphics2D;

import de.teamteamteam.spacescooter.datastructure.ConcurrentIterator;
import de.teamteamteam.spacescooter.entity.Entity;
import de.teamteamteam.spacescooter.entity.Player;
import de.teamteamteam.spacescooter.screen.Screen;

public class HealthBar extends Entity {

	private int width = 100;
	private int height = 20;
	private Graphics2D g;
	
	public HealthBar(int x, int y) {
		super(x, y);
	
	}

	public void paint(Graphics2D g) {
		ConcurrentIterator<Entity> entities = Screen.currentScreen.getEntityIterator();
		while(entities.hasNext()) {
			Entity e = entities.next();
			if(e instanceof Player){
				this.width = ((Player) e).getHealthPoints();
			}
		}
		Graphics2D grpahic = (Graphics2D) g;
		this.g = grpahic;
		this.g.setColor(new Color(0,255,0));
		this.g.fillRect(this.getX(), this.getY(), this.width, this.height);
	}
	
	public void update() {
	}
	
	public int getWidth() {
		return 0;
	}
	
	public int getHeight() {
		return 0;
	}
}
