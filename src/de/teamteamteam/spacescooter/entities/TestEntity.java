package de.teamteamteam.spacescooter.entities;

import java.awt.Color;
import java.awt.Graphics;
import java.util.Random;

public class TestEntity extends Entity {

	private Color color;
	private Random random;

	public TestEntity() {
		super();
		this.color = new Color(255, 0, 0);
		this.random = new Random();
	}

	public void paint(Graphics g) {
		g.setColor(this.color);
		g.drawRect(100, 200, 300, 300);
	}

	public void update() {
		System.out.println("TestEntity.update()!");
		this.color = new Color(this.random.nextInt(255),this.random.nextInt(255),this.random.nextInt(255));
	}

}
