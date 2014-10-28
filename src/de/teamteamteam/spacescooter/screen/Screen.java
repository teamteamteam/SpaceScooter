package de.teamteamteam.spacescooter.screen;

import java.awt.Graphics;
import java.util.ArrayList;

import de.teamteamteam.spacescooter.entity.Entity;

public abstract class Screen {

	protected Screen overlay;
	protected Screen parent;
	
	protected ArrayList<Entity> entities;
	
	public Screen(Screen parent) {
		this.overlay = null;
		this.parent = parent;
		this.entities = new ArrayList<Entity>();
	}
	
	protected void addEntity(Entity e) {
		this.entities.add(e);
	}
	
	protected void removeEntity(Entity e) {
		this.entities.remove(e);
	}
	
	protected abstract void paint(Graphics g);
	protected abstract void update();
	
	public void doPaint(Graphics g) {
		this.paint(g);
		if(this.overlay != null) this.overlay.doPaint(g);
	}
	
	public void doUpdate() {
		if(this.overlay != null) {
			this.overlay.doUpdate();
			return;
		}
		this.update();
	}

	public void setOverlay(Screen screen) {
		this.overlay = screen;
	}
}
