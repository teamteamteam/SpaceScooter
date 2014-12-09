package de.teamteamteam.spacescooter.utility;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.Hashtable;

import javax.imageio.ImageIO;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.UnsupportedAudioFileException;

import de.teamteamteam.spacescooter.brain.GameConfig;
import de.teamteamteam.spacescooter.level.LevelConfig;
import de.teamteamteam.spacescooter.level.LevelConfigParser;
import de.teamteamteam.spacescooter.screen.LoadingScreen;
import de.teamteamteam.spacescooter.sound.SoundSystem;

/**
 * This Loader prefetches all required resources for the Game, such as images or
 * sounds.
 */
public class Loader {

	/**
	 * Private instance of the LevelConfigParser to parse LevelConfigs.
	 * Sorry, i know this is kind of bad, but it is a TODO to clean that up. :-/
	 */
	private static LevelConfigParser levelConfigParser;
	
	/**
	 * HashTable containing loaded BufferedImages
	 */
	private static Hashtable<String, BufferedImage> images;

	/**
	 * HashTable containing the loaded sounds URLs
	 */
	private static Hashtable<String, URL> sounds;

	/**
	 * HashTable containing the parsed LevelConfigs.
	 */
	private static Hashtable<String, LevelConfig> levelConfigs;
	
	
	/**
	 * Initialize the HashTables on load.
	 */
	static {
		Loader.images = new Hashtable<String, BufferedImage>();
		Loader.sounds = new Hashtable<String, URL>();
		Loader.levelConfigs = new Hashtable<String, LevelConfig>();
		Loader.levelConfigParser = new LevelConfigParser();
	}

	
	/**
	 * Private constructor, this class will never be instantiated.
	 */
	private Loader() {}
	
	
	/**
	 * Return the loaded BufferedImage by its relative filename.
	 */
	public static BufferedImage getBufferedImageByFilename(String filename) {
		BufferedImage image = null;
		if(CodeEnvironment.isJar) {
			image = Loader.images.get(filename);
		} else {
			image = Loader.images.get(filename.replace("/", File.separator));
		}
		
		if(image == null) {
			System.err.println("Could not get BufferedImage by filename: '" + filename + "'");
		}
		return image;
	}

	/**
	 * Return the loaded sound URL by its relative filename.
	 */
	public static URL getSoundURLByFilename(String filename) {
		if(CodeEnvironment.isJar) {
			return Loader.sounds.get(filename);
		} else {
			return Loader.sounds.get(filename.replace("/", File.separator));
		}
	}
	
	/**
	 * Return the LevelConfig by its relative filename.
	 */
	public static LevelConfig getLevelConfigByFilename(String filename) {
		if(CodeEnvironment.isJar) {
			return Loader.levelConfigs.get(filename);
		} else {
			return Loader.levelConfigs.get(filename.replace("/", File.separator));
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
			if(e.endsWith(".class")) {
				if(GameConfig.DEBUG)
					System.out.println("Loading Class for: " + e);
				Loader.preloadClassByFilename(e);
			}
			if(e.endsWith(".png")) {
				if(GameConfig.DEBUG)
					System.out.println("Creating BufferedImage for: " + e);
				Loader.addBufferedImageByFilename(e);
			}
			if(e.endsWith(".wav")) {
				if(GameConfig.DEBUG)
					System.out.println("Creating AudioInputStream for: " + e);
				Loader.addSoundURLByFilename(e);
			}
			if(e.endsWith(".level")) {
				if(GameConfig.DEBUG) {
					System.out.println("Creating LevelConfig for: " + e);
				}
				Loader.addLevelByFilename(e);
			}
			loadingScreen.increaseCurrentProcessed();
		}
	}


	/**
	 * Preload a LevelConfig by simply parsing it into a LevelConfig object.
	 */
	private static void addLevelByFilename(String levelFilename) {
		InputStream foo = Loader.class.getClassLoader().getResourceAsStream(levelFilename);
		LevelConfig levelConfig = Loader.levelConfigParser.parse(foo);
		Loader.levelConfigs.put(levelFilename, levelConfig);
	}

	/**
	 * Preload a given class by its filename using the ClassLoader.
	 * This way, we avoid reading out of a jar later.
	 */
	private static void preloadClassByFilename(String classFilename) {
		if(classFilename.contains("$")) return; //skip anonymous classes
		String className = classFilename.replace(".class", "").replace(File.separator, ".");
		try {
			Class.forName(className);
		} catch (ClassNotFoundException e) {
			System.err.println("Error preloading class: " + classFilename);
			System.err.println("> Resulting class name: " + className);
			e.printStackTrace();
		}
	}

	/**
	 * Load a BufferedImage by relative filename, creating a compatible 
	 * BufferedImage using the GraphicsSettings ColorModel.
	 */
	private static void addBufferedImageByFilename(String filename) {
		try {
			BufferedImage storedImage = ImageIO.read(Loader.class.getClassLoader().getResourceAsStream(filename));
			BufferedImage image = Loader.createCompatibleImage(storedImage);
			Loader.images.put(filename, image);
		} catch (Exception e) {
			System.err.println("Unable to load BufferedImage: " + filename);
			e.printStackTrace();
		}

	}

	/**
	 * Helper method creating a compatible BufferedImage from a 
	 * given BufferedImage taking into account the current ColorModel of the
	 * current ScreenDevice.
	 */
	private static BufferedImage createCompatibleImage(BufferedImage storedImage) {
		if(GraphicsSettings.getInstance().getColorModel().equals(storedImage.getColorModel())) {
			return storedImage;
		} else {
			return GraphicsSettings.getInstance().createCompatibleBufferedImage(storedImage);
		}
	}


	/**
	 * Load an AudioInputStream by relative filename.
	 */
	private static void addSoundURLByFilename(String filename) {
		try {
			URL soundURL = Loader.class.getClassLoader().getResource(filename);
			//make sure the sound is in a valid AudioFormat
			AudioInputStream sound = SoundSystem.getAudioInputStreamByURL(soundURL);
			sound.close();
			Loader.sounds.put(filename, soundURL);
		} catch (IOException e) {
			System.err.println("Unable to load AudioInputStream: " + filename);
			e.printStackTrace();
		} catch (UnsupportedAudioFileException e) {
			System.err.println("Unsupported AudioFormat in file: " + filename);
			e.printStackTrace();
		}
	}
}
