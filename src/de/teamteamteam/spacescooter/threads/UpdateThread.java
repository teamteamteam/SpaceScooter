package de.teamteamteam.spacescooter.threads;

import java.util.ArrayList;
import java.util.Iterator;


import de.teamteamteam.spacescooter.entities.Entity;
import de.teamteamteam.spacescooter.gui.GameFrame;

public class UpdateThread extends Thread {

	private GameFrame gf;

	public UpdateThread(GameFrame gf) {
		this.gf = gf;
	}

	public void run() {
		while (true) {
			try {
				Thread.sleep(16);
			} catch (InterruptedException e) {
				System.err.println(e);
			}
			this.updateEntities();
		}
	}
	
	public void updateEntities() {
		ArrayList<Entity> entityList = this.gf.getEntityList();
		Iterator<Entity> i = entityList.iterator();
		while(i.hasNext()) {
			Entity e = i.next();
			e.update();
		}
	}
}
