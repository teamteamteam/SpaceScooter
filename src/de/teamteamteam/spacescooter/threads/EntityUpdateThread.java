package de.teamteamteam.spacescooter.threads;

import java.util.Iterator;

import de.teamteamteam.spacescooter.background.Background;
import de.teamteamteam.spacescooter.entities.Entity;
import de.teamteamteam.spacescooter.gui.GameFrame;

/**
 * This thread is responsible for updating all the entities.
 * It runs about 60 times per second.
 */
public class EntityUpdateThread extends Thread {

	private GameFrame gf;
	BasicTimer timer = new BasicTimer(60);

	public EntityUpdateThread(GameFrame gf) {
		this.gf = gf;
	}

	public void run() {
		while (true) {
			timer.sync();
			this.updateBackgrounds();
			this.updateEntities();
		}
	}
	
	private void updateBackgrounds() {
		Iterator<Background> i = Background.backgrounds.iterator();
		while(i.hasNext()) {
			Background b = i.next();
			b.update();
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
