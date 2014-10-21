package de.teamteamteam.spacescooter.entities;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.util.Random;

import de.teamteamteam.spacescooter.controls.Keyboard;

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
		if(Keyboard.isKeyDown(KeyEvent.VK_SPACE)) {
			System.out.println("Hallo Welt!");
		}
		this.color = new Color(this.random.nextInt(255),this.random.nextInt(255),this.random.nextInt(255));
	}

}
