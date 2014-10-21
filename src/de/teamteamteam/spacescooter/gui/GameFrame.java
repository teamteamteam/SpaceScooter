package de.teamteamteam.spacescooter.gui;

import java.awt.Graphics;
import java.awt.Toolkit;
import java.awt.image.BufferStrategy;
import java.util.Iterator;

import javax.swing.JFrame;

import de.teamteamteam.spacescooter.controls.Keyboard;
import de.teamteamteam.spacescooter.entities.Entity;

/**
 * The game will take place in this beautiful window.
 */
public class GameFrame extends JFrame {

	private static final long serialVersionUID = 1L;

	public GameFrame() {
		super();
	}

	/**
	 * Set up the GameFrame before showing it to the world.
	 */
	public void init() {
		this.setTitle("Unser schöner Titel");
		this.setSize(800, 600);
		this.setUndecorated(false);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);

		// Make sure we get the keyboard events. Use Keyboard.isKeyDown() to ask
		// about keys status.
		this.addKeyListener(new Keyboard());

		this.setVisible(true);
	}

	/**
	 * The pain, do not underestimate it!
	 * @see http://content.gpwiki.org/index.php/Java:Tutorials:Double_Buffering for details.
	 */
	public void drawEntities() {
		this.createBufferStrategy(2);
		Graphics bufferedGraphics = null;
		BufferStrategy bufferStrategy = this.getBufferStrategy();

		do { //while bufferStrategy.contentsLost()
			do { //bufferStrategy.contentsRestored()
				try {
					bufferedGraphics = bufferStrategy.getDrawGraphics();

					// Now we can use bufferedGraphics to actually draw stuff
					// ...
					Iterator<Entity> i = Entity.entities.iterator();
					while (i.hasNext()) {
						Entity e = i.next();
						e.paint(bufferedGraphics);
					}
				} catch(Exception e) {
					System.out.println("Hier geht was schief");
					System.out.println(e);
				} finally {
					// We are done, dispose the pen and celebrate the result!
					if (bufferedGraphics != null)
						bufferedGraphics.dispose();
				}
			} while (bufferStrategy.contentsRestored());
			bufferStrategy.show();
		} while (bufferStrategy.contentsLost());
		Toolkit.getDefaultToolkit().sync();

	}

}
