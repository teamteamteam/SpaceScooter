package de.teamteamteam.spacescooter.entity.item;

import de.teamteamteam.spacescooter.brain.PlayerSession;
import de.teamteamteam.spacescooter.entity.CollidableEntity;
import de.teamteamteam.spacescooter.entity.Player;
import de.teamteamteam.spacescooter.entity.spi.Collidable;
import de.teamteamteam.spacescooter.sound.SoundSystem;
import de.teamteamteam.spacescooter.utility.Random;

/**
 * Abstract representation of an item.
 * Contains logic to spawn a random item at a location and passes
 * the collision with a player up to the itemCollected() method.
 */
public abstract class Item extends CollidableEntity {
	
	/**
	 * Default constructor.
	 */
	public Item(int x, int y) {
		super(x, y);
	}

	/**
	 * If an item collides with a player, play the powerup pickup sound and remove the item.
	 */
	public void collideWith(Collidable entity) {
		if(entity instanceof Player) {
			SoundSystem.playSound("sounds/powerup_pickup.wav");
			this.itemCollected((Player) entity);
			this.remove();
		}
	}
	
	/**
	 * Default update method for all items.
	 * They smoothly scroll along and vanish when they're off the screen.
	 */
	public void update(){
		this.transpose(-1, 0);
	}
	
	/**
	 * Item property.
	 * What happens when item is collected.
	 */
	public abstract void itemCollected(Player player);
	
	/**
	 * Spawns a random item using the weighted probabilities..
	 */
	public static void create(int x, int y){
		int i;
		int sum = 0;
		int choice = -1;
		//List of items with their weighted probabilities.
		int[] items = new int[6];
		items[0] = 1;	//ItemNuke
		items[1] = 4;	//ItemCredit
		items[2] = 3;	//ItemHeal
		items[3] = 2;	//ItemShield
		items[4] = 2;	//ItemRocket or ItemBeam
		items[5] = 3;	//ItemIncreaseDamage
		//Add them all up
		for(i=0; i<items.length; i++) {
			sum += items[i];
		}
		//Get a random number between 0 and sum
		int randomNumber = Random.nextInt(sum);
		//Check out which one is the current choice.
		for(i=0; i<items.length; i++) {
			if(randomNumber >= items[i]) {
				randomNumber -= items[i];
			} else {
				//A choice has been made. Break away now.
				choice = i;
				break;
			}
		}
		//Actually spawn the random item now
		switch (choice) {
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
				if(PlayerSession.getSecondsecondaryWeapon() == 1){
					new ItemRocket(x, y);
				}else{
					new ItemBeam(x, y);
				}
				break;
			case 5:
				new ItemIncreaseDamage(x, y);
				break;
			default:
				System.err.println("Could not determine which item to spawn!");
				break;
		}
	}
}
