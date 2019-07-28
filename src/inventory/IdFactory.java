package inventory;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 * Simple id factory
 * @author FinFan
 */
public class IdFactory {
	public static final IdFactory instance = new IdFactory();
	
	private static final int VALUE = 100000;
	
	private int nextId;
	
	public int createAndGet() {
		if(nextId == Integer.MAX_VALUE || nextId < VALUE) {
			reset();
		}
		return nextId++;
	}
	
	private void reset() {
		nextId = VALUE;
	}
	
}
