package de.teamteamteam.spacescooter.utility;

import java.awt.DisplayMode;
import java.awt.GraphicsDevice;
import java.awt.GraphicsEnvironment;

public class GraphicsSettings {
	
	private int height;
	private int width;
	private int refreshRate;
	private int bitDepth;
	
	public GraphicsSettings() {
		this.retrieveSettings();
	}
	
	public int getRefreshRate() {
		return this.refreshRate;
	}
	
	public void retrieveSettings() {
		GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
		GraphicsDevice[] gs = ge.getScreenDevices();
		
		for(int i=0; i < gs.length; i++) {
			DisplayMode dm = gs[i].getDisplayMode();
			this.refreshRate = dm.getRefreshRate();
			if(this.refreshRate == DisplayMode.REFRESH_RATE_UNKNOWN) {
				System.err.println("[!]Display Mode " + i + ": Unknown refresh rate!");
				this.refreshRate = 60; //sane default value. :/
			}
			this.bitDepth = dm.getBitDepth();
			this.height = dm.getHeight();
			this.width = dm.getWidth();
			
			if(GameConfig.DEBUG) {
				System.out.println("Display Mode " + i + ": " + this.width + "x" + this.height+ "@" + this.refreshRate + "Hz, " + this.bitDepth + " bit");
				System.out.println("OS: " + System.getProperty("os.name"));
			}
		}
	}
	
}
