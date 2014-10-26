package de.teamteamteam.spacescooter;

import java.awt.EventQueue;

import de.teamteamteam.spacescooter.background.StarBackground;
import de.teamteamteam.spacescooter.entities.Player;
import de.teamteamteam.spacescooter.entities.TestEntity;
import de.teamteamteam.spacescooter.gui.GameFrame;
import de.teamteamteam.spacescooter.threads.PaintThread;
import de.teamteamteam.spacescooter.threads.UpdateThread;
import de.teamteamteam.spacescooter.utilities.GraphicsSettings;

/**
 * Nothing but a class containing the main method.
 */
public class Main {

	/**
	 * Main entry point of the game.
	 * "... for i am the Alpha and the Omega." - God
	 * 
	 * @param args Command line arguments.
	 */
	public static void main(String[] args) {
		GraphicsSettings gs = new GraphicsSettings(); //Get settings
		
		final GameFrame gf = new GameFrame();
		
		//Whatever.
		new StarBackground();
		new Player();
		new TestEntity();
		
		//Initialize the GameFrame properly within the AWT EventQueue
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				gf.init();
				gf.draw(); //Draw nothing for the first time.
			}
		});
	
		//Initialize GameThread
		PaintThread paintThread = new PaintThread(gf);
		paintThread.setHz(gs.getRefreshRate()); //This may be set depending on the system graphic settings.
		paintThread.start();
		
		//Initialize UpdateThread
		UpdateThread updateThread = new UpdateThread();
		updateThread.setHz(100); //This shall remain constant across all systems.
		updateThread.start();

	}
}
