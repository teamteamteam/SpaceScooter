package de.teamteamteam.spacescooter.screen;

import java.awt.Graphics2D;
import java.awt.event.KeyEvent;

import de.teamteamteam.spacescooter.control.Keyboard;
import de.teamteamteam.spacescooter.entity.Player;
import de.teamteamteam.spacescooter.gui.HealthBar;
import de.teamteamteam.spacescooter.gui.InterfaceBar;
import de.teamteamteam.spacescooter.gui.ScoreBar;
import de.teamteamteam.spacescooter.gui.ShieldBar;
import de.teamteamteam.spacescooter.level.Level;
import de.teamteamteam.spacescooter.sound.SoundSystem;
import de.teamteamteam.spacescooter.utility.CollisionHandler;

/**
 * In this GameScreen, the actual gameplay takes place.
 * All Entities are updated and painted by it.
 * Also, it offers the GamePausedScreen when the user presses VK_ESCAPE.
 * When the Player died in the game, the GameOverScreen replaces this Screen.
 */
public class GameScreen extends Screen {

	private static Player player;
	
	/**
	 * Level instance to handle all the stuff based on its LevelConfig.
	 */
	private Level level;
	
	/**
	 * Internal counter that has to match a certain modulo to trigger the Level class within update().
	 */
	private long gameClockTrigger;
	
	/**
	 * Internal Thread handle for the background music.
	 */
	private Thread backgroundMusic;
	
	/**
	 * GameScreen Constructor.
	 * Takes the level as its second parameter.
	 */
	public GameScreen(Screen parent, String levelConfigName) {
		super(parent);
		this.level = new Level(levelConfigName);
		this.level.doBuildUp(); //Have the level build up the whole setting.
		
		this.gameClockTrigger = 0;
		
		//Basic UI buildup - it's the same across all levels.
		new InterfaceBar(0, 0);
		new HealthBar(10, 5);
		new ShieldBar(10, 27);
		new ScoreBar(575, 33);
		
		this.backgroundMusic = SoundSystem.playSound("music/ScooterFriendsTurbo8Bit.wav");
	}

	
	/**
	 * This part is simple: Paint all the Entities!
	 */
	@Override
	protected void paint(Graphics2D g) {
		this.entityPaintIterator.reset();
		while (this.entityPaintIterator.hasNext()) {
			this.entityPaintIterator.next().paint(g);
		}
	}

	/**
	 * Trigger level logic, trigger updates on all entities,
	 * take care of user input such as pressing escape and
	 * do a little check on the gameover condition.
	 */
	@Override
	protected void update() {
		this.gameClockTrigger++;
		//The level will take care of whatever happens next every second.
		if(this.gameClockTrigger % 100 == 0) {
			this.gameClockTrigger = 0;
			this.level.handleUpdateTick();
		}
		
		//Take care of the usual bussiness
		this.entityUpdateIterator.reset();
		while (this.entityUpdateIterator.hasNext()) {
			this.entityUpdateIterator.next().update();
		}
		// Pass the collision handler a copy of the entity list
		CollisionHandler.handleCollisions();
		if (Keyboard.isKeyDown(KeyEvent.VK_ESCAPE)) {
			this.setOverlay(new GamePausedScreen(this));
		}
		
		//Go to GameOverScreen if the game is actually over.
		if (this.level.isGameOver()) {
			this.parent.setOverlay(new GameOverScreen(this.parent));
		}
	}
	
	/**
	 * Cleanup method to stop the background music.
	 */
	@Override
	public void cleanup() {
		this.backgroundMusic.interrupt();
		super.cleanup();
	}
	
	
	public static Player getPlayer() {
		return GameScreen.player;
	}
	
	public static void setPlayer(Player player) {
		GameScreen.player = player;
	}
	
}

