package com.stealthyone.bukkit.todolist.java;

import java.util.Map;

public final class MapUtils {

	public final static int getLocationInMap(Object object, Map<String, Object> map) {
		for (int i = 0; i < map.size(); i++) {
			if (map.get(Integer.toString(i)).equals(object)) {
				return i;
			}
		}
		return -1;
	}
}