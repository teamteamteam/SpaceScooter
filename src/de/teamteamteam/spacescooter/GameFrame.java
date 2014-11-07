package de.teamteamteam.spacescooter;

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

	/**
	 * The usual stuff since the JFrame supports serialization.
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * Our BufferStrategy for convenient reuse in the draw method.
	 */
	private BufferStrategy bufferStrategy;

	/**
	 * The SuperScreen instance, so we know who to trigger for fresh drawn frames.
	 */
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
		this.setTitle(GameConfig.windowTitle);
		this.setSize(GameConfig.windowWidth, GameConfig.windowHeight);
		this.setResizable(false);
		this.setUndecorated(false);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);

		// Make sure we get the keyboard events. Use Keyboard.isKeyDown() to ask
		// about keys status.
		this.addKeyListener(Keyboard.getInstance());

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
	 * This is the draw method that gets called by the PaintThread through the AWT EventQueue.
	 * We use a BufferStrategy for double buffering, so things are a little more complicated than usual.
	 * First, we create our bufferStrategy _once_ within the init() method.
	 * Then, we try to fetch our Graphics object from it and do the painting.
	 * When the painting is done, we dispose the Graphics object.
	 * 
	 * Now the little tricky part:
	 * We are drawing on what is called a VolatileImage (Due to the BufferStrategy).
	 * This VolatileImage may lose its contents since it is stored in video ram.
	 * This is why we need to repeat our painting operations in case the contents
	 * of our VolatileImage were restored (aka reset to a blank canvas) or in case the
	 * contents were lost (video ram was re-claimed by something, whatever...).
	 * In between, there is a small window where the Image can be shown through the BufferStrategy.
	 * 
	 * After all that is done, we tell our Toolkit to do a sync.
	 * This takes care of graphical output synchronization things related to the running OS.
	 * 
	 * And that's how you use double buffering. Easy, huh? ;-)
	 * 
	 * @see http://content.gpwiki.org/index.php/Java:Tutorials:Double_Buffering for details.
	 * @see http://docs.oracle.com/javase/7/docs/api/java/awt/image/BufferStrategy.html
	 */
	public void draw() {
		Graphics2D bufferedGraphics = null;
		do { // while bufferStrategy.contentsLost()
			do { // bufferStrategy.contentsRestored()
				try {
					bufferedGraphics = (Graphics2D) this.bufferStrategy.getDrawGraphics();
					this.superScreen.doPaint(bufferedGraphics); //Trigger the actual paint routines.
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
