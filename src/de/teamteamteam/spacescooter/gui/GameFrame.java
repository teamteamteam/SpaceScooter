package de.teamteamteam.spacescooter.gui;

import java.awt.Graphics;
import java.util.ArrayList;
import java.util.Iterator;

import javax.swing.JFrame;

import de.teamteamteam.spacescooter.entities.Entity;
import de.teamteamteam.spacescooter.entities.TestEntity;

public class GameFrame extends JFrame {

	private ArrayList<Entity> entities;
	
	public GameFrame() {
		super();
		this.entities = new ArrayList<Entity>();
		
		//TODO: Remove this!
		TestEntity t = new TestEntity();
		this.entities.add(t);
	}
	
	public ArrayList<Entity> getEntityList() {
		return this.entities;
	}
	
	public void init() {
		this.setTitle("Unser schöner Titel");
		this.setSize(800, 600);
		this.setUndecorated(false);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.setVisible(true);
	}
	
	@Override
	public void paint(Graphics g) {
		Iterator<Entity> i = this.entities.iterator();
		while(i.hasNext()) {
			Entity e = i.next();
			e.paint(g);
		}
	}
	
	
	
}
