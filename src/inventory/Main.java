/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package inventory;

import inventory.enums.EquipPaperdoll;

/**
 *
 * @author FinFan
 */
public class Main {

	/**
	 * @param args the command line arguments
	 * @throws java.lang.InterruptedException
	 */
	public static void main(String[] args) throws InterruptedException {
		// TODO code application logic here
		final Inventory inventory = new Inventory(new Equip(), new Container(120));
		
		Item sword = new Item(IdFactory.instance.createAndGet(), 1, "Sword of Bastard", EquipPaperdoll.RHAND);
		Item shield = new Item(IdFactory.instance.createAndGet(), 2, "Shield of Empire", EquipPaperdoll.LHAND);
		Item necklace = new Item(IdFactory.instance.createAndGet(), 3, "Necklace of Bitch", EquipPaperdoll.NECKLACE);
		Item chest = new Item(IdFactory.instance.createAndGet(), 4, "Iron Chest", EquipPaperdoll.CHEST);
		Item potions = new Item(IdFactory.instance.createAndGet(), 5, "Heal Potion", null);
		potions.isStackable = true;
		potions.count = 25;
		
		inventory.container.add(sword,shield,necklace,chest,potions);
		
		Item[] random = new Item[150];
		for(int i = 0; i < 150; i++) {
			random[i] = new Item(IdFactory.instance.createAndGet(), 6 + i, "item" + i, null);
		}
		inventory.container.add(random);
		
		Thread.sleep(100);
		
		inventory.container.add(chest);
		
		inventory.container.getItemCount(5);
		
		debug(inventory.container);
		debug("ItemCounts: ", inventory.container.getItemCount(5));
	
		inventory.container.destroy(true, potions.objectId);
	}
	
	public static void debug(Object...args) {
		StringBuilder sb = new StringBuilder();
		for(Object next : args) {
			sb.append(String.valueOf(next));
		}
		System.out.println(sb.toString());
	}
}
