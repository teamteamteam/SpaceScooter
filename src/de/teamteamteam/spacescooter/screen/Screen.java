package de.teamteamteam.spacescooter.screen;

import java.awt.Graphics2D;
import java.util.LinkedList;

import de.teamteamteam.spacescooter.entity.Entity;

public abstract class Screen {

	public static Screen currentScreen;
	
	public Screen overlay;
	protected Screen parent;
	
	protected LinkedList<Entity> entities;
	
	public Screen(Screen parent) {
		this.setOverlay(null);
		this.parent = parent;
		this.entities = new LinkedList<Entity>();
	}
	
	public void addEntity(Entity e) {
		this.entities.add(e);
	}
	
	public void removeEntity(Entity e) {
		this.entities.remove(e);
	}
	
	public LinkedList<Entity> getEntities() {
		return new LinkedList<Entity>(this.entities);
	}
	
	protected abstract void paint(Graphics2D g);
	protected abstract void update();
	
	public void doPaint(Graphics2D g) {
		this.paint(g);
		if(this.overlay != null) this.overlay.doPaint(g);
	}
	
	public void doUpdate() {
		if(this.overlay != null) {
			this.overlay.doUpdate();
		} else {
			this.update();
		}
	}

	public void setOverlay(Screen screen) {
		this.overlay = screen;
		if(screen == null) {
			Screen.currentScreen = this;
		} else {
			Screen.currentScreen = screen;
		}
	}
}
