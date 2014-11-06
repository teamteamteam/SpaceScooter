package de.teamteamteam.spacescooter.thread;

import java.awt.EventQueue;

import de.teamteamteam.spacescooter.gui.GameFrame;

/**
 * This thread triggers the redrawing on the GameFrame.
 */
public class PaintThread extends TimedThread {

	/**
	 * Runnable that is passed to the EventQueue for later invocation.
	 */
	private final Runnable paintRunnable;
	
	/**
	 * Constructor. Sets the name of the Thread and creates the paintRunnable.
	 */
	public PaintThread(GameFrame gf) {
		final GameFrame gameFrame = gf;
		this.setName("PaintThread");
		this.paintRunnable = new Runnable() {
			public void run() {
				gameFrame.draw();
			}
		};
	}

	/**
	 * The work method invoked by the TimingThread.
	 */
	public void work() {
		//Trigger redrawing the things. Important: AWT-Context needed here!
		EventQueue.invokeLater(this.paintRunnable);
	}
}
