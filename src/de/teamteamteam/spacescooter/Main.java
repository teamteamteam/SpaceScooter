package de.teamteamteam.spacescooter;

import java.awt.EventQueue;

import de.teamteamteam.spacescooter.gui.GameFrame;
import de.teamteamteam.spacescooter.threads.PaintThread;
import de.teamteamteam.spacescooter.threads.EntityUpdateThread;

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
		final GameFrame gf = new GameFrame();
		
		//Initialize the GameFrame properly within the AWT EventQueue
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				gf.init();
			}
		});
		
		//Initialize PaintThread
		PaintThread pt = new PaintThread(gf);
		pt.start();

		//Initialize EntityUpdateThread
		EntityUpdateThread ut = new EntityUpdateThread(gf);
		ut.start();	
	}
}
