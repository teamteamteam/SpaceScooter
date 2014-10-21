package de.teamteamteam.spacescooter.threads;

import java.util.Iterator;

import de.teamteamteam.spacescooter.entities.Entity;
import de.teamteamteam.spacescooter.gui.GameFrame;

/**
 * This thread is responsible for updating all the entities.
 * It runs about 60 times per second.
 */
public class EntityUpdateThread extends Thread {

	private GameFrame gf;

	public EntityUpdateThread(GameFrame gf) {
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
	
	private void updateEntities() {
		Iterator<Entity> i = Entity.entities.iterator();
		while(i.hasNext()) {
			Entity e = i.next();
			e.update();
		}
	}
}
