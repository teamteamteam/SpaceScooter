package de.teamteamteam.spacescooter.gui;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;

import de.teamteamteam.spacescooter.entity.Entity;
import de.teamteamteam.spacescooter.entity.enemy.Enemy;

public class BossHealthBar extends Entity {

	private int width = 150;
	private int height = 14;
	private int health = 0;
	private int fullhealth = 0;
	private int healthwidth = 0;
	private Enemy boss;
	
	public BossHealthBar(int x, int y, Enemy boss) {
		super(x, y);
		this.boss = boss;
		this.fullhealth = boss.getHealthPoints();
	}

	public void paint(Graphics2D g) {
		try {
			this.health = (int) (((double) boss.getHealthPoints() / (double) this.fullhealth) * 100);
			this.healthwidth = ((this.width) * this.health) / 100;
		} catch(Exception e) {
			this.healthwidth = 0;
		}
		g.setColor(Color.WHITE);
		g.setFont(new Font("Monospace", 0, 16));
		g.drawString("Boss:", this.getX(), this.getY()+12);
		g.setColor(Color.PINK);
		g.fillRect(this.getX()+70, this.getY(), this.healthwidth, this.height);
		g.setColor(Color.WHITE);
		g.drawRect(this.getX()+70, this.getY(), this.width, this.height);
	}
	
	public void update() {
		if (this.boss != null && this.boss.isAlive() == false) {
			this.boss = null; //Dereference the boss, so it actually can be removed.
			this.remove();
		}
	}
	
}
