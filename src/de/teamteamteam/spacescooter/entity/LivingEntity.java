package de.teamteamteam.spacescooter.entity;

import de.teamteamteam.spacescooter.screen.Screen;

public abstract class LivingEntity extends Entity {

	public LivingEntity(int x, int y) {
		super(x, y);
	}

	private int healthPoints;
	private int shieldPoints;
	
	public boolean isAlive() {
		return healthPoints > 0;
	}
	
	public void takeDamage(int damage) {
		//TODO: shield and health logic
		this.healthPoints -= damage;
		if(this.isAlive() == false) {
			System.out.println(this + " ist gestorben. RIP");
			Screen.currentScreen.removeEntity(this);
		}
	}
	
	public void setHealthPoints(int hp) {
		this.healthPoints = hp;
	}
	
	public int getHealthPoints() {
		return this.healthPoints;
	}

	public void setShieldPoints(int sp) {
		this.shieldPoints = sp;
	}
	
	public int getShieldPoints() {
		return this.shieldPoints;
	}

}
