package de.teamteamteam.spacescooter.level;

import java.io.InputStream;
import java.util.Scanner;

/**
 * To parse the LevelConfig, this parser will be used.
 * It reads the level config file using a scanner and creates a LevelConfig object
 * filled with all the information it could extract.
 */
public class LevelConfigParser {

	
	/**
	 * Internal LevelConfig instance to fill with data.
	 */
	private LevelConfig levelConfig;

	/**
	 * Internal Scanner instance on the current file.
	 */
	private Scanner scanner;

	/**
	 * Parse mode determining what to look out for.
	 */
	private int parseMode;
	
	/**
	 * The start of the last read interval.
	 */
	private int currentIntervalStart;

	/**
	 * The end of the last read interval.
	 */
	private int currentIntervalEnd;

	
	/**
	 * Constructor. Initialize most internals to a safe state.
	 */
	public LevelConfigParser() {
		this.reset();
	}


	/**
	 * Takes a given configFile and turns it into a LevelConfig object.
	 */
	public LevelConfig parse(InputStream configFile) {
		this.reset();
		this.prepareScanner(configFile);
		while (this.scanner.hasNextLine()) {
			String line = this.scanner.nextLine().trim(); // Get next line trimmed.
			if(line.startsWith("#") || line.equals("")) continue; // Skip comments and empty lines.
			if(line.equals("-"))  { //increase parse mode and continue.
				this.parseMode++;
				continue;
			}
			switch(parseMode) {
				case 0:
					// Handle configurable attributes
					if (line.contains(":")) {
						String[] linePieces = line.split(":", 2);
						// If the attribute is known, set it.
						if (linePieces[0].equals("name")) {
							this.levelConfig.name = linePieces[1];
						} else if (linePieces[0].equals("background")) {
							this.levelConfig.background = linePieces[1];
						} else if (linePieces[0].equals("backgroundMusic")) {
							this.levelConfig.backgroundMusic = linePieces[1];
						} else {
							throw new LevelConfigException("[LevelConfigParser] Unknown attribute in line: '" + line + "'");
						}
					}
					break;
				case 1:
					// Handle interval based rules
					if (line.startsWith("[") && line.endsWith("]")) { //Handle interval definition
						String interval = line.substring(1, line.length() - 1);
						String[] intervalBorder = interval.split("-", 2);
						this.currentIntervalStart = Integer.parseInt(intervalBorder[0]);
						this.currentIntervalEnd = Integer.parseInt(intervalBorder[1]);
						this.levelConfig.addIntervalToList(this.currentIntervalStart, this.currentIntervalEnd);
					} else {
						String[] rule = line.split(":", 2);
						if(rule[0].equals("spawn")) {
							String[] entitySpawnRule = rule[1].split(",", 4);
							this.levelConfig.addEntitySpawnRule(
								this.currentIntervalStart, 
								this.currentIntervalEnd, 
								entitySpawnRule[0], 
								Integer.parseInt(entitySpawnRule[1]), 
								Integer.parseInt(entitySpawnRule[2]), 
								Integer.parseInt(entitySpawnRule[3])
							);
						} else {
							throw new LevelConfigException("Unknown rule type: '"+rule[0]+"' : '"+line+"'");
						}
					}
					break;
				default:
					throw new LevelConfigException("[LevelConfigParser] Where am i?!");
			}
		}
		return this.levelConfig;
	}
	
	/**
	 * Prepares a Scanner for the file to parse.
	 */
	private void prepareScanner(InputStream configStream) {
		try {
			this.scanner = new Scanner(configStream);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * Reset internal parser state.
	 */
	private void reset() {
		this.parseMode = 0;
		this.levelConfig = new LevelConfig();
		this.currentIntervalStart = -1;
		this.currentIntervalEnd = -1;
		if(this.scanner != null) {
			this.scanner.close();
			this.scanner = null;
		}
	}

}
