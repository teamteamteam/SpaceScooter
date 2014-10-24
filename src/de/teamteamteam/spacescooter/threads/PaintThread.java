package de.teamteamteam.spacescooter.threads;

import java.awt.EventQueue;

import de.teamteamteam.spacescooter.gui.GameFrame;

/**
 * This thread triggers about 60 redraws per second.
 */
public class PaintThread extends Thread {

	private GameFrame gf;
	BasicTimer timer = new BasicTimer(120);

	public PaintThread(GameFrame gf) {
		this.gf = gf;
	}

	public void run() {
		final GameFrame gf = this.gf; // :'-(
		while (true) {
			timer.sync();
			//Trigger redrawing the things. Important: AWT-Context needed here!
			EventQueue.invokeLater(new Runnable() {
				public void run() {
					gf.draw();
				}
			});
		}
	}
}
