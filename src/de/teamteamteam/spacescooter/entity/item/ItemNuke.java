package de.teamteamteam.spacescooter.entity.item;

import de.teamteamteam.spacescooter.datastructure.ConcurrentIterator;
import de.teamteamteam.spacescooter.entity.Entity;
import de.teamteamteam.spacescooter.entity.LivingEntity;
import de.teamteamteam.spacescooter.entity.Player;
import de.teamteamteam.spacescooter.entity.enemy.Enemy;
import de.teamteamteam.spacescooter.screen.Screen;

public class ItemNuke extends Item {

	
	private ConcurrentIterator<Entity> entityIterator;
	
	public ItemNuke(int x, int y) {
		super(x, y);
		this.setImage("images/items/itemNuke.png");
	}

	@Override
	public void itemCollected(Player player) {
		System.out.println("Gotta Nuke 'em All!");
		this.entityIterator = Screen.currentScreen.createEntityIterator();
		entityIterator.reset();
		while (entityIterator.hasNext()) {
			Entity entity = entityIterator.next();
			if(entity instanceof Enemy) {
				((LivingEntity) entity).takeDamage(20);
			}
		}
	}
}
