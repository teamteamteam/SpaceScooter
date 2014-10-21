package de.teamteamteam.spacescooter.threads;

import de.teamteamteam.spacescooter.gui.GameFrame;

public class PaintThread extends Thread {

	private GameFrame gf;

	public PaintThread(GameFrame gf) {
		this.gf = gf;
	}

	public void run() {
		while (true) {
			try {
				Thread.sleep(16);
			} catch (InterruptedException e) {
				System.err.println(e);
			}
			this.gf.repaint();
		}
	}
}
