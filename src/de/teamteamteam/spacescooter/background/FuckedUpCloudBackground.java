package de.teamteamteam.spacescooter.background;

import java.awt.Graphics2D;

import de.teamteamteam.spacescooter.brain.GameConfig;

public class FuckedUpCloudBackground extends ScrollingBackground {

	private double x;
	/*private double x_delta;
	private int percentage;
	private int timer;*/
	
	public FuckedUpCloudBackground(int x, int y) {
		super(x, y);
		this.setImage("images/cloudbackground.png");
		this.setScrollingSpeed(-1);
	}
	
	public void paint(Graphics2D g) {
		g.fillRect(0, 0, GameConfig.windowWidth, GameConfig.windowHeight);
		/*if(this.timer == 0 && this.percentage < 100) {
			this.percentage++;
		} else if (timer > 500 && this.percentage > 0) {
			this.percentage--;
		} else if (this.percentage == 100){
			this.timer++;
		} else if (this.percentage == 0) {
			this.timer--;
		}*/
		this.x += 0.005;
		
		
		g.rotate(this.x, GameConfig.windowWidth/2, GameConfig.windowHeight/2);
		super.paint(g);
	}

}
