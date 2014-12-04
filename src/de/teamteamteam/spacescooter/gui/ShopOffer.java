package de.teamteamteam.spacescooter.gui;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;

import de.teamteamteam.spacescooter.entity.Entity;

public class ShopOffer extends Entity {

	private String offer;
	private int bought;
	private int max;
	
	public ShopOffer(int x, int y, int max, int bought, String offer) {
		super(x, y);
		this.offer = offer;
		this.bought = bought;
		this.max = max;
		for (int i = 0; i<max; i++){
			if(i<bought){
				new ImageEntity(x + 140 + i*35, y - 3, "images/shop/shopbought.png");
			}else{
				new ImageEntity(x + 140 + i*35, y - 3, "images/shop/shopnotbought.png");
			}
		}
	}

	@Override
	public void paint(Graphics2D g) {
		g.setFont(new Font("Monospace", 0, 20));
		g.setColor(new Color(255, 255, 255));
		g.drawString(offer, this.getX(), this.getY()+20);
	}
	
	public void update() {}
	
	public void buy(){
		new ImageEntity(this.getX() + 140 + bought*35, this.getY() - 3, "images/shop/shopbought.png");
		bought++;
	}

	public int getBought() {
		return bought;
	}

	public int getMax() {
		return max;
	}
}
	
