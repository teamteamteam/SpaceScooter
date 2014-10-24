package de.teamteamteam.spacescooter.threads;

import java.awt.EventQueue;
import java.util.Iterator;

import de.teamteamteam.spacescooter.background.Background;
import de.teamteamteam.spacescooter.entities.Entity;
import de.teamteamteam.spacescooter.gui.GameFrame;

/**
 * This thread triggers about 60 redraws per second.
 */
public class GameThread extends Thread {

	private GameFrame gf;

	private long lastFrame;
	
	public GameThread(GameFrame gf) {
		this.setName("GameThread");
		this.gf = gf;
	}

	public void run() {
		final GameFrame gf = this.gf; // :'-(
		this.lastFrame = System.currentTimeMillis();
		while (true) {
			//If it is not time yet, sleep and continue the loop.
			if((System.currentTimeMillis() - this.lastFrame) <= 15) {
				try {
					Thread.sleep(1);
				} catch(InterruptedException e) {
					System.err.println(e.getStackTrace());
				}
				continue;
			}
			
			this.updateBackgrounds();
			this.updateEntities();
			
			//Trigger redrawing the things. Important: AWT-Context needed here!
			EventQueue.invokeLater(new Runnable() {
				public void run() {
					gf.draw();
				}
			});
			//Update time for the last frame
			this.lastFrame = System.currentTimeMillis();
		}
	}
	
	private void updateBackgrounds() {
		Iterator<Background> i = Background.backgrounds.iterator();
		while(i.hasNext()) {
			Background b = i.next();
			b.update(System.currentTimeMillis() - this.lastFrame);
		}
	}
	
	private void updateEntities() {
		Iterator<Entity> i = Entity.entities.iterator();
		while(i.hasNext()) {
			Entity e = i.next();
			e.update(System.currentTimeMillis() - this.lastFrame);
		}
	}
}
