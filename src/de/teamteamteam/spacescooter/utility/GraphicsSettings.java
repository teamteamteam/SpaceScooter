package de.teamteamteam.spacescooter.utility;

import java.awt.DisplayMode;
import java.awt.Graphics2D;
import java.awt.GraphicsDevice;
import java.awt.GraphicsEnvironment;
import java.awt.image.BufferedImage;
import java.awt.image.ColorModel;

import de.teamteamteam.spacescooter.brain.GameConfig;

/**
 * GraphicsSettings allows to fetch the current graphical settings in order
 * to determine a sane refresh rate and the current screen resolution.
 */
public class GraphicsSettings {
	
	/**
	 * Height of the primary screen.
	 */
	private int height;

	/**
	 * Width of the primary screen.
	 */
	private int width;
	
	/**
	 * The set refreshRate in Hz.
	 */
	private int refreshRate;
	
	/**
	 * Available bitDepth. In case you get -1, do not worry. :-)
	 */
	private int bitDepth;
	
	/**
	 * ScreenDevice this game is supposed to be run on.
	 * Probably just the primary screen device.
	 */
	private GraphicsDevice screenDevice;
	
	/**
	 * Instance holder for GraphicsSettings.
	 */
	private static GraphicsSettings instance;
	
	
	/**
	 * Create the instance and store it in the instance holder.
	 */
	static {
		GraphicsSettings.instance = new GraphicsSettings();
	}
	
	/**
	 * Private Constructor. Just retrieve the settings once and 
	 * storing the instance in GraphicsSettings.instance.
	 */
	private GraphicsSettings() {
		this.retrieveSettings();
	}
	
	/**
	 * Returns the refresh rate to use.
	 */
	public int getRefreshRate() {
		return this.refreshRate;
	}
	
	/**
	 * This method fetches the current settings and refreshes the GraphicsSettings attributes.
	 * In case the current refresh rate could not be detected, it uses 60Hz as a default value.
	 */
	public void retrieveSettings() {
		if(GameConfig.DEBUG) {
			System.out.println("OS: " + System.getProperty("os.name"));
		}
		GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
		GraphicsDevice[] gs = ge.getScreenDevices();
		int refreshRate;
		//iterate over all available screen devices
		for(int i=0; i < gs.length; i++) {
			DisplayMode dm = gs[i].getDisplayMode();
			refreshRate = dm.getRefreshRate();
			//handle unknown refresh rate
			if(refreshRate == DisplayMode.REFRESH_RATE_UNKNOWN) {
				System.err.println("[!]Display Mode " + i + ": Unknown refresh rate!");
				refreshRate = 60; //sane default value. :/
			}
			//only store the values of the primary screen.
			if(i == 0) {
				this.refreshRate = refreshRate;
				this.bitDepth = dm.getBitDepth();
				this.height = dm.getHeight();
				this.width = dm.getWidth();
				this.screenDevice = gs[i]; 
			}
			if(GameConfig.DEBUG) {
				System.out.println("Display Mode " + i + ": " + this.width + "x" + this.height+ "@" + this.refreshRate + "Hz, " + this.bitDepth + " bit");
			}
		}
	}
	
	/**
	 * Get the ColorModel of the screen device the game is supposed to be run on.
	 */
	public ColorModel getColorModel() {
		return this.screenDevice.getDefaultConfiguration().getColorModel();
	}
	
	/**
	 * Creates a compatible BufferedImage from a given BufferedImage and returns it.
	 */
	public BufferedImage createCompatibleBufferedImage(BufferedImage originalImage) {
		BufferedImage compatibleImage = this.screenDevice.getDefaultConfiguration()
			.createCompatibleImage(originalImage.getWidth(), originalImage.getHeight(), originalImage.getTransparency());
		//Transfer the actual image content by simply drawing.
		Graphics2D g = (Graphics2D) compatibleImage.getGraphics();
		g.drawImage(originalImage, 0, 0, null);
		g.dispose();
		//We're done.
		return compatibleImage;
	}
	
	/**
	 * Get the instance of GraphicsSettings
	 */
	public static GraphicsSettings getInstance() {
		return GraphicsSettings.instance;
	}
}
