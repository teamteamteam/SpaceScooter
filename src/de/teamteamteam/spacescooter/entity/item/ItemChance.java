package de.teamteamteam.spacescooter.entity.item;

import de.teamteamteam.spacescooter.utility.Random;

public class ItemChance {

	private static int summe = 0;
	private static int[] items;
	
	/**
	 * Item spawn probability,
	 * higher number, higher spawn rate,
	 * same number, same spawn rate.
	 * 
	 * New items must be registered here!!
	 */
	public ItemChance() {
		ItemChance.items = new int[6];
		items[0] = 1;	//ItemNuke
		items[2] = 2;	//ItemHeal
		items[3] = 2;	//ItemShield
		items[4] = 2;	//ItemRocket
		items[1] = 4;	//ItemCredit
		items[5] = 3;	//ItemIncreaseDamage

		for(int i=0; i<ItemChance.items.length; i++) {
			ItemChance.summe += ItemChance.items[i];
		}
	}
	
	public static int choose() {
		int r = Random.nextInt(ItemChance.summe - 1) + 1;
		
		for(int i=0; i<ItemChance.items.length; i++) {
			r -= ItemChance.items[i];
			if(r <= 0) {
				return i;
			}
		}
		return -1;		
	}
}
