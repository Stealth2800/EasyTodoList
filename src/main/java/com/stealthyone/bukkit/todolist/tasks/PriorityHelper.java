package com.stealthyone.bukkit.todolist.tasks;

import java.util.Map;

import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;

import com.stealthyone.bukkit.todolist.BasePlugin;
import com.stealthyone.bukkit.todolist.java.MapUtils;

public class PriorityHelper {

	/*
	 * Returns the current priority of a ticket for a specified player
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
	
	/*
	 * Sets the priority of a player's task
	 */
	public final static void setPriority(CommandSender sender, String taskName, int newPriority) {
		/* Make sure player has access to the task */
		if (!playerOwnsTask(sender, taskName)) {
			sender.sendMessage(ChatColor.RED + "You do not own that!");
			return;
		}
		
		/* Set the old priority */
		String playerName = sender.getName().toLowerCase();
		
		FileConfiguration priorityConfig = BasePlugin.getInstance().getStorageManager().getPriorityFile().getConfig();
		
		int oldPriority = getPriority(sender, taskName);
		
		if (oldPriority == -1) {
			//Sender has no priorities, set this as first
			priorityConfig.set(playerName + ".1", taskName);
		} else {
			//Sender has priorities, set this as one and replace if necessary
			int priorityCount = priorityConfig.getConfigurationSection(playerName).getValues(false).size();
			if (newPriority > priorityCount) {
				priorityConfig.set(playerName + "." + Integer.toString(priorityCount + 1), taskName);
			} else {
				Map<String, Object> playerPriorities = priorityConfig.getConfigurationSection(playerName).getValues(true);
				//for (int i = 1; i <= priorityCount - newPriority; i++) {
				for (int i = priorityCount; i >= newPriority; i--) {
					String oldMessage = playerPriorities.get(Integer.toString(i)).toString();
					playerPriorities.put(Integer.toString(i) + 1, oldMessage);
				}
			}
		}
	}
	
	/*
	 * Returns if a player has access to a specified task
	 */
	public final static boolean playerOwnsTask(CommandSender sender, String taskName) {
		FileConfiguration structConfig = BasePlugin.getInstance().getStorageManager().getStructureFile().getConfig();
		
		String playerName = sender.getName().toLowerCase();
		
		if (structConfig.getConfigurationSection(playerName) == null) {
			return false;
		} else {
			int shareCount = structConfig.getConfigurationSection(playerName + ".shared").getValues(false).size();
			
			for (int i = 0; i < shareCount; i++) {
				String compareTaskName = structConfig.getString(playerName + ".shared." + Integer.toString(i));
				if (compareTaskName.equalsIgnoreCase(taskName)) {
					return true;
				}
			}
			
		}
		return false;
	}
}