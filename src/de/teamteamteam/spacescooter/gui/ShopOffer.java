package de.teamteamteam.spacescooter.gui;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;

import de.teamteamteam.spacescooter.entity.Entity;

public class ShopOffer extends Entity {

	private String offer;
	private int bought;
	private final int max = 15;
	private final int price;
	private int currentPrice;
	private ImageEntity displayPrice;
	
	public ShopOffer(int x, int y, int bought, String offer, int price) {
		super(x, y);
		this.offer = offer;
		this.bought = bought;
		this.price = price;
		if(bought == 0) this.currentPrice = price;
		else this.currentPrice = (int) (price+(price*bought*1.36));
		this.displayPrice = new ImageEntity(185, y+20, null);
		if(this.bought<this.max) this.displayPrice.drawString(String.valueOf(this.currentPrice) + "C", 20, Color.WHITE);
		else this.displayPrice.drawString("---", 20, Color.WHITE);
		for (int i = 0; i<max; i++){
			if(i<bought){
				new ImageEntity(x + 150 + i*35, y - 3, "images/shop/shopbought.png");
			}else{
				new ImageEntity(x + 150 + i*35, y - 3, "images/shop/shopnotbought.png");
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
		new ImageEntity(this.getX() + 150 + bought*35, this.getY() - 3, "images/shop/shopbought.png");
		bought++;
		this.currentPrice = (int) (price+(price*bought*1.36));
		if(this.bought<this.max) this.displayPrice.drawString(String.valueOf(this.currentPrice) + "C", 20, Color.WHITE);
		else this.displayPrice.drawString("----", 20, Color.WHITE);
	}

	public int getBought() {
		return bought;
	}

	public int getMax() {
		return max;
	}
	
	public int getCurrentPrice(){
		return this.currentPrice;
	}
}
	
