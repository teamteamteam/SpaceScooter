package de.teamteamteam.spacescooter.control;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;


public class Keyboard implements KeyListener {

	private static ArrayList<Integer> activeKeys = new ArrayList<Integer>();
	
	public static boolean isKeyDown(int keyCode) {
		return Keyboard.activeKeys.contains((Integer) keyCode);
	}

	public void keyPressed(KeyEvent e) {
		if(Keyboard.activeKeys.contains((Integer) e.getKeyCode())) return;
		Keyboard.activeKeys.add((Integer) e.getKeyCode());
	}

	public void keyReleased(KeyEvent e) {
		if(Keyboard.activeKeys.contains((Integer) e.getKeyCode())) {
			Keyboard.activeKeys.remove((Integer) e.getKeyCode());
		}
	}
	
	public void keyTyped(KeyEvent e) {
		//Nice for cheatcodes and eastereggs later...
		//System.out.println("keyTyped: " + e);
	}


}
