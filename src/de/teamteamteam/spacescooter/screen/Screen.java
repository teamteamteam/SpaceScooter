package de.teamteamteam.spacescooter.screen;

import java.awt.Graphics;
import java.util.LinkedList;

import de.teamteamteam.spacescooter.entity.Entity;

public abstract class Screen {

	public static Screen currentScreen;
	
	protected Screen overlay;
	protected Screen parent;
	
	protected LinkedList<Entity> entities;
	
	public Screen(Screen parent) {
		this.overlay = null;
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
		Screen.currentScreen = screen;
	}
}
