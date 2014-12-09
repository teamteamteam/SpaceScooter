package de.teamteamteam.spacescooter.gui;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;

import de.teamteamteam.spacescooter.brain.GameConfig;
import de.teamteamteam.spacescooter.entity.Entity;

/**
 * A headline that is being displayed at the beginning of each level,
 * disappearing by fading out after 1-2 seconds.
 */
public class LevelHeadline extends Entity {


	private int lifeTime;
	private int lifeTimeCounter;

	private String text;
	
	public LevelHeadline(int x, int y, String text) {
		super(x, y);
		this.text = text;
		this.lifeTime = 150;
		this.lifeTimeCounter = this.lifeTime;
	}
	
	/**
	 * Make the headline stay for a while, then let it disappear.
	 */
	@Override
	public void update() {
		if(this.lifeTimeCounter > 0) {
			this.lifeTimeCounter--;
		}
		if(lifeTime == 0) {
			this.remove();
		}
	}
	
	/**
	 * Draw the Headline
	 */
	public void paint(Graphics2D g) {
		g.setFont(new Font("Monospace", 0, 60));
		float alpha = (float) (this.lifeTimeCounter / (this.lifeTime * 1.0));
		int textWidth = g.getFontMetrics().stringWidth(this.text);
		int textHeight = g.getFontMetrics().getHeight();
		int x = (GameConfig.gameScreenWidth/2 - textWidth/2);
		int y = (int) (GameConfig.gameScreenHeight/2 - textHeight);
		g.setColor(new Color(1.0F, 1.0F, 1.0F, alpha));
		g.drawString(this.text, x, y);
	}

}
