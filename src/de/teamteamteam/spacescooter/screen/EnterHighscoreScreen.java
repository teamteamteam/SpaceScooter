package de.teamteamteam.spacescooter.screen;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.event.KeyEvent;

import de.teamteamteam.spacescooter.brain.GameConfig;
import de.teamteamteam.spacescooter.brain.Highscore;
import de.teamteamteam.spacescooter.brain.PlayerSession;
import de.teamteamteam.spacescooter.control.Keyboard;
import de.teamteamteam.spacescooter.gui.ImageEntity;

/**
 * This is the EnterHighscoreScreen, which is displayed once the player ended
 * the game by either winning (beating all levels) or losing.
 * Once a name is entered, the ViewHighscoreScreen is shown.
 */
public class EnterHighscoreScreen extends Screen {
	
	private ImageEntity cursor;
	private float cursorMoveSpeed = 0;
	private int colorValue = 0;
	private boolean colorValueIncrease = true;
	private int animationStatus = 0; //0 = Noch nicht gestartet, 1 = Animation läuft, 2 = Animation beendet

	private boolean keyPressed = false;
	private boolean uppercase = true;
	private Point cursorPosition = new Point(0, 0);
	private int score;
	private int enteredLetter = 0;
	private ImageEntity[] letters = new ImageEntity[10];
	private ImageEntity[] lettersbox = new ImageEntity[10];
	
	public EnterHighscoreScreen(Screen parent) {
		super(parent);
		new ImageEntity(0, 0, "images/shopbackground.png");
		this.score = PlayerSession.getScore();
		this.cursor = new ImageEntity(173, 293, "images/ship.png");
		for(int i = 0; i<10; i++){
			lettersbox[i] = new ImageEntity(150 + (i*50), 200, "images/letterbox.png");
		}
		PlayerSession.reset();
	}

	@Override
	protected void paint(Graphics2D g) {
		this.entityPaintIterator.reset();
		while (this.entityPaintIterator.hasNext()) {
			this.entityPaintIterator.next().paint(g);
		}
		g.setFont(new Font("Monospace", 0, 100));
		g.setColor(new Color(75 + colorValue, 175 + colorValue, 175 + colorValue));
		g.drawString("Game Over", GameConfig.windowWidth/2-290, 120);
		// Paint the screen to enter a name for the high score
		g.setFont(new Font("Monospace", 0, 30));
		g.setColor(Color.WHITE);
		if(uppercase){
			g.drawString("A         C    D    E         G", 210, 320);
			g.drawString("B                     F", 267, 320);
			g.drawString("H    I     J    K    L    M   N", 210, 380);
			g.drawString("O         Q         S    T    U", 210, 440);
			g.drawString("P          R", 267, 440);
			g.drawString("V                     Z", 210, 500);
			g.drawString("W   X    Y", 263, 500);
			g.setFont(new Font("Monospace", 0, 15));
			g.drawString("abc", 497, 493);
		}else{
			g.drawString("a    b    c    d    e", 210, 320);
			g.drawString("f    g", 505, 320);
			g.drawString("h                k    l    m   n", 210, 380);
			g.drawString("i     j", 273, 380);
			g.drawString("o    p    q    r     s    t    u", 210, 440);
			g.drawString("v", 210, 500);
			g.drawString("w    x    y    z", 264, 500);	
			g.setFont(new Font("Monospace", 0, 15));
			g.drawString("ABC", 496, 493);
		}
		g.setColor(Color.RED);
		g.drawString("del", 555, 493);
		g.setFont(new Font("Monospace", 0, 20));
		g.setColor(Color.GREEN);
		g.drawString("Fertig", GameConfig.gameScreenWidth/2-40, 555);
		g.drawString("Du bist auf dem " + (Highscore.getPlacement(score)+1) + ". Platz!", GameConfig.gameScreenWidth/2-130, 170);
		// Paint the normal gameover screen
	}

	@Override
	protected void update() {
		this.entityUpdateIterator.reset();
		while (this.entityUpdateIterator.hasNext()) {
			this.entityUpdateIterator.next().update();
		}
		if(this.colorValueIncrease) {
			this.colorValue += 2;
			if(this.colorValue > 70) this.colorValueIncrease = false;
		} else {
			this.colorValue -= 2;
			if(this.colorValue < -70) this.colorValueIncrease = true;
		}
		//Control while enter the name for the high score.
		if(Keyboard.isKeyDown(KeyEvent.VK_UP) && !this.keyPressed){
			this.keyPressed = true;
			if(this.cursorPosition.getY() > 0){
				this.cursorPosition.setLocation(this.cursorPosition.getX(), this.cursorPosition.getY() - 1);
				this.cursor.setPosition(173+(int)(this.cursorPosition.getX()*58), this.cursor.getY() - 60);
			}else if(cursorPosition.getY() == 4){
				this.cursorPosition.setLocation(this.cursorPosition.getX(), this.cursorPosition.getY() - 1);
				this.cursor.setPosition(173+(int)(this.cursorPosition.getX()*58), this.cursor.getY() - 60);
			}else{
				this.cursorPosition.setLocation(this.cursorPosition.getX(), 4);
				this.cursor.setPosition(325, 533);
			}
		}
		if(Keyboard.isKeyDown(KeyEvent.VK_DOWN) && !this.keyPressed){
			this.keyPressed = true;
			if(this.cursorPosition.getY() < 3){
				this.cursorPosition.setLocation(this.cursorPosition.getX(), this.cursorPosition.getY() + 1);
				this.cursor.setPosition(173+(int)(this.cursorPosition.getX()*58), this.cursor.getY() + 60);
			}else if(cursorPosition.getY() == 3){
				this.cursorPosition.setLocation(this.cursorPosition.getX(), this.cursorPosition.getY() + 1);
				this.cursor.setPosition(325, this.cursor.getY() + 60);
			}else{
				this.cursorPosition.setLocation(this.cursorPosition.getX(), 0);
				this.cursor.setPosition(173+(int)(this.cursorPosition.getX()*58), 293);
			}
		}
		if(Keyboard.isKeyDown(KeyEvent.VK_LEFT) && !this.keyPressed){
			this.keyPressed = true;
			if(cursorPosition.getY() !=4){
				if(this.cursorPosition.getX() > 0){
					this.cursorPosition.setLocation(this.cursorPosition.getX() - 1, this.cursorPosition.getY());
					this.cursor.setPosition(this.cursor.getX() - 58, this.cursor.getY());
				}else{
					this.cursorPosition.setLocation(6, this.cursorPosition.getY());
					this.cursor.setPosition(521, this.cursor.getY());
				}
			}
		}
		if(Keyboard.isKeyDown(KeyEvent.VK_RIGHT) && !this.keyPressed){
			this.keyPressed = true;
			if(cursorPosition.getY() !=4){
				if(this.cursorPosition.getX() < 6){
					this.cursorPosition.setLocation(this.cursorPosition.getX() + 1, this.cursorPosition.getY());
					this.cursor.setPosition(this.cursor.getX() + 58, this.cursor.getY());
				}else{
					this.cursorPosition.setLocation(0, this.cursorPosition.getY());
					this.cursor.setPosition(173, this.cursor.getY());
				}
			}
		}
		if((Keyboard.isKeyDown(KeyEvent.VK_ENTER) || Keyboard.isKeyDown(KeyEvent.VK_SPACE)) && !this.keyPressed){
			this.keyPressed = true;
			if(this.cursorPosition.equals(new Point(5, 3))){
				if(uppercase) uppercase = false;
				else uppercase = true;
			}else if(this.cursorPosition.equals(new Point(6, 3))){
				if(enteredLetter > 0){
					enteredLetter--; 
					letters[enteredLetter].remove();
				}
			}else if(cursorPosition.getY() == 4){
				String eingabeName = "";
				for(int i = 0; i<10; i++){
					if(letters[i] != null){
						eingabeName += letters[i].getText();
					}
				}
				//Make sure the player entered a name.
				if(eingabeName.equals("") == false) {
					Highscore.newScore(this.score, eingabeName);
					animationStatus = 1;
				}
			}else{
				if(this.enteredLetter < 10)	this.searchForChar();
			}
		}
		if(this.animationStatus == 1) {
			if(this.cursor.getX() <= GameConfig.windowWidth) {
				this.cursor.setPosition(this.cursor.getX() + (int) this.cursorMoveSpeed, this.cursor.getY());
				this.cursorMoveSpeed += 0.1;
			} else this.animationStatus = 2;
		} else if(this.animationStatus == 2) {
			//clean up and go to ViewHighscoreScreen
			this.cursor.remove();
			for(int i = 0; i<10; i++){
				if(lettersbox[i] != null) lettersbox[i].remove();
				if(letters[i] != null)letters[i].remove();
			}
			this.parent.setOverlay(new ViewHighscoreScreen(this.parent));
		}
		if(!Keyboard.isKeyDown(KeyEvent.VK_ENTER) && !Keyboard.isKeyDown(KeyEvent.VK_SPACE) && !Keyboard.isKeyDown(KeyEvent.VK_UP) && !Keyboard.isKeyDown(KeyEvent.VK_DOWN) && !Keyboard.isKeyDown(KeyEvent.VK_LEFT) && !Keyboard.isKeyDown(KeyEvent.VK_RIGHT)){
			this.keyPressed = false;
		}
	}

	private void searchForChar(){
		char eingabe = (char) ('A' + this.cursorPosition.getX()+this.cursorPosition.getY()*7);
		if(!this.uppercase){
			eingabe += 32;
		}
		if(eingabe == 'W'){
			letters[enteredLetter] = new ImageEntity(155+(50*this.enteredLetter), 230, null);
			letters[enteredLetter].drawString("" + eingabe, 30, Color.WHITE);
		}else if(eingabe == 'I' || eingabe =='J' || eingabe =='i' || eingabe =='j' || eingabe =='t' || eingabe =='l' || eingabe =='f'){
			letters[enteredLetter] = new ImageEntity(165+(50*this.enteredLetter), 230, null);
			letters[enteredLetter].drawString("" + eingabe, 30, Color.WHITE);
		}else if(eingabe == 'w'){
			letters[enteredLetter] = new ImageEntity(157+(50*this.enteredLetter), 230, null);
			letters[enteredLetter].drawString("" + eingabe, 30, Color.WHITE);
		}else{
			letters[enteredLetter] = new ImageEntity(161+(50*this.enteredLetter), 230, null);
			letters[enteredLetter].drawString("" + eingabe, 30, Color.WHITE);
		}
		enteredLetter++;
	}
	
}
