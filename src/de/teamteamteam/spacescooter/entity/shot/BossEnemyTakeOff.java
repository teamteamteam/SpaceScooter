package de.teamteamteam.spacescooter.entity.shot;

import java.awt.Point;
import java.util.ArrayList;

import de.teamteamteam.spacescooter.entity.Entity;
import de.teamteamteam.spacescooter.entity.enemy.EnemyFour;
import de.teamteamteam.spacescooter.gui.ImageEntity;
import de.teamteamteam.spacescooter.screen.GameScreen;

public class BossEnemyTakeOff extends Entity{

	private boolean hardmode;
	private ImageEntity image1;
	private ImageEntity image2;
	private ImageEntity image3;
	private ImageEntity image4;
	private ImageEntity imageEnemyFour;
	private int lifetime;
	private ArrayList<Point> points = new ArrayList<Point>();
	
	public BossEnemyTakeOff(int x, int y, boolean hardmode) {
		super(x, y);
		this.setImageDimensions(57, 399);
		this.hardmode = hardmode;
		this.image4 = new ImageEntity(x, y, "images/bossEnemyTakeOff4.png");
		this.imageEnemyFour = new ImageEntity(getCenteredX()-10, getCenteredY()-15, "images/enemy01.png");
		this.image2 = new ImageEntity(x, y, "images/bossEnemyTakeOff2.png");
		this.image3 = new ImageEntity(x, y, "images/bossEnemyTakeOff3.png");
		this.image1 = new ImageEntity(x, y, "images/bossEnemyTakeOff1.png");
	}
	
	@Override
	public void remove() {
		this.image1.remove();
		this.image2.remove();
		this.image3.remove();
		this.image4.remove();
		this.imageEnemyFour.remove();
		super.remove();
	}
	
	@Override
	public void update() {
		this.lifetime++;
		if(this.lifetime<40){
			this.image2.transpose(0, -1);
			this.image3.transpose(0, 1);
		}else if(this.lifetime == 50){
			points.add(new Point(GameScreen.getPlayer().getCenteredX(), GameScreen.getPlayer().getCenteredY()));
			new EnemyFour(getCenteredX()-10, getCenteredY()-15, points);
		}else if(this.lifetime == 65 && hardmode){
			points.remove(0);
			points.add(new Point(GameScreen.getPlayer().getCenteredX(), GameScreen.getPlayer().getCenteredY()));
			new EnemyFour(getCenteredX()-10, getCenteredY()-15, points);
		}else if(this.lifetime == 80){
			points.remove(0);
			points.add(new Point(GameScreen.getPlayer().getCenteredX(), GameScreen.getPlayer().getCenteredY()));
			new EnemyFour(getCenteredX()-10, getCenteredY()-15, points);
		}else if(this.lifetime == 95 && hardmode){
			points.remove(0);
			points.add(new Point(GameScreen.getPlayer().getCenteredX(), GameScreen.getPlayer().getCenteredY()));
			new EnemyFour(getCenteredX()-10, getCenteredY()-15, points);
		}else if(this.lifetime == 110){
			points.remove(0);
			points.add(new Point(GameScreen.getPlayer().getCenteredX(), GameScreen.getPlayer().getCenteredY()));
			if(!hardmode) imageEnemyFour.remove();
			new EnemyFour(getCenteredX()-10, getCenteredY()-15, points);
		}else if(this.lifetime == 125 && hardmode){
			points.remove(0);
			points.add(new Point(GameScreen.getPlayer().getCenteredX(), GameScreen.getPlayer().getCenteredY()));
			imageEnemyFour.remove();
			new EnemyFour(getCenteredX()-10, getCenteredY()-15, points);
		}else if(this.lifetime>160 && this.lifetime<200){
			this.image2.transpose(0, 1);
			this.image3.transpose(0, -1);
		}else if(this.lifetime == 200){
			this.remove();
		}
	}

	public void positionUpdate(int y_delta){
		this.image1.transpose(0, y_delta);
		this.image2.transpose(0, y_delta);
		this.image3.transpose(0, y_delta);
		this.image4.transpose(0, y_delta);
		this.imageEnemyFour.transpose(0, y_delta);
		this.transpose(0, y_delta);
	}
}
