/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package inventory;

/**
 * Добавление вещи происходит по свободному слоту freeSlots;<br>
 * После каждого добавления мы рекалькулируем слоты freeSlots и получаем в нём -
 * массив индексов которые свободны и гогтовы к использованию;<br>
 * При удалении итема (итем зранит в себе storageIndex) мы освобождаем слот в
 * котормо лежал итем;<br>
 *
 * @author FinFan
 */
public class Container {

	private Item[] items;
	public int[] freeSlots;

	public Container(final int size) {
		this.items = new Item[size];
		this.freeSlots = new int[size];
	}

	/**
	 * Copy old items in a new array with new size
	 * @param newSize 
	 */
	public void resize(int newSize) {
		Main.debug("Resize the items...");
		Main.debug("Old size: ", items.length);
		Item[] newItems = new Item[newSize];
		System.arraycopy(items, 0, newItems, 0, newSize);
		items = newItems;
		Main.debug("New size: ", items.length);
	}
	
	private void recalcFreeSlots() {
		// get the size for future free slots
		int size = 0;
		for (int i = 0; i < items.length; i++) {
			if (items[i] == null) {
				size++;
			}
		}

		// fill free slots by free indexes in container
		freeSlots = new int[size];
		for (int i = 0, j = 0; i < items.length; i++) {
			if (items[i] == null) {
				freeSlots[j++] = i;
			}
		}
	}

	public boolean hasSpace() {
		return freeSlots.length > 0;
	}

	public Item getItem(int id) {
		return getItem(id, false);
	}

	public Item getItem(int id, boolean byObjectId) {

		for (int i = 0; i < items.length; i++) {
			final Item item = items[i];
			if (item != null) {
				final int tempId = byObjectId ? item.objectId : item.id;
				if (tempId == id) {
					return item;
				}
			}
		}

		return null;
	}

	public boolean hasItem(int id) {
		return hasItem(id, false);
	}

	public boolean hasItem(int id, boolean byObjectId) {
		return getItem(id, byObjectId) != null;
	}

	public boolean add(Item... item) {
		for (Item next : item) {
			if (!hasSpace()) {
				Main.debug("Not enought space.");
				return false;
			}

			if (next.storageIndex > -1) {
				Main.debug("Index ", next.storageIndex, " already busy!");
				return false;
			}

			for (int i = 0; i < freeSlots.length; i++) {
				final int freeSlotIndex = freeSlots[i];
				next.storageIndex = freeSlotIndex;
				items[freeSlotIndex] = next;
				recalcFreeSlots();
				break;
			}
		}
		return true;
	}

	public boolean remove(int id) {
		return remove(id, false);
	}
	
	public boolean remove(int id, boolean byObjectId) {
		final Item removeable = getItem(id, byObjectId);
		if (removeable == null) {
			Main.debug("Item with ID ", id, " not found in invetory!");
			return false;
		}

		if (removeable.storageIndex < 0) {
			Main.debug("Cannot remove ", removeable, " cause container index < 0");
			return false;
		}

		//remove item
		items[removeable.storageIndex] = null;

		// set index to -1
		removeable.storageIndex = -1;
		Main.debug("Item with ", id, " was removed!");
		recalcFreeSlots();
		return true;
	}

	public int getItemCount(int id) {
		Item item = getItem(id);
		if (item == null) {
			return 0;
		}

		if (!item.isStackable) {
			Main.debug("Item ", item, " is not stackable");
			return 0;
		}

		return item.count;
	}

	public void destroy(int id) {
		destroy(false, id, 1);
	}
	
	public void destroy(boolean byObjectId, int id) {
		destroy(byObjectId, id, 1);
	}
	
	public void destroy(int id, int count) {
		destroy(false, id, count);
	}

	public void destroy(boolean byObjectId, int id, int count) {
		final Item removable = getItem(id, byObjectId);
		if(removable != null) {
			if(count > 1 && removable.isStackable) {
				final int newCnt = removable.count - count;
				if(newCnt > 0) {
					// decrease count
					removable.count = newCnt;
					Main.debug("Count of item ", removable, " reduced to ", removable.count);
				} else {
					// count < 1, remove item from container
					remove(id, byObjectId);
					Main.debug("Destroy ", removable, " success!");
				}
			} else {
				// item not stackable and cant be counted, just remove them
				remove(removable.objectId, byObjectId);
				Main.debug("Destroy ", removable, " success!");
			}
		}
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("Sotraged items:\n");
		for (int i = 0; i < items.length; i++) {
			final Item item = items[i];
			if (item != null) {
				sb.append("	#").append(i).append(": ").append(item.name).append(" (storage index: ").append(item.storageIndex).append(")").append("\n");
			}
		}
		sb.append("\n");
		return sb.toString();
	}
}
