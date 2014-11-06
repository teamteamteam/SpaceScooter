package de.teamteamteam.spacescooter.entity;

import java.awt.Rectangle;
import java.util.List;

import de.teamteamteam.spacescooter.entity.enemy.Enemy;
import de.teamteamteam.spacescooter.entity.explosion.Explosion;
import de.teamteamteam.spacescooter.entity.item.Items;
import de.teamteamteam.spacescooter.entity.shot.Shot;
import de.teamteamteam.spacescooter.screen.Screen;
import de.teamteamteam.spacescooter.utility.GameConfig;

public abstract class LivingEntity extends Entity implements Collidable {

	public LivingEntity(int x, int y) {
		super(x, y);
	}

	private int collisionDamage;

	public Rectangle getCollisionBox() {
		return new Rectangle(this.getX(), this.getY(), this.getWidth(),
				this.getHeight());
	}

	public void update() {
		if(!(this instanceof ShootingEntity)) return; //Only check collisions for ShootingEntity.
		List<Entity> entities = Screen.currentScreen.getEntities();
		for (Entity e : entities) {
			if (e.equals(this)) //Do not collide with myself!
				continue;
			if(!(e instanceof Collidable)) //Do not collide with non-collidable!
				continue;
			Collidable ce = (Collidable) e;
			if (ce.getCollisionBox().intersects(this.getCollisionBox())) {
				this.collideWith(ce);
			}
		}
	}

	/**
	 * Handle collisions based on what we collide with.
	 */
	public void collideWith(Collidable entity) {
		//this instanceof ShootingEntity
		if(entity instanceof Shot) {
			Shot s = (Shot) entity;
			this.takeDamage(s.getDamageValue());
			s.takeDamage(this.getCollisionDamage());
		}
		if(entity instanceof Player && (!(this instanceof Player))) {
			Player player = (Player) entity;
			player.takeDamage(this.getCollisionDamage());
			this.takeDamage(player.getCollisionDamage());
		}
		if(entity instanceof Enemy && (!(this instanceof Enemy))) {
			Enemy enemy = (Enemy) entity;
			enemy.takeDamage(this.getCollisionDamage());
			this.takeDamage(enemy.getCollisionDamage());
		}
		if(this instanceof Player && entity instanceof Items){
			Items item = (Items) entity;
			item.setHealthPoints(0);
			item.remove();
		}
	}

	public void setCollisionDamage(int d) {
		this.collisionDamage = d;
	}

	public int getCollisionDamage() {
		return this.collisionDamage;
	}

	private int healthPoints;
	private int shieldPoints;

	public boolean isAlive() {
		return healthPoints > 0;
	}

	public void takeDamage(int damage) {
		if(this instanceof Shot) {
			if(GameConfig.DEBUG) System.out.println("Shot took damage: " + damage + "left: "+this.getHealthPoints()+" (" + this + ")");
		}
		// TODO: shield and health logic
		this.healthPoints -= damage;
		if (this.isAlive() == false) {
			if(GameConfig.DEBUG) System.out.println(this + " ist gestorben. RIP");
			this.explode();
			this.remove();
		}
	}

	public void explode() {
		new Explosion(this.x, this.y);
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
