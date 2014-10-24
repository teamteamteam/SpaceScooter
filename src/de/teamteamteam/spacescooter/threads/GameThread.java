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
	
	/**
	 * 60FPS => 1/60 in nanoseconds.
	 */
	private long frameTime = 16666666L;
	
	public GameThread(GameFrame gf) {
		this.setName("GameThread");
		this.gf = gf;
	}

	public void run() {
		final GameFrame gf = this.gf; // :'-(
		this.lastFrame = System.nanoTime();
		while (true) {
			//If we have to wait for more than 1.5ms, sleep 1ms
			if((System.nanoTime() - this.lastFrame) > 1500000) {
				try {
					Thread.sleep(1); //wait 1 ms
				} catch(InterruptedException e) {
					System.err.println(e.getStackTrace());
				}
				continue;
			}

			//If we have to wait for less than 1.5ms, wait manually
			while((this.frameTime - (System.nanoTime() - this.lastFrame)) > 100);
			
			//Update all the entities
			this.updateBackgrounds();
			this.updateEntities();
			
			//Trigger redrawing the things. Important: AWT-Context needed here!
			EventQueue.invokeLater(new Runnable() {
				public void run() {
					gf.draw();
				}
			});
			//Update time for the last frame
			this.lastFrame = System.nanoTime();
		}
	}
	
	private void updateBackgrounds() {
		Iterator<Background> i = Background.backgrounds.iterator();
		while(i.hasNext()) {
			Background b = i.next();
			b.update(System.nanoTime() - this.lastFrame);
		}
	}
	
	private void updateEntities() {
		Iterator<Entity> i = Entity.entities.iterator();
		while(i.hasNext()) {
			Entity e = i.next();
			e.update(System.nanoTime() - this.lastFrame);
		}
	}
}
