package de.teamteamteam.spacescooter.entity.shot;


public class BossShot extends Shot{
	
	private double x;
	private double y;
	private double vektorX;
	private double vektorY;
	private int speed = 5;
	private int counter;
	private int mode;
	
	public BossShot(int x, int y, int counter, int mode) {
		super(x, y, Shot.LEFT, 0, 2, "images/shots/ballshot.png");
		this.counter = counter;
		this.x = x;
		this.y = y;
		this.mode = mode;
		neuerVektor();
	}
	
	@Override
	public void update() {
		super.update();
		
		this.x -= this.vektorX*this.speed;
		this.y -= this.vektorY*this.speed;
		this.setPosition((int)this.x, (int)this.y);
	}
	
	private void neuerVektor(){
		this.vektorX = (this.getX());
		if(mode == 0) this.vektorY = (this.getY() - (600-(counter*5)));
		else this.vektorY = (this.getY() - (60+(counter*5)));
		double laenge = Math.sqrt(this.vektorX * this.vektorX + this.vektorY * this.vektorY);
		this.vektorX = vektorX/laenge;
		this.vektorY = vektorY/laenge;
	}
}
