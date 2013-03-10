package com.stealthyone.bukkit.todolist.java;

import java.util.List;

public final class StringUtils {

	/**
	 * Compares a string to a value and returns true if any comparison equals the main input
	 * @param main - main string to check
	 * @param comparisons - what to compare to string
	 * @return - true/false
	 */
	public final static boolean compareMultiple(String main, List<String> comparisons) {
		for (int i = 0; i < comparisons.size(); i++) {
			//Go through all comparisons
			if (main.equalsIgnoreCase(comparisons.get(i))) {
				//Comparison matches
				return true;
			}
		}
		//No comparisons match
		return false;
	}
	
	/* Converts a string array into a proper english list */
	public final static String makeProperList(String[] items) {
		String returnString = "";
		
		for (int i = 0; i < items.length; i++) {
			if (i == items.length - 1 && items.length > 1) {
				if (items.length > 2) {
					returnString += ", and ";
				} else {
					returnString += " and ";
				}
			} else if (i != 0 && items.length > 2) {
				returnString += ", ";
			}
			returnString += items[i];
		}
		
		return returnString;
	}
	
	/* Checks if any specified strings match the main string */
	public final static boolean equalsIgnoreCaseMultiple(String main, List<String> comparisons) {
		for (int i = 0; i < comparisons.size(); i++) {
			if (main.equalsIgnoreCase(comparisons.get(i))) {
				return true;
			}
		}
		return false;
	}
}