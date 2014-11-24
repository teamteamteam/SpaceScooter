package de.teamteamteam.spacescooter.entity.item;

import de.teamteamteam.spacescooter.entity.CollidableEntity;
import de.teamteamteam.spacescooter.entity.Player;
import de.teamteamteam.spacescooter.entity.spi.Collidable;
import de.teamteamteam.spacescooter.sound.SoundSystem;

public abstract class Item extends CollidableEntity {
	
	
	public Item(int x, int y) {
		super(x, y);
	}

	/**
	 * If an item collides with a player, play the powerup pickup sound and remove the item.
	 */
	public void collideWith(Collidable entity) {
		if(entity instanceof Player) {
			SoundSystem.playSound("sounds/powerup_pickup.wav");
			itemCollected((Player) entity);
			this.remove();
		}
	}
	
	public void update(){
		this.transpose(-1, 0);
		if(this.getX() < 0-this.getWidth()){
			this.remove();
		};
	}
	
	/**
	 * Item property.
	 * What happens when item is collected.
	 */
	public abstract void itemCollected(Player player);
	
	/**
	 * Selects which item spawns.
	 * 
	 * (If you add a new item, you must also add it in ItemChance.java)
	 */
	public static void create(int x, int y){
		int auswahl = ItemChance.choose();
		switch (auswahl) {
		case 0:
			new ItemNuke(x, y);
			break;
		case 1:
			new ItemCredit(x, y);
			break;
		case 2:
			new ItemHeal(x, y);
			break;
		case 3:
			new ItemShield(x, y);
			break;
		case 4:
			new ItemRocket(x, y);
			break;
		case 5:
			new ItemIncreaseDamage(x, y);
			break;
		}
	}
}
