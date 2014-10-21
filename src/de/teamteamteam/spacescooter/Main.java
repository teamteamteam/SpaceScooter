package de.teamteamteam.spacescooter;

import java.awt.EventQueue;

import de.teamteamteam.spacescooter.gui.GameFrame;
import de.teamteamteam.spacescooter.threads.PaintThread;
import de.teamteamteam.spacescooter.threads.UpdateThread;

public class Main {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		final GameFrame gf = new GameFrame();
		
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				gf.init();
			}
		});
		
		PaintThread pt = new PaintThread(gf);
		pt.start();

		UpdateThread ut = new UpdateThread(gf);
		ut.start();

	}

}
