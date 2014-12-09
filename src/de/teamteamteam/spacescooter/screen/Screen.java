package de.teamteamteam.spacescooter.screen;

import java.awt.Color;
import java.awt.Graphics2D;

import de.teamteamteam.spacescooter.brain.GameConfig;
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
	 * Temporary holder of the new Overlay, used by setOverlay() to
	 * implement the transitions before passing focus to the new overlay.
	 */
	private Screen newOverlay;
	
	/**
	 * Marker telling whether this Screen is currently processing a 
	 * setOverlay call.
	 */
	private boolean processSetOverlayCall;

	/**
	 * List of entities this screen is taking care of
	 */
	private ConcurrentLinkedList<Entity> entities;
	
	/**
	 * Internal entity Iterator instance for updating
	 */
	protected ConcurrentIterator<Entity> entityUpdateIterator;

	/**
	 * Internal entity Iterator instance for painting
	 */
	protected ConcurrentIterator<Entity> entityPaintIterator;
	
	/**
	 * Internal entity Iterator instances for collision detection
	 */
	private ConcurrentIterator<Entity> collisionIteratorOne;
	private ConcurrentIterator<Entity> collisionIteratorTwo;
	
	/**
	 * Transition state, value in [0,1]. Used to implement
	 * the fade-to-black, fade-to-screen effects.
	 * Value reflects the "visibility" of the Screen, from
	 * 0 (not visible) to 1 (visible).
	 */
	private float transitionState;
	
	/**
	 * Transition setting, telling whether a transition is currently happening
	 * and in which direction. 
	 * Values: -1 (fade to black), 0 (nothing happening), 1 (fade to screen)
	 */
	private int transitionSetting;
	
	/**
	 * Initialize parent, overlay and the Entity list
	 */
	public Screen(Screen parent) {
		this.transitionState = 1.0F;
		this.transitionSetting = 0;
		this.newOverlay = null;
		this.processSetOverlayCall = false;
		this.setOverlay(null);
		this.parent = parent;
		this.entities = new ConcurrentLinkedList<Entity>();
		this.entityUpdateIterator = this.entities.iterator();
		this.entityPaintIterator = this.entities.iterator();
		this.collisionIteratorOne = this.entities.iterator();
		this.collisionIteratorTwo = this.entities.iterator();
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
	public final ConcurrentIterator<Entity> createEntityIterator() {
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
		} else if(this.transitionState != 1.0) {
			this.paintTransition(g);
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
		} else if (this.transitionSetting != 0) {
			this.updateTransition();
		} else {
			this.update();
		}
	}

	/**
	 * Sets the overlay Screen for the current Screen.
	 * Triggers a fade out transition.
	 * After that transition, fadeOutDone() will bubble back up.
	 * The transition will only trigger if the new overlay is not null
	 * OR an existing overlay is being removed.
	 */
	public final void setOverlay(Screen screen) {
		if(screen != null || (this.overlay != null && screen == null)) {
			//Trigger the effect if we're fading to the new Screen.
			this.processSetOverlayCall = true;
			this.newOverlay = screen;
			this.fadeOut();
		} else if(this.overlay == null) {
			this.overlay = null;
			Screen.currentScreen = this;
		}
	}

	/**
	 * There are very few occasions where the transition effect is not neccessary.
	 * This overloaded method is used for this purpose. 
	 */
	public final void setOverlay(Screen screen, boolean useEffect) {
		if(useEffect == true) {
			this.setOverlay(screen);
		} else {
			if(this.overlay != null) this.overlay.cleanup();
			this.overlay = screen;
			if(screen != null) {
				Screen.currentScreen = screen;
			} else {
				Screen.currentScreen = this;
			}
		}
	}
	
	/**
	 * A screen will pass the fadeIn() effect up the most upper
	 * overlay, so the transition will be visible.
	 * After fadeIn, a call will be bubbled up to the parent, telling
	 * it that the transition has finished.
	 */
	private void fadeIn() {
		if(this.overlay != null) {
			this.overlay.fadeIn();
		} else {
			this.initializeTransition(1);
		}
	}
	
	/**
	 * A screen will pass the fadeOut() effect up the most upper
	 * overlay, so the transition will be visible.
	 * After fadeOut, a call will be bubbled up to the parent, telling
	 * it that the transition has finished.
	 */
	private void fadeOut() {
		if(this.overlay != null) {
			this.overlay.fadeOut();
		} else {
			this.initializeTransition(-1);
		}
	}
	
	/**
	 * Notification bubbling upwards telling that the fade in transition is done.
	 */
	private void fadeInDone() {
		if(this.parent != null) {
			this.parent.fadeInDone();
		}
	}
	
	/**
	 * Notification bubbling upwards telling that the fade out transition is done.
	 * In case this Screen is processing a setOverlay call, the old overlay will now receive 
	 * the cleanup() call, and the new screen will be put active with a fade in transition.
	 */
	private void fadeOutDone() {
		if(this.processSetOverlayCall) {
			if(this.overlay != null) {
				this.overlay.cleanup();
			} else {
				this.cleanup();
			}
			if(this.newOverlay == null) {
				Screen.currentScreen = this;
			} else {
				Screen.currentScreen = this.newOverlay;
			}
			this.overlay = this.newOverlay;
			this.newOverlay = null;
			this.fadeIn();
			this.processSetOverlayCall = false;
		} else {
			if(this.parent != null) {
				this.parent.fadeOutDone();
			}
		}
	}

	/**
	 * When a Screens life ends, this method will take care of existing overlays
	 * and remove all references to existing entities.
	 */
	protected void cleanup() {
		if(this.overlay != null) {
			this.overlay.cleanup();
		}
		this.entityUpdateIterator.reset();
		while(this.entityUpdateIterator.hasNext()) {
			Entity e = this.entityUpdateIterator.next();
			e.remove();
		}
	}
	
	/**
	 * Returns second collision iterator.
	 * These are used for collision detection _only_!
	 */
	public final ConcurrentIterator<Entity> getCollisionIteratorOne() {
		return this.collisionIteratorOne;
	}

	/**
	 * Returns second collision iterator.
	 * These are used for collision detection _only_!
	 */
	public final ConcurrentIterator<Entity> getCollisionIteratorTwo() {
		return this.collisionIteratorTwo;
	}
	
	/**
	 * Triggered by doUpdate().
	 * Updates the transitionState based on the current transitionSetting.
	 */
	private void updateTransition() {
		this.transitionState += (0.03F * this.transitionSetting);
		switch(this.transitionSetting) {
			case 1:
				if(this.transitionState > 1.0F) {
					this.transitionState = 1.0F;
					this.transitionSetting = 0;
					this.fadeInDone();
				}
				break;
			case -1:
				if(this.transitionState < 0.0F) {
					this.transitionState = 0.0F;
					this.transitionSetting = 0;
					this.fadeOutDone();
				}
				break;
			default:
				break;
		}
	}
	
	/**
	 * Kick off a transition.
	 * Setting tells from where to where the transition will happen.
	 */
	private void initializeTransition(int setting) {
		if(this.transitionSetting != 0 || setting == 0) return;
		switch(setting) {
			case 1:
				this.transitionState = 0.0F;
				break;
			case -1:
				this.transitionState = 1.0F;
				break;
			default:
				break;
		}
		this.transitionSetting = setting;
	}
	
	/**
	 * Paint the transition effect.
	 */
	private void paintTransition(Graphics2D g) {
		float alpha = (float) (1.0F - this.transitionState);
		g.setColor(new Color(0.0F, 0.0F, 0.0F, alpha));
		g.fillRect(0, 0, GameConfig.windowWidth, GameConfig.windowHeight);
	}

}
