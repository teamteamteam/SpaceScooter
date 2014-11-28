package de.teamteamteam.spacescooter;

import java.awt.EventQueue;
import java.lang.reflect.InvocationTargetException;

import de.teamteamteam.spacescooter.brain.PlayerSession;
import de.teamteamteam.spacescooter.screen.LoadingScreen;
import de.teamteamteam.spacescooter.screen.SuperScreen;
import de.teamteamteam.spacescooter.thread.PaintThread;
import de.teamteamteam.spacescooter.thread.UpdateThread;
import de.teamteamteam.spacescooter.utility.GraphicsSettings;
import de.teamteamteam.spacescooter.utility.Loader;

/**
 * Nothing but a class containing the main method.
 */
public class Main {

	/**
	 * Main entry point of the game.
	 * 
	 * "... for i am the Alpha and the Omega."
	 * - God, a long time ago.
	 * 
	 * @param args Command line arguments.
	 */
	public static void main(String[] args) {
		final GraphicsSettings gs = new GraphicsSettings(); // Get settings

		// Initialize SuperScreen and add to GameFrame, so we can call doPaint()
		// on it.
		final SuperScreen superScreen = new SuperScreen(null);

		// Set up the LoadingScreen
		final LoadingScreen loadingScreen = new LoadingScreen(superScreen);
		superScreen.setOverlay(loadingScreen);

		// Initialize the GameFrame properly within the AWT EventQueue
		try {
			EventQueue.invokeAndWait(new Runnable() {
				public void run() {
					// Instantiate the GameFrame
					final GameFrame gameFrame = new GameFrame();

					// Set the SuperScreen
					gameFrame.setSuperScreen(superScreen);

					// Initialize the gameFrame and trigger a first draw.
					gameFrame.init();
					gameFrame.draw(); // Draw nothing for the first time.

					// Initialize GameThread
					PaintThread paintThread = new PaintThread(gameFrame);
					paintThread.setHz(gs.getRefreshRate()); // Use refresh rate from system graphics configuration.
					paintThread.start();

					// Initialize UpdateThread
					UpdateThread updateThread = new UpdateThread();
					updateThread.setSuperScreen(superScreen);
					updateThread.setHz(100); // This is constant across all systems.
					updateThread.start();
				}
			});
		} catch (InvocationTargetException e) {
			e.printStackTrace();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

		// Finally start loading and everything will follow up.
		// This has to happen after the AWT-Eventqueue is done initializing everything.
		Loader.load(loadingScreen);
		
		//Initialize the player session the first time.
		PlayerSession.reset();
	}
}
