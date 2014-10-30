package de.teamteamteam.spacescooter.screen;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import de.teamteamteam.spacescooter.utility.GameConfig;

public class LoadingScreen extends Screen {

	private int currentProcessed;
	private int totalProcessable;
	
	public LoadingScreen(Screen parent) {
		super(parent);
		this.currentProcessed = 0;
		this.totalProcessable = 1; //sane default
	}
	
	public void initialize(int currentProcessed, int totalProcessable) {
		this.currentProcessed = currentProcessed;
		this.totalProcessable = totalProcessable;
	}
	
	public void increaseCurrentProcessed() {
		this.currentProcessed++;
	}
	
	public int getProgress() {
		return (int) Math.round((100.0 * this.currentProcessed) / this.totalProcessable);
	}
	
	@Override
	protected void paint(Graphics2D g) {
		g.setColor(new Color(0,0,120));
		g.fillRect(0, 0, GameConfig.windowWidth, GameConfig.windowHeight);
		g.setColor(new Color(255,255,255));
		g.setFont(new Font("Monospace", 0, 50));
		g.drawString("Loading ...", 100, 100);
		g.drawString("Progress: " + this.getProgress() + "%", 200, 500);
	}

	@Override
	protected void update() {
		if(this.getProgress() == 100) {
			this.parent.setOverlay(new MainMenuScreen(this.parent));
		}
	}

}
