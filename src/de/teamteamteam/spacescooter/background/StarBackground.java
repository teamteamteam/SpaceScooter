package de.teamteamteam.spacescooter.background;

import java.awt.Graphics2D;


public class StarBackground extends Background {
	
	private int y;

	public StarBackground(int x, int y) {
		super(x, y);
		this.y = y;
		this.setImage("images/starbackground.png");
	}

	private int offset = 0;
	
	public void update() {
		this.offset -= 2;
		//System.out.println(this.offset);
		if(Math.abs(this.offset) > this.getImage().getWidth()) {
			this.offset += this.getImage().getWidth();
		}
	}
	
	public void paint(Graphics2D g) {
		g.drawImage(this.getImage(), (0+this.offset), this.y, null);
		g.drawImage(this.getImage(), (this.getImage().getWidth()+this.offset), this.y, null);
		g.drawImage(this.getImage(), (2*this.getImage().getWidth()+this.offset), this.y, null);
	}
	
}
