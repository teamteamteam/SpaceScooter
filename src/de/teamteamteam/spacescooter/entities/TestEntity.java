package de.teamteamteam.spacescooter.entities;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.KeyEvent;

import de.teamteamteam.spacescooter.controls.Keyboard;

public class TestEntity extends Entity {

	private Color color;

	public TestEntity() {
		super();
		this.setEnemy(true);
		this.color = new Color(255, 0, 0);
	}

	public void paint(Graphics g) {
		g.setColor(this.color);
		g.fillRect(300, 300, 100, 100);
	}

	public void update(long millisecondsSinceLastCall) {
		if(Keyboard.isKeyDown(KeyEvent.VK_SPACE)) {
			System.out.println("Hallo Welt!");
		}
	}

}
