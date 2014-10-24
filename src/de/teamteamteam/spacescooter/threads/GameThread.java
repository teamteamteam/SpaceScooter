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
	 * 60FPS => 1/60s in nanoseconds (10^-9).
	 */
	private long frameTime = (1000L*1000L*1000L) / 60L;
	
	public GameThread(GameFrame gf) {
		this.setName("GameThread");
		this.gf = gf;
//		this.setPriority(Thread.MAX_PRIORITY);
	}

	public void run() {
		final GameFrame gf = this.gf; // :'-(
		while (true) {
			long frameStart = System.nanoTime();
			
			//Update all the entities
			this.updateBackgrounds();
			this.updateEntities();
			
			//Trigger redrawing the things. Important: AWT-Context needed here!
			EventQueue.invokeLater(new Runnable() {
				public void run() {
					gf.draw();
				}
			});
			
			long frameDone = System.nanoTime();			
			long workTime = (frameDone - frameStart);
			//System.out.println("Arbeitszeit: " + (workTime) + "ns");

			long timeToWait = this.frameTime - workTime;
			long msToWait = timeToWait / 1000000;

			//wait using sleep for bigger intervals
			if(msToWait > 1) {
				try {
					Thread.sleep(msToWait);
				} catch(InterruptedException e) {
					System.err.println(e.getStackTrace());
				}
			}
			System.out.println("1: " + (System.nanoTime() - frameStart));
			System.out.println("2: " + (System.nanoTime() - frameStart));
			System.out.println("3: " + (System.nanoTime() - frameStart));
			System.out.println("4: " + (System.nanoTime() - frameStart));
			System.out.println("5: " + (System.nanoTime() - frameStart));
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
