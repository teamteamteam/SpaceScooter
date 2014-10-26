package de.teamteamteam.spacescooter.threads;

import java.util.Iterator;

import de.teamteamteam.spacescooter.background.Background;
import de.teamteamteam.spacescooter.entities.Entity;

public class UpdateThread extends TimedThread {
		
	public UpdateThread() {
		this.setName("UpdateThread");
	}
	
	public void work() {
		// Update all the entities
		this.updateBackgrounds();
		this.updateEntities();
	}

	private void updateBackgrounds() {
		Iterator<Background> i = Background.backgrounds.iterator();
		while (i.hasNext()) {
			Background b = i.next();
			b.update();
		}
	}

	private void updateEntities() {
		Iterator<Entity> i = Entity.entities.iterator();
		while (i.hasNext()) {
			Entity e = i.next();
			e.update();
		}
	}
}
