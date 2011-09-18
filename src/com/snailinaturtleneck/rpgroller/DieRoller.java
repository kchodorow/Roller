package com.snailinaturtleneck.rpgroller;

/**
 * This class attempts to roll dice.  If invalid dice are given, it falls back on
 * rolling a d20.
 */
public class DieRoller {
	private static final int[] dieMap = new int[] { 20, 12, 10, 8, 6, 4 };
	
	public static int roll(int die) {
    	int num = (int)Math.ceil(Math.random()*dieMap[die]);
    	
    	// we have to special case if random == 0
    	if (num == 0) {
    		return 1;
    	}
    	else {
	    	return num;
    	}
	}
	
	public static int getD(int index) {
		return dieMap[index];
	}
}
