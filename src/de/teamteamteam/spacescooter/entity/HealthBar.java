package de.teamteamteam.spacescooter.entity;

import java.awt.Color;
import java.awt.Graphics2D;
import java.util.List;

import de.teamteamteam.spacescooter.screen.Screen;

public class HealthBar extends Entity {

	private int width = 100;
	private int height = 20;
	private Graphics2D g;
	
	public HealthBar(int x, int y) {
		super(x, y);
	
	}

	public void paint(Graphics2D g) {
		List<Entity> entities = Screen.currentScreen.getEntities();
		for (Entity e : entities) {
			if(e instanceof Player){
				this.width = ((Player) e).getHealthPoints();
			}
		}
		Graphics2D grpahic = (Graphics2D) g;
		this.g = grpahic;
		this.g.setColor(new Color(0,255,0));
		this.g.fillRect(this.x, this.y, this.width, this.height);
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
