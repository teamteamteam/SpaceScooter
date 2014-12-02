package de.teamteamteam.spacescooter.entity;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

import de.teamteamteam.spacescooter.brain.GameConfig;
import de.teamteamteam.spacescooter.entity.spi.Paintable;
import de.teamteamteam.spacescooter.entity.spi.Updateable;
import de.teamteamteam.spacescooter.screen.Screen;
import de.teamteamteam.spacescooter.utility.Loader;

/**
 * The basic Entity class where all the logic of objects begins.
 * Every Entity is Updateable and Paintable.
 * It knows about its X/Y Position and its default image.
 * Also, it is able to paint itself in the default way.
 */
public abstract class Entity implements Updateable, Paintable {
	
	/**
	 * All available entity names are listed here, so the Level logic can use
	 * these to instantiate things.
	 * All the elements in this list HAVE to match their class name AND they have to be
	 * non-abstract!
	 * @see <de.teamteamteam.spacescooter.level.Level>
	 */
	public static enum availableNames {
		/* Backgrounds */
		StarBackground, CloudBackground, EarthBackground,
		/* Enemies */
		EnemyOne, EnemyTwo, EnemyThree, EnemyFour,
		/* Boss Enemies and belongings */
		EnemyBoss, EnemyBossMinion,
		/* Explosions */
		ExplosionOne, ExplosionTwo, MultiExplosion,
		/* Stones */
		StoneOne, StoneTwo, StoneThree,
		/* Items */
		ItemCredit, ItemHeal, ItemIncreaseDamage, ItemNuke, ItemOneUp, ItemRocket, ItemShield,
	}
	
	/**
	 * Entity position x coordinate.
	 */
	private int x;

	/**
	 * Entity position y coordinate.
	 */
	private int y;
	
	/**
	 * Entity width.
	 */
	private int width;
	
	/**
	 * Entity height.
	 */
	private int height;
	
	/**
	 * Whether or not the Entity is able to move using transpose.
	 */
	private boolean canMove;
	
	/**
	 * Internal reference to the entities image.
	 */
	private BufferedImage img;
	
	/**
	 * Until the Entity is disposed through remove(), this is false.
	 */
	private boolean disposed;
	
	
	/**
	 * Constructor.
	 * All entities are within a static array list for our convenience.
	 */
	public Entity(int x, int y) {
		this.disposed = false;
		this.setPosition(x, y);
		this.setCanMove(true);
		Screen.currentScreen.addEntity(this);
	}
	
	
	/**
	 * Get the X-Position of the Entity. (top left corner)
	 */
	public int getX() {
		return this.x;
	}

	/**
	 * Get the Y-Position of the Entity. (top left corner)
	 */
	public int getY() {
		return this.y;
	}
	
	/**
	 * Get the centered X-Position of the Entity.
	 */
	public int getCenteredX() {
		return this.x + (this.width / 2);
	}

	/**
	 * Get the centered Y-Position of the Entity.
	 */
	public int getCenteredY() {
		return this.y + (this.height / 2);
	}
	
	/**
	 * Set the Entities new absolute position.
	 */
	public void setPosition(int x, int y) {
		this.x = x;
		this.y = y;
	}

	/**
	 * Moves the entity by the given X/Y deltas.
	 * Example: X = X + x_delta, Y = Y + y_delta.
	 */
	public void transpose(int x_delta, int y_delta) {
		if(this.canMove) {
			this.x += x_delta;
			this.y += y_delta;
		}
	}
	
	/**
	 * Get the Entities width.
	 */
	public int getWidth() {
		return this.width;
	}
	
	/**
	 * Get the Entities height.
	 */
	public int getHeight() {
		return this.height;
	}

	/**
	 * Set the entities width and height.
	 */
	public void setDimensions(int width, int height) {
		this.width = width;
		this.height = height;
	}
	
	/**
	 * Gets the Entities BufferedImage.
	 */
	public BufferedImage getImage() {
		return this.img;
	}

	/**
	 * Sets the Entities BufferedImage by fetching it through the Loader.
	 */
	public void setImage(String filename) {
		this.img = Loader.getBufferedImageByFilename(filename);
		//set the entities dimensions using the dimensions of the image.
		this.setDimensions(this.img.getWidth(), this.img.getHeight());
	}
	
	/**
	 * Set whether the Entity is able to move.
	 */
	public void setCanMove(boolean canMove) {
		this.canMove = canMove;
	}

	/**
	 * Returns true if the Entity is able to move.
	 */
	public boolean canMove() {
		return this.canMove;
	}
	
	/**
	 * The default way to paint the Entity.
	 * Simply draw the Entities image on its current position.
	 */
	public void paint(Graphics2D g) {
		if(GameConfig.DEBUG) {
			g.setColor(new Color(255,0,0));
			g.drawRect(this.x, this.y, this.width, this.height);
		}
		g.drawImage(this.img, this.x, this.y, null);
	}
	
	/**
	 * Removes entity from the game by telling the current Screen
	 * to remove it from its list.
	 */
	public void remove() {
		if(this.disposed) {
			return;
		}
		else{
			Screen.currentScreen.removeEntity(this);
			this.disposed = true;
		}
	}
}
