package de.teamteamteam.spacescooter.thread;

import java.awt.EventQueue;

import de.teamteamteam.spacescooter.GameFrame;

/**
 * This thread triggers the redrawing on the GameFrame.
 */
public class PaintThread extends TimedThread {

	/**
	 * Runnable that is passed to the EventQueue for later invocation.
	 */
	private final Runnable paintRunnable;
	
	/**
	 * Internal pointer to GameFrame.
	 */
	private final GameFrame gameFrame;
	
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
		this.gameFrame = gf;
	}

	/**
	 * The work method invoked by the TimingThread.
	 */
	public void work() {
		//Trigger redrawing the things. Important: AWT-Context needed here!
		EventQueue.invokeLater(this.paintRunnable);
	}

	/**
	 * Return the workTime by asking the GameFrame for the time the
	 * draw() call took in nanoseconds.
	 */
	@Override
	public long getWorkTime() {
		return this.gameFrame.getFrameTime();
	}
}
