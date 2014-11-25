package de.teamteamteam.spacescooter.background;

import java.awt.Graphics2D;

/**
 * The StarBackground of the first level
 */
public class StarBackground extends Background {
	
	private int y;
	
	/**
	 * Constructor for the background
	 */
	public StarBackground(int x, int y) {
		super(x, y);
		this.y = y;
		this.setImage("images/starbackground.png");
	}

	/**
	 * offset in x-direction for the painting
	 */
	private int offset = 0;
	
	/**
	 * standart update method
	 */
	public void update() {
		this.offset -= 2;
		//System.out.println(this.offset);
		if(Math.abs(this.offset) > this.getImage().getWidth()) {
			this.offset += this.getImage().getWidth();
		}
	}
	
	/**
	 * standart paint method
	 */
	public void paint(Graphics2D g) {
		g.drawImage(this.getImage(), (0+this.offset), this.y, null);
		g.drawImage(this.getImage(), (this.getImage().getWidth()+this.offset), this.y, null);
		g.drawImage(this.getImage(), (2*this.getImage().getWidth()+this.offset), this.y, null);
	}
	
}
