package de.teamteamteam.spacescooter.utility;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

public class Highscore {
	/**
	 * Points for the high score.
	 */
	private static int[] points;
	
	/**
	 * Names for the high score.
	 */
	private static String[] names;
	
	/**
	 * Load the high score from the save file, if it has not been loaded yet.
	 * If the file not exist, the standard value "0" and "Name" will be loaded for every entry.
	 */
	private static final void loadHighscore(){
		if(Highscore.points == null || Highscore.names == null){
			try {
				File f = new File("Highscore");
				if(!f.exists()){
					int[] point;
					String[] name;
					point = new int[20];
					name = new String[20];
					for(int i = 0; i<point.length; i++){
						point[i] = 0;
						name[i] = "Name";
					}
					Highscore.points = point;
					Highscore.names = name;
					return;
				}
				FileInputStream fis = new FileInputStream(f);
				ObjectInputStream ois = new ObjectInputStream(fis);
				Highscore.points = (int[])ois.readObject();
				Highscore.names = (String[])ois.readObject();
				ois.close();
				fis.close();
			} catch (IOException e) {
				e.printStackTrace();
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			}
		}
	}
	
	/**
	 * Saves the high score.
	 */
	private static final void saveScore(){
		try {
			if(Highscore.points == null || Highscore.names == null){
				Highscore.loadHighscore();
			}
			File f = new File("Highscore");
			if(!f.exists()){
				f.createNewFile();
			}
			FileOutputStream fos = new FileOutputStream(f);
			ObjectOutputStream oos = new ObjectOutputStream(fos);
			oos.writeObject(Highscore.points);
			oos.writeObject(Highscore.names);
			oos.close();
			fos.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Get the points of the high score.
	 * Load the high score, if it has not been loaded yet.
	 */
	public static final int[] getPoints(){
		Highscore.loadHighscore();
		return Highscore.points;
	}
	
	/**
	 * Get the names of the high score.
	 * Load the high score, if it has not been loaded yet.
	 */
	public static final String[] getNames(){
		Highscore.loadHighscore();
		return Highscore.names;
	}
	
	/**
	 * Add a new high score.
	 */
	public static void newScore(int point, String name){
		Highscore.loadHighscore();
		int placemant = getPlacement(point);
		int temp = 19;
		if(placemant == -1) return;
		while(temp>placemant){
			points[temp] = points[temp-1];
			names[temp] = names[temp-1];
			temp--;
		}
		points[placemant] = point;
		names[placemant] = name;
		saveScore();
	}
	
	/**
	 * Get your placement in the high score, -1 if the score is too low
	 */
	public static int getPlacement(int point){
		Highscore.loadHighscore();
		for(int i = 0; i<20; i++){
			if(point>=Highscore.points[i]) return i;
		}
		return -1;
	}
}
