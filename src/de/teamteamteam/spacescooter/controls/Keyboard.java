package de.teamteamteam.spacescooter.controls;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;


public class Keyboard implements KeyListener {

	private static boolean[] keys = new boolean[150]; //TODO: This might kill the game in case there are keycodes > 150
	
	public static boolean isKeyDown(int keyCode) {
		if (keyCode >= 0 && keyCode < keys.length) {
			return keys[keyCode];
		}
		return false;
	}

	public void keyPressed(KeyEvent e) {
		keys[e.getKeyCode()] = true;
	}

	public void keyReleased(KeyEvent e) {
		keys[e.getKeyCode()] = false;
	}
	
	public void keyTyped(KeyEvent e) {
		//Nice for cheatcodes and eastereggs later...
		//System.out.println("keyTyped: " + e);
	}


}
