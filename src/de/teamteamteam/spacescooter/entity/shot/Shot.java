package de.teamteamteam.spacescooter.entity.shot;

import de.teamteamteam.spacescooter.entity.LivingEntity;
import de.teamteamteam.spacescooter.utility.GameConfig;

public class Shot extends LivingEntity {

	public static final int RIGHT = 1;
	public static final int LEFT= -1;
	
	private int damageValue;
	protected int collisionCount;
	
	private int speed;
	private int direction;
	
	public Shot(int x, int y, int shootDirection, int shootSpeed, int damageValue, String filename) {
		super(x, y);
		this.direction = shootDirection;
		this.speed = shootSpeed;
		this.collisionCount = 1;
		this.damageValue = damageValue;
		super.setImage(filename);
		this.setPosition(this.x - this.getImage().getWidth() / 2, this.y - this.getImage().getHeight() / 2);
	}
	
    //public void setImage(String filename) {
        //super.setImage(filename);
        //this.setPosition(this.x - this.getImage().getWidth() / 2, this.y - this.getImage().getHeight() / 2);
    //}
    
	public int getDamageValue() {
		return this.damageValue;
	}
	
	public void setDamageValue(int dmg) {
		this.damageValue = dmg;
	}
	
	public void update() {
		this.x += this.direction * this.speed;
		//remove the shot in case it is out of the game window.
		if ((this.x + this.getWidth()) < 0 || this.x > GameConfig.windowWidth
				|| (this.y + this.getHeight()) < 0
				|| this.y > GameConfig.windowHeight) {
			this.remove();
		}
	}
	
}
