package de.teamteamteam.spacescooter.entity.enemy;

import java.awt.Point;
import java.util.ArrayList;

import de.teamteamteam.spacescooter.entity.explosion.ExplosionOne;

public class EnemyFour extends Enemy{
	
	private ArrayList<Point> points;
	private int index =0;
	private Point nextPoint;
	private double x;
	private double y;
	private double vektorX;
	private double vektorY;

	public EnemyFour(int x, int y, ArrayList<Point> points) {
		super(x, y);
		this.setImage("images/enemy01.png");
		this.setPrimaryShotImage("images/shots/laser_yellow.png");
		this.setShootSpeed(4);
		this.setShootDelay(42);
		this.setShootSpawn(-10, 10);
		this.setShootDamage(5);
		this.setCollisionDamage(5);
		this.setHealthPoints(5);
		this.setScore(40);
		this.points = points;
		this.x = x;
		this.y = y;
		this.setCollisionDamage(this.getHealthPoints());
		getNextPoint();
	}
	
	@Override
	public void update() {
		super.update();
		
		this.x -= vektorX;
		this.y -= vektorY;
		this.setPosition((int)x, (int)y);
		
		if(this.getX() == (int)nextPoint.getX() && this.getY() == (int)nextPoint.getY()){
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
		}catch(IndexOutOfBoundsException e){
			this.remove();
		}
	}

	@Override
	public void explode() {
		new ExplosionOne(this.getX(), this.getY());
	}
	
}
