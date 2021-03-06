package de.teamteamteam.spacescooter.screen;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;

import de.teamteamteam.spacescooter.brain.GameConfig;

/**
 * This is the LoadingScreen, which is displayed when the game is started.
 * It vanishes when all available resources have been processed by the loader,
 * showing the MainMenuScreen.
 */
public class LoadingScreen extends Screen {

	/**
	 * Number of by the Loader processed (loaded) elements.
	 */
	private int currentProcessedElements;

	/**
	 * Total number of elements to be processed by the Loader.
	 */
	private int totalProcessableElements;
	
	/**
	 * Whether or not the LoadingScreen has been initialized - 
	 * aka. it knows the totalProcessableElements.
	 */
	private boolean initialized;
	
	
	/**
	 * Constructor initializing sane defaults for both the element count attributes
	 * so the Screen can safely be painted and updated.
	 */
	public LoadingScreen(Screen parent) {
		super(parent);
		this.initialized = false;
		this.currentProcessedElements = 0;
		this.totalProcessableElements = 1; //sane default
	}
	
	/**
	 * Initialize the LoadingScreen with these two element counters.
	 * This way, the LoadingScreen is able to indicate a progress.
	 */
	public void initialize(int currentProcessed, int totalProcessable) {
		this.currentProcessedElements = currentProcessed;
		this.totalProcessableElements = totalProcessable;
		this.initialized = true;
	}
	
	/**
	 * Tell the LoadingScreen that an element has been processed.
	 */
	public void increaseCurrentProcessed() {
		this.currentProcessedElements++;
	}
	
	/**
	 * Calculate the percentage of the loading progress.
	 */
	public int getProgress() {
		return (int) Math.floor((100.0 * this.currentProcessedElements) / this.totalProcessableElements);
	}
	
	/**
	 * Draw the LoadingScreen. :-)
	 */
	@Override
	protected void paint(Graphics2D g) {
		g.setColor(new Color(0,0,120));
		g.fillRect(0, 0, GameConfig.windowWidth, GameConfig.windowHeight);
		g.setColor(Color.WHITE);
		g.setFont(new Font("Monospace", 0, 50));
		String text = "Loading ...";
		g.drawString(text, (GameConfig.windowWidth - g.getFontMetrics().stringWidth(text))/2, 150);
		text = this.getProgress() + "%";
		g.drawString(text, (GameConfig.windowWidth - g.getFontMetrics().stringWidth(text))/2, GameConfig.windowHeight - 175);
		int height = 75;
		int width = GameConfig.windowWidth - 100;
		int x = (GameConfig.windowWidth - width) / 2;
		int y = GameConfig.windowHeight - 150;
		int padding = 5;
		g.drawRect(x - padding , y - padding, width + 2*padding, height + 2*padding);
		g.setColor(Color.YELLOW);
		g.fillRect(x, y, (int) (width * (this.getProgress() / 100.0)), height);
	}

	/**
	 * In case the Loader is done, immediately fire up the MainMenuScreen.
	 */
	@Override
	protected void update() {
		if(this.initialized == true && this.getProgress() == 100) {
			this.parent.setOverlay(new MainMenuScreen(this.parent));
		}
	}

}
