package com.stealthyone.bukkit.todolist.tasks;

import java.util.List;
import java.util.Map;

import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;

import com.stealthyone.bukkit.todolist.BasePlugin;
import com.stealthyone.bukkit.todolist.PluginLogger;
import com.stealthyone.bukkit.todolist.PluginMethods;
import com.stealthyone.bukkit.todolist.java.MapUtils;
import com.stealthyone.bukkit.todolist.java.StringUtils;
import com.stealthyone.bukkit.todolist.utils.CustomFileManager;

public final class TaskManager {

	private BasePlugin plugin;
	
	private CustomFileManager structureFile;
	private CustomFileManager taskFile;
	private CustomFileManager priorityFile;
	
	public TaskManager(BasePlugin plugin) {
		this.plugin = plugin;
		
		//Setup files
		structureFile = new CustomFileManager(plugin, "structure");
		taskFile = new CustomFileManager(plugin, "tasks");
		priorityFile = new CustomFileManager(plugin, "priorities");
		structureFile.reloadConfig();
		structureFile.saveFile();
		taskFile.reloadConfig();
		taskFile.saveFile();
		priorityFile.reloadConfig();
		priorityFile.saveFile();
	}
	
	public final CustomFileManager getStructureFile() {
		return this.structureFile;
	}
	
	public final CustomFileManager getTaskFile() {
		return this.taskFile;
	}
	
	public final CustomFileManager getPriorityFile() {
		return this.priorityFile;
	}
	
	public final void addTask(CommandSender sender, String taskMessage) {
		/* Define variables for easy reference */
		PluginLogger log = plugin.getLog();		//Plugin logger for debugging purposes
		FileConfiguration structConfig = structureFile.getConfig();		//Easy reference to the structure config
		FileConfiguration taskConfig = taskFile.getConfig();		//Easy reference to the task config
		FileConfiguration priorityConfig = priorityFile.getConfig();
		
		String playerName = sender.getName().toLowerCase();		//Easy definition of the sender's name in lowercase
		int playerTaskCount;
		try {
			playerTaskCount = taskConfig.getConfigurationSection(playerName).getValues(false).size();
		} catch (NullPointerException e) {
			playerTaskCount = 0;
		}
		
		int curTaskNum = playerTaskCount + 1;		//the current task number that we're dealing with
		
		/* Set values in task and structure files */
		
		//Save the ticket to taskFile
		taskConfig.set(playerName + "." + Integer.toString(curTaskNum), taskMessage);
		
		/* Save changes to files */
		structureFile.saveFile();
		taskFile.saveFile();
		
		/* Set the priority */
		PriorityHelper.setPriority(sender, playerName + "." + Integer.toString(curTaskNum), 1);
		priorityFile.saveFile();
		
		PluginMethods.sendTaggedMessage(sender, "Successfully added a task to your todo list! You now have a total of " + Integer.toString(curTaskNum) + " tasks.");
	}
	
	public final void shareTask(CommandSender sender, int ticketNum, List<String> otherPlayers) {
		/* Define variables for easy reference */
		FileConfiguration structConfig = structureFile.getConfig();
		FileConfiguration taskConfig = taskFile.getConfig();
		
		String ownerName = sender.getName().toLowerCase();
		
		/* Make sure ticket isn't null */
		if (taskConfig.getString(ownerName + "." + Integer.toString(ticketNum)) == null) {
			return;
		}
		
		/* Update structure file with all people ticket is shared with */
		for (int i = 0; i < otherPlayers.size(); i++) {
			String targetPlayer = otherPlayers.get(i).toLowerCase();
			int targetShareCount;
			try {
				targetShareCount = structConfig.getConfigurationSection(targetPlayer + ".shared").getValues(false).size();
			} catch (NullPointerException e) {
				targetShareCount = 0;
			}
			structConfig.set(targetPlayer + ".shared." + Integer.toString(targetShareCount + 1), ownerName + "." + ticketNum);
		}
		
		/* Save changes to files */
		structureFile.saveFile();
		taskFile.saveFile();
		
		PluginMethods.sendTaggedMessage(sender, "Successfully shared task with " + StringUtils.makeProperList(otherPlayers) + "!");
	}
}