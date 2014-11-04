package de.teamteamteam.spacescooter.utility;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Hashtable;

import javax.imageio.ImageIO;

import de.teamteamteam.spacescooter.screen.LoadingScreen;

public class Loader {

	private static Hashtable<String, BufferedImage> images;

	static {
		Loader.images = new Hashtable<String, BufferedImage>();
	}


	/**
	 * Private constructor, this class will never be instantiated.
	 */
	private Loader() {}
	
	
	/**
	 * Return the loaded BufferedImage by its filename.
	 */
	public static BufferedImage getBufferedImageByFilename(String filename) {
		if(CodeEnvironment.isJar()) {
			return Loader.images.get(filename);
		} else {
			return Loader.images.get(filename.replace("/", File.separator));
		}
	}
	
	/**
	 * Perform the initial loading of everything and show the progress on the given LoadingScreen.
	 */
	public static void load(LoadingScreen loadingScreen) {
		// get a list of stuff to load and create buffered images
		// and other things
		String[] elements = CodeEnvironment.getFileList();
		loadingScreen.initialize(0, elements.length);
		for(int i=0; i<elements.length; i++) {
			String e = elements[i];
			if(e.endsWith(".png")) {
				if (GameConfig.DEBUG) System.out.println("Creating BufferedImage for: " + e);
				Loader.addBufferedImageByFilename(e);
			}
			loadingScreen.increaseCurrentProcessed();
		}
	}
	
	/**
	 * Load a BufferedImage by filename.
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

}
