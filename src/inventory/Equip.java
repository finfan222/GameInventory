/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package inventory;

import inventory.enums.EquipPaperdoll;

/**
 * Equip component realization
 * @author FinFan
 */
public class Equip {

	private final Paperdoll[] slots = new Paperdoll[EquipPaperdoll.values().length];

	public Equip() {
		for (int i = 0; i < slots.length; i++) {
			slots[i] = new Paperdoll(EquipPaperdoll.values()[i]);
		}
	}

	public Item getItemInSlot(EquipPaperdoll paperdoll) {
		return slots[paperdoll.ordinal()].item;
	}

	public Item unequip(EquipPaperdoll paperdoll) {
		return slots[paperdoll.ordinal()].item = null;
	}

	public Item equip(Item item) {
		if(item.slot == null) {
			Main.debug("Item ", item, "can't be equipped!");
			return null;
		}
		
		return slots[item.slot.ordinal()].item = item;
	}

	public int sizeOfEquippedItems() {
		int i = 0;
		for (Paperdoll doll : slots) {
			if (doll.item != null) {
				i++;
			}
		}
		return i;
	}

	public Item[] getAllEquippedItems() {
		Item[] items = new Item[sizeOfEquippedItems()];
		for (int i = 0; i < slots.length; i++) {
			if (slots[i].item != null) {
				items[i] = slots[i].item;
			}
		}
		return items;
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("############### EQUIPMENT\n");
		for (Paperdoll slot : slots) {
			if (slot.item != null) {
				sb.append(slot).append("\n");
			}
		}
		return sb.toString();
	}
}
