package de.teamteamteam.spacescooter.control;

import java.awt.Point;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;

import de.teamteamteam.spacescooter.entity.enemy.EnemyBoss;
import de.teamteamteam.spacescooter.entity.enemy.EnemyFour;
import de.teamteamteam.spacescooter.entity.enemy.EnemyOne;
import de.teamteamteam.spacescooter.entity.enemy.EnemyThree;
import de.teamteamteam.spacescooter.entity.enemy.EnemyTwo;
import de.teamteamteam.spacescooter.entity.item.ItemNuke;
import de.teamteamteam.spacescooter.screen.GameScreen;

/**
 * This is our main control input source.
 * It accumulates pressed keys, so we can use them to do player movement and more.
 * It also provides methods to pass on the KeyListener events in case somebody wants
 * to implement something special (such as continuous fire versus spontaneous fire)
 */
public class Keyboard implements KeyListener {

	/**
	 * Instance of the Keyboard class to pass to others.
	 */
	private static Keyboard instance = new Keyboard();
	
	/**
	 * ArrayList containing registered KeyboardListeners.
	 */
	private static ArrayList<KeyboardListener> listener = new ArrayList<KeyboardListener>();
	
	/**
	 * ArrayList containing the currently pressed keys.
	 */
	private static ArrayList<Integer> activeKeys = new ArrayList<Integer>();
	
	
	/**
	 * Private constructor, this class will only be instantiated once.
	 */
	private Keyboard() {}
	
	
	/**
	 * Static method to get the Keyboard instance.
	 */
	public static Keyboard getInstance() {
		return Keyboard.instance;
	}

	/**
	 * Returns true if the given keyCodes key is down.
	 */
	public static boolean isKeyDown(int keyCode) {
		return Keyboard.activeKeys.contains((Integer) keyCode);
	}
	
	/**
	 * Returns true if the given keyCodes key is down.
	 */
	public static boolean isKeyPress(int keyCode) {
		return Keyboard.activeKeys.contains((Integer) keyCode);
	}

	/**
	 * Returns true if the given keyCodes key is up.
	 */
	public static boolean isKeyUp(int keyCode) {
		return !Keyboard.activeKeys.contains((Integer) keyCode);
	}
	
	
	/**
	 * Register given listener to the event notifications. 
	 */
	public void addListener(KeyboardListener listener) {
		Keyboard.listener.add(listener);
	}

	/**
	 * Remove the given listener from the event notifications.
	 */
	public void removeListener(KeyboardListener listener) {
		Keyboard.listener.remove(listener);
	}
	
	/**
	 * Make sure we only iterate over copies due to possible concurrent modification.
	 * This method returns a copy of the listener list.
	 */
	public ArrayList<KeyboardListener> getListener() {
		return new ArrayList<KeyboardListener>(Keyboard.listener);
	}
	/**
	 * KeyListener method.
	 * Registers the pressed key and passes the event on to registered listeners.
	 */
	public void keyPressed(KeyEvent e) {
		if(Keyboard.activeKeys.contains((Integer) e.getKeyCode())){
			return;
		}
		Keyboard.activeKeys.add((Integer) e.getKeyCode());
		for(KeyboardListener kl : Keyboard.listener) kl.keyPressed(e);
		
		//Debug Spawn Enemy on Press or Items
		//TODO: Remove the Code when game is ready
		if(e.getKeyCode() == KeyEvent.VK_1) {
			new EnemyOne(400,400);
		}
		if(e.getKeyCode() == KeyEvent.VK_2) {
			new EnemyTwo(400,400);
		}
		if(e.getKeyCode() == KeyEvent.VK_3) {
			new EnemyThree(400,400);
		}
		if(e.getKeyCode() == KeyEvent.VK_4) {
			ArrayList<Point> points = new ArrayList<Point>();
			points.add(new Point(398,306));;
			points.add(new Point(10,300));
			new EnemyFour(700,51,points);
		}
		if(e.getKeyCode() == KeyEvent.VK_9) {
			new ItemNuke(400,400);
		}
		if(e.getKeyCode() == KeyEvent.VK_0) {
			new EnemyBoss(400,400);
		}
		if(e.getKeyCode() == KeyEvent.VK_5) {
			GameScreen.getPlayer().setCollide(!GameScreen.getPlayer().canCollide());
		}
		
	}

	/**
	 * KeyListener method.
	 * Registers the released key and passes the event on to registered listeners.
	 */
	public void keyReleased(KeyEvent e) {
		if(Keyboard.activeKeys.contains((Integer) e.getKeyCode())) {
			Keyboard.activeKeys.remove((Integer) e.getKeyCode());
		}
		for(KeyboardListener kl : Keyboard.listener) kl.keyReleased(e);
	}
	
	/**
	 * KeyListener method.
	 * Registers the typed key and passes the event on to registered listeners.
	 */
	public void keyTyped(KeyEvent e) {
		for(KeyboardListener kl : Keyboard.listener) kl.keyTyped(e);
		//Nice for cheatcodes and eastereggs later...
		//System.out.println("keyTyped: " + e);
	}

}
