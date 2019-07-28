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
public class Item {
	public final int objectId;
	public final int id;
	public final String name;
	public final EquipPaperdoll slot;
	public int storageIndex;
	
	public boolean isStackable = false;
	public int count;

	public Item(int objectId, int id, String name, EquipPaperdoll slot) {
		this.objectId = objectId;
		this.id = id;
		this.name = name;
		this.storageIndex = -1;
		this.slot = slot;
	}

	@Override
	public String toString() {
		return name;
	}
}
