package de.teamteamteam.spacescooter.entity.item;

import de.teamteamteam.spacescooter.datastructure.ConcurrentIterator;
import de.teamteamteam.spacescooter.entity.CollidableEntity;
import de.teamteamteam.spacescooter.entity.Entity;
import de.teamteamteam.spacescooter.entity.Player;
import de.teamteamteam.spacescooter.entity.spi.Collidable;
import de.teamteamteam.spacescooter.screen.Screen;
import de.teamteamteam.spacescooter.sound.SoundSystem;

public abstract class Item extends CollidableEntity {
	
	private ConcurrentIterator<Entity> entityIterator;
	
	
	public Item(int x, int y) {
		super(x, y);
		this.entityIterator = Screen.currentScreen.createEntityIterator();
	}

	/**
	 * If an item collides with a player, play the powerup pickup sound and remove the item.
	 */
	public void collideWith(Collidable entity) {
		if(entity instanceof Player) {
			SoundSystem.playSound("sounds/powerup_pickup.wav");
			while(entityIterator.hasNext()) {
				Entity e = entityIterator.next();
				if(e instanceof Player){
					itemCollected((Player) e);
				}
			}
			this.remove();
		}
	}
	
	public void update(){
		this.transpose(-1, 0);
		if(this.getX() < 0-this.getWidth()){
			this.remove();
		};
		entityIterator.reset();
	}
	
	public abstract void itemCollected(Player player);
	
	public static void create(int x, int y){
		int auswahl = ItemChance.choose();
		switch (auswahl) {
		case 0:
			new TestItem1(x, y);
			break;
		case 1:
			new TestItem2(x, y);
			break;
		case 2:
			new TestItem3(x, y);
			break;
		case 3:
			new TestItem4(x, y);;
			break;
		}
	}
}
