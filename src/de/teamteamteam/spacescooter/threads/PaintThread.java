package de.teamteamteam.spacescooter.threads;

import java.awt.EventQueue;

import de.teamteamteam.spacescooter.gui.GameFrame;

/**
 * This thread triggers about 60 redraws per second.
 */
public class PaintThread extends TimedThread {

	private GameFrame gf;

	public PaintThread(GameFrame gf) {
		this.gf = gf;
		this.setName("PaintThread");
		this.setHz(60);
	}

	public void work() {
		//Trigger redrawing the things. Important: AWT-Context needed here!
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				gf.draw();
			}
		});
	}
}
