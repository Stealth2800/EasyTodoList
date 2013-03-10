package com.stealthyone.bukkit.todolist.java;

import java.util.Map;

import com.stealthyone.bukkit.todolist.BasePlugin;
import com.stealthyone.bukkit.todolist.PluginLogger;

public final class MapUtils {

	public final static int getLocationInMap(Object object, Map<String, Object> map) {
		PluginLogger log = BasePlugin.getInstance().getLog();
		
		log.debug("(getLocationInMap) looping through map");
		log.debug("(getLocationInMap) map.size(): " + map.size());
		for (int i = 1; i <= map.size(); i++) {
			log.debug("(getLocationInMap) i: " + i);
			try {
				if (map.get(Integer.toString(i)).equals(object.toString())) {
					log.debug("(getLocationInMap) returning i: " + i);
					return i;
				}
			} catch (NullPointerException e) {
				log.debug("(getLocationInMap) caught NPE");
				return -1;
			}
		}
		return map.size() + 1;
	}
}