package de.teamteamteam.spacescooter.screen;

import java.awt.Graphics2D;

import de.teamteamteam.spacescooter.datastructure.ConcurrentIterator;
import de.teamteamteam.spacescooter.datastructure.ConcurrentLinkedList;
import de.teamteamteam.spacescooter.entity.Entity;

/**
 * A Screen virtually represents a context of the game, let it be a Menu, the
 * Game itself, or even a "Game paused"-overlay.
 * 
 * The SuperScreen, which always exists, is hooked up into the GameFrame and the
 * UpdateThread, and contains the "Main" active Screen as an overlay.
 */
public abstract class Screen {

	/**
	 * Static way to access the current active Screen
	 */
	public static Screen currentScreen;

	/**
	 * Overlay of the current Screen
	 */
	public Screen overlay;

	/**
	 * Parent Screen of the current Screen
	 */
	protected Screen parent;

	/**
	 * List of entities this screen is taking care of
	 */
	private ConcurrentLinkedList<Entity> entities;
	
	
	/**
	 * Initialize parent, overlay and the Entity list
	 */
	public Screen(Screen parent) {
		this.setOverlay(null);
		this.parent = parent;
		this.entities = new ConcurrentLinkedList<Entity>();
	}
	
	
	/**
	 * Method for each Screen to implement. Does the actual painting.
	 */
	protected abstract void paint(Graphics2D g);

	/**
	 * Method for each Screen to implement. Does the actual updating.
	 */
	protected abstract void update();

	/**
	 * Add an Entity to this Screen, so it will be updated and drawn.
	 */
	public final void addEntity(Entity e) {
		this.entities.add(e);
	}

	/**
	 * Remove an Entity from this Screen.
	 */
	public final void removeEntity(Entity e) {
		this.entities.remove(e);
	}

	/**
	 * Get an Iterator over the Entity List.
	 * Use this within update method context!
	 */
	public final ConcurrentIterator<Entity> getEntityIterator() {
		return this.entities.iterator();
	}
	
	/**
	 * This gets called by the PaintThread. It takes care of the painting order,
	 * so an overlay Screen is actually in front of its parent Screen.
	 */
	public final void doPaint(Graphics2D g) {
		this.paint(g);
		if(this.overlay != null) {
			this.overlay.doPaint(g);
		}
	}

	/**
	 * This gets called by the UpdateThread. It determines whose update() method
	 * will be called, so an overlay Screen stops the parent Screen from being
	 * updated.
	 */
	public final void doUpdate() {
		if(this.overlay != null) {
			this.overlay.doUpdate();
		} else {
			this.update();
		}
	}

	/**
	 * Sets the overlay Screen for the current Screen. In case an overlay is
	 * being replaced, the old overlays cleanup-method is called. Also, this
	 * takes care of the static currentScreen value, which is accessible for
	 * everybody.
	 */
	public final void setOverlay(Screen screen) {
		if(this.overlay != null) this.overlay.cleanup();
		if(screen == null) {
			Screen.currentScreen = this;
		} else {
			Screen.currentScreen = screen;
		}
		this.overlay = screen;
	}

	/**
	 * When a Screens life ends, because it is removed, this method will take
	 * care of existing overlays and remove all references to existing entities.
	 */
	private final void cleanup() {
		if(this.overlay != null) this.overlay.cleanup();
		//tell all entities to cleanup themselves.
		ConcurrentIterator<Entity> i = this.getEntityIterator();
		while(i.hasNext()) {
			Entity e = i.next();
			e.remove();
		}
	}
}
