package de.teamteamteam.spacescooter.gui;

import java.awt.Graphics;
import java.util.ArrayList;
import java.util.Iterator;

import javax.swing.JFrame;

import de.teamteamteam.spacescooter.controls.Keyboard;
import de.teamteamteam.spacescooter.entities.Entity;
import de.teamteamteam.spacescooter.entities.Player;
import de.teamteamteam.spacescooter.entities.TestEntity;

/**
 * The game will take place in this beautiful window.
 */
public class GameFrame extends JFrame {

	private ArrayList<Entity> entities;
	
	public GameFrame() {
		super();
		this.entities = new ArrayList<Entity>();
		
		//TODO: Remove this!
		this.entities.add(new TestEntity());
		this.entities.add(new Player());
	}
	
	public ArrayList<Entity> getEntityList() {
		return this.entities;
	}
	
	/**
	 * Set up the GameFrame before showing it to the world.
	 */
	public void init() {
		this.setTitle("Unser schöner Titel");
		this.setSize(800, 600);
		this.setUndecorated(false);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		
		//Make sure we get the keyboard events. Use Keyboard.isKeyDown() to ask about keys status.
		this.addKeyListener(new Keyboard());
		
		this.setVisible(true);
	}
	
	/**
	 * Have the GameFrame repaint all the visible entities.
	 */
	@Override
	public void paint(Graphics g) {
		Iterator<Entity> i = this.entities.iterator();
		while(i.hasNext()) {
			Entity e = i.next();
			e.paint(g);
		}
	}
	
	
	
}
