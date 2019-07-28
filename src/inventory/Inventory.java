/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package inventory;

/**
 * Inventory component with 2 components of equip and container.
 * @author FinFan
 */
public class Inventory {
	public final Equip equip;
	public final Container container;

	public Inventory(Equip equip, Container container) {
		this.equip = equip;
		this.container = container;
	}
}
