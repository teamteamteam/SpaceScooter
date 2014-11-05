package de.teamteamteam.spacescooter.utility;

import java.awt.image.BufferedImage;
import java.io.BufferedInputStream;
import java.io.File;
import java.io.IOException;
import java.util.Hashtable;

import javax.imageio.ImageIO;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.UnsupportedAudioFileException;

import de.teamteamteam.spacescooter.screen.LoadingScreen;

/**
 * This Loader prefetches all required resources for the Game, such as images or
 * sounds.
 */
public class Loader {

	/**
	 * HashTable containing loaded BufferedImages
	 */
	private static Hashtable<String, BufferedImage> images;

	/**
	 * HashTable containing loaded AudioInputStreams
	 */
	private static Hashtable<String, AudioInputStream> sounds;

	static {
		Loader.images = new Hashtable<String, BufferedImage>();
		Loader.sounds = new Hashtable<String, AudioInputStream>();
	}

	
	/**
	 * Private constructor, this class will never be instantiated.
	 */
	private Loader() {}
	
	
	/**
	 * Return the loaded BufferedImage by its relative filename.
	 */
	public static BufferedImage getBufferedImageByFilename(String filename) {
		if(CodeEnvironment.isJar()) {
			return Loader.images.get(filename);
		} else {
			return Loader.images.get(filename.replace("/", File.separator));
		}
	}

	/**
	 * Return the loaded AudioInputStream by its relative filename.
	 */
	public static AudioInputStream getAudioInputStreamByFilename(String filename) {
		if(CodeEnvironment.isJar()) {
			return Loader.sounds.get(filename);
		} else {
			return Loader.sounds.get(filename.replace("/", File.separator));
		}
	}

	/**
	 * Perform the initial loading of everything and show the progress on the
	 * given LoadingScreen.
	 */
	public static void load(LoadingScreen loadingScreen) {
		// get a list of stuff to load and create buffered images
		// and other things
		String[] elements = CodeEnvironment.getFileList();
		loadingScreen.initialize(0, elements.length);
		for(int i=0; i<elements.length; i++) {
			String e = elements[i];
			if(e.endsWith(".png")) {
				if(GameConfig.DEBUG)
					System.out.println("Creating BufferedImage for: " + e);
				Loader.addBufferedImageByFilename(e);
			}
			if(e.endsWith(".wav")) {
				if(GameConfig.DEBUG)
					System.out.println("Creating AudioInputStream for: " + e);
				Loader.addAudioInputStreamByFilename(e);
			}
			loadingScreen.increaseCurrentProcessed();
		}
	}

	/**
	 * Load a BufferedImage by relative filename.
	 */
	private static void addBufferedImageByFilename(String filename) {
		try {
			BufferedImage image = ImageIO.read(Loader.class.getClassLoader().getResourceAsStream(filename));
			Loader.images.put(filename, image);
		} catch (IOException e) {
			System.err.println("Unable to load BufferedImage: " + filename);
			e.printStackTrace();
		}

	}

	/**
	 * Load an AudioInputStream by relative filename.
	 */
	private static void addAudioInputStreamByFilename(String filename) {
		try {
			AudioInputStream sound = AudioSystem.getAudioInputStream(new BufferedInputStream(Loader.class.getClassLoader().getResourceAsStream(filename)));
			Loader.sounds.put(filename, sound);
		} catch (IOException e) {
			System.err.println("Unable to load AudioInputStream: " + filename);
			e.printStackTrace();
		} catch (UnsupportedAudioFileException e) {
			System.err.println("Unsupported AudioFormat in file: " + filename);
			e.printStackTrace();
		}

	}

}
