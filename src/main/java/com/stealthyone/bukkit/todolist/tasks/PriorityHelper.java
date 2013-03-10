package com.stealthyone.bukkit.todolist.tasks;

import java.util.Map;

import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;

import com.stealthyone.bukkit.todolist.BasePlugin;
import com.stealthyone.bukkit.todolist.java.MapUtils;

public class PriorityHelper {

	/**
	 * getPriority outline:
	 * GOAL: Return the priority of the task for a specified player
	 */
	public final static int getPriority(CommandSender sender, String taskName) {
		FileConfiguration priorityConfig = BasePlugin.getInstance().getStorageManager().getPriorityFile().getConfig();
		
		String playerName = sender.getName().toLowerCase();
		
		Map<String, Object> playerPriorities;
		try {
			playerPriorities = priorityConfig.getConfigurationSection(playerName).getValues(true);
		} catch (NullPointerException e) {
			return -1;
		}
		
		int curPriority = MapUtils.getLocationInMap(taskName, playerPriorities);
		
		return curPriority;
	}
}