package de.teamteamteam.spacescooter.entity.enemy;

import java.awt.Point;
import java.util.ArrayList;

import de.teamteamteam.spacescooter.entity.explosion.ExplosionOne;
import de.teamteamteam.spacescooter.entity.item.Item;
import de.teamteamteam.spacescooter.utility.Random;

public class EnemyFour extends Enemy{
	
	private ArrayList<Point> points;
	private int index =0;
	private Point nextPoint;
	private double x;
	private double y;
	private double vektorX;
	private double vektorY;
	private int speed = 4;

	public EnemyFour(int x, int y, ArrayList<Point> points) {
		super(x, y);
		this.setImage("images/enemy01.png");
		this.setPrimaryShotImage("images/shots/laser_yellow.png");
		this.setShootSpeed(4);
		this.setShootDelay(60);
		this.setCanShoot(false);
		this.setShootSpawn(-10, 10);
		this.setShootDamage(5);
		this.setCollisionDamage(5);
		this.setHealthPoints(20);
		this.setScore(40);
		this.points = points;
		this.x = x;
		this.y = y;
		getNextPoint();
	}
	
	@Override
	public void update() {
		super.update();
		
		this.x -= vektorX*speed;
		this.y -= vektorY*speed;
		this.setPosition((int)x, (int)y);
		
		if(this.getX()+2 >= (int)nextPoint.getX() && this.getX()-2 <= (int)nextPoint.getX()
				&& this.getY()+2 >= (int)nextPoint.getY() && this.getY()+1 <= (int)nextPoint.getY()){
			getNextPoint();
		}
	}
	
	private void neuerVektor(){
		vektorX = (this.getX() - nextPoint.getX());
		vektorY = (this.getY() - nextPoint.getY());
		double laenge = Math.sqrt(this.vektorX * this.vektorX + this.vektorY * this.vektorY);
		vektorX = vektorX/laenge;
		vektorY = vektorY/laenge;
	}
	
	private void getNextPoint(){
		try{
			nextPoint = points.get(index);
			index++;
			neuerVektor();
		}catch(IndexOutOfBoundsException e){}
	}

	@Override
	public void explode() {
		new ExplosionOne(this.getCenteredX(), this.getCenteredY());
	}
	
	/**
	 * This enemy spawns an Item on its death and causes another enemy to appear.
	 */
	@Override
	public void die() {
		if(Random.nextInt(100) < 5) Item.create(getX(), getY());
		super.die();
	}
	
}
