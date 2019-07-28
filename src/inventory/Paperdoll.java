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
public class Paperdoll {
	public final EquipPaperdoll paperdoll;
	public Item item;

	public Paperdoll(EquipPaperdoll paperdoll) {
		this.paperdoll = paperdoll;
		this.item = null;
	}

	@Override
	public String toString() {
		return new StringBuilder().append(paperdoll).append(": ").append(item).toString();
	}
}
