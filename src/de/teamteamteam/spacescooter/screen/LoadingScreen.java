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

	private int currentProcessed;
	private int totalProcessable;
	private boolean initialized;
	
	public LoadingScreen(Screen parent) {
		super(parent);
		this.initialized = false;
		this.currentProcessed = 0;
		this.totalProcessable = 1; //sane default
	}
	
	public void initialize(int currentProcessed, int totalProcessable) {
		this.currentProcessed = currentProcessed;
		this.totalProcessable = totalProcessable;
		this.initialized = true;
	}
	
	public void increaseCurrentProcessed() {
		this.currentProcessed++;
	}
	
	public int getProgress() {
		return (int) Math.floor((100.0 * this.currentProcessed) / this.totalProcessable);
	}
	
	@Override
	protected void paint(Graphics2D g) {
		g.setColor(new Color(0,0,120));
		g.fillRect(0, 0, GameConfig.windowWidth, GameConfig.windowHeight);
		g.setColor(Color.WHITE);
		g.setFont(new Font("Monospace", 0, 50));
		g.drawString("Loading ...", 100, 100);
		g.drawString("Progress: " + this.getProgress() + "%", 200, 500);
	}

	@Override
	protected void update() {
		if(this.initialized == true && this.getProgress() == 100) {
			this.parent.setOverlay(new MainMenuScreen(this.parent));
		}
	}

}
