package de.teamteamteam.spacescooter.entity.item;

import de.teamteamteam.spacescooter.datastructure.ConcurrentIterator;
import de.teamteamteam.spacescooter.entity.Entity;
import de.teamteamteam.spacescooter.entity.LivingEntity;
import de.teamteamteam.spacescooter.entity.Player;
import de.teamteamteam.spacescooter.screen.Screen;

public abstract class Items extends LivingEntity {
	
	private ConcurrentIterator<Entity> entityIterator;
	
	public Items(int x, int y) {
		super(x, y);
		this.setHealthPoints(1);
		this.entityIterator = Screen.currentScreen.createEntityIterator();
	}

	public void update(){
		this.transpose(-1, 0);
		if(this.getX() < 0-getWidth()){
			this.remove();
		};
		if(!this.isAlive()){
			entityIterator.reset();
			while(entityIterator.hasNext()) {
				Entity e = entityIterator.next();
				if(e instanceof Player){
					itemCollected((Player) e);
				}
			}
		}
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
