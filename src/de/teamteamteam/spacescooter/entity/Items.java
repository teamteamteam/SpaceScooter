package de.teamteamteam.spacescooter.entity;

import java.util.LinkedList;
import de.teamteamteam.spacescooter.screen.Screen;

public abstract class Items extends LivingEntity{
	
	public Items(int x, int y) {
		super(x, y);
		this.setHealthPoints(1);
	}

	public void update(){
		this.setPosition(getX()-1, getY());
		if(this.getX() < 0-getWidth()){
			this.remove();
		};
		if(!this.isAlive()){
			LinkedList<Entity> entities = Screen.currentScreen.getEntities();
			for (Entity e : entities) {
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
			Screen.currentScreen.addEntity(new TestItem1(x, y));
			break;
		case 1:
			Screen.currentScreen.addEntity(new TestItem2(x, y));
			break;
		case 2:
			Screen.currentScreen.addEntity(new TestItem3(x, y));
			break;
		case 3:
			Screen.currentScreen.addEntity(new TestItem4(x, y));;
			break;
		}
	}
}
