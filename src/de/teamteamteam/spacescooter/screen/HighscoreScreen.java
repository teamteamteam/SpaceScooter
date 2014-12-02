package de.teamteamteam.spacescooter.screen;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

import de.teamteamteam.spacescooter.brain.GameConfig;
import de.teamteamteam.spacescooter.control.Keyboard;
import de.teamteamteam.spacescooter.entity.Player;
import de.teamteamteam.spacescooter.gui.Button;
import de.teamteamteam.spacescooter.utility.Loader;

public class HighscoreScreen extends Screen{

	private BufferedImage img;
	private float playerMoveSpeed = 0;
	private Player player;
	private int animationStatus = 0; //0 = Animation noch nicht gestartet, 1 = Animation laeuft, 2 = Animation beendet
	private ArrayList<String> eintraege = new ArrayList<String>();
	
	public HighscoreScreen(Screen parent) {
		super(parent);
		this.img = Loader.getBufferedImageByFilename("images/earthbackground.png");
		new Button(GameConfig.windowWidth/2-125, GameConfig.windowHeight-75);
		player = new Player(GameConfig.windowWidth/2-170, GameConfig.windowHeight-63);
		player.setCanMove(false);
		player.setCanShoot(false);
		readHighscore();
	}

	@Override
	protected void paint(Graphics2D g) {
		g.drawImage(this.img, 0, 0, null);
		this.entityPaintIterator.reset();
		while (this.entityPaintIterator.hasNext()) {
			this.entityPaintIterator.next().paint(g);
		}
		g.setFont(new Font("Monospace", 0, 20));
		g.setColor(new Color(255, 255, 255));
		for(int i = 0; i<eintraege.size(); i++){
			g.drawString(eintraege.get(i), 50, 27*(i+1));
		}
		g.setColor(new Color(0, 0, 0));
		g.drawString("Hauptmen\u00fc", GameConfig.windowWidth/2-55, GameConfig.windowHeight-40);
	}

	@Override
	protected void update() {
		if ((Keyboard.isKeyDown(KeyEvent.VK_ENTER) || Keyboard.isKeyDown(KeyEvent.VK_SPACE)) && this.animationStatus == 0) {
			this.animationStatus = 1;
		}
		if(this.animationStatus == 1) {
			if(this.player.getX() <= GameConfig.windowWidth) {
				this.player.setPosition(this.player.getX() + (int) playerMoveSpeed, this.player.getY());
				this.playerMoveSpeed += 0.1;
			} else this.animationStatus = 2;
		} else if(this.animationStatus == 2) {
			this.parent.setOverlay(new MainMenuScreen(this.parent));
		}
	}
	
	private void readHighscore(){
		try {
			File f = new File("save");
			if(!f.exists()){
				f.createNewFile();
			}
			Scanner scan = new Scanner(f);
			while(scan.hasNextLine()){
				this.eintraege.add(scan.nextLine());
			}
			scan.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
