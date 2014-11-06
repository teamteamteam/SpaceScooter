package de.teamteamteam.spacescooter.entity.item;

import java.util.Random;

public class ItemChance {

	private static int summe = 0;
	private static int[] items;
	
	public ItemChance() {
		ItemChance.items = new int[4];
		items[0] = 4;
		items[1] = 3;
		items[2] = 2;
		items[3] = 1;

		for(int i=0; i<ItemChance.items.length; i++) {
			ItemChance.summe += ItemChance.items[i];
		}
	}
	
	public static int choose() {
		//dauerhaft
		Random random = new Random();
		int r = random.nextInt(ItemChance.summe - 1) + 1;
		
		for(int i=0; i<ItemChance.items.length; i++) {
			r -= ItemChance.items[i];
			if(r <= 0) {
				return i;
			}
		}
		return -1;		
	}
}
