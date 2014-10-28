package de.teamteamteam.spacescooter.gui;

import java.awt.Graphics2D;
import java.awt.Toolkit;
import java.awt.image.BufferStrategy;
import javax.swing.JFrame;

import de.teamteamteam.spacescooter.control.Keyboard;
import de.teamteamteam.spacescooter.screen.Screen;
import de.teamteamteam.spacescooter.utility.GameConfig;

/**
 * The game will take place in this beautiful window.
 */
public class GameFrame extends JFrame {

	private static final long serialVersionUID = 1L;

	private BufferStrategy bufferStrategy;

	private Screen superScreen;

	/**
	 * Default constructor.
	 */
	public GameFrame() {
		super();
	}

	/**
	 * Set up the GameFrame before showing it to the world.
	 */
	public void init() {
		this.setTitle("Unser schöner Titel");
		this.setSize(GameConfig.windowWidth, GameConfig.windowHeight);
		this.setResizable(false);
		this.setUndecorated(false);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);

		// Make sure we get the keyboard events. Use Keyboard.isKeyDown() to ask
		// about keys status.
		this.addKeyListener(new Keyboard());

		this.setVisible(true);

		//ignore the OS telling us to repaint - it's wasting our time.
		this.setIgnoreRepaint(true);
		
		//prepare the buffered strategy
		this.createBufferStrategy(2);
		this.bufferStrategy = this.getBufferStrategy();
	}

	/**
	 * Set the superScreen to trigger doPaint() on.
	 * @param superScreen
	 */
	public void setSuperScreen(Screen superScreen) {
		this.superScreen = superScreen;
	}
	
	/**
	 * The pain, do not underestimate it!
	 * 
	 * @see http://content.gpwiki.org/index.php/Java:Tutorials:Double_Buffering for details.
	 */
	public void draw() {
		Graphics2D bufferedGraphics = null;
		do { // while bufferStrategy.contentsLost()
			do { // bufferStrategy.contentsRestored()
				try {
					bufferedGraphics = (Graphics2D) this.bufferStrategy.getDrawGraphics();

					this.superScreen.doPaint(bufferedGraphics);

				} catch (Exception e) {
					System.out.println("Hier geht was schief");
					e.printStackTrace();
				} finally {
					// We are done, dispose the pen and celebrate the result!
					if (bufferedGraphics != null)
						bufferedGraphics.dispose();
				}
			} while (this.bufferStrategy.contentsRestored());
			this.bufferStrategy.show();
		} while (this.bufferStrategy.contentsLost());
		Toolkit.getDefaultToolkit().sync(); //Tell the OS to update its graphics of the window.
	}

}
