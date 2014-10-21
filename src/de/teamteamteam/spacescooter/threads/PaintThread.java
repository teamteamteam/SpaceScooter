package de.teamteamteam.spacescooter.threads;

import java.awt.EventQueue;

import de.teamteamteam.spacescooter.gui.GameFrame;

/**
 * This thread triggers about 60 redraws per second.
 */
public class PaintThread extends Thread {

	private GameFrame gf;

	public PaintThread(GameFrame gf) {
		this.gf = gf;
	}

	public void run() {
		final GameFrame gf = this.gf; // :'-(
		while (true) {
			try {
				Thread.sleep(16);
			} catch (InterruptedException e) {
				System.err.println(e);
			}
			//Trigger redrawing the things. Important: AWT-Context needed here!
			EventQueue.invokeLater(new Runnable() {
				public void run() {
					gf.drawEntities();
				}
			});
		}
	}
}
