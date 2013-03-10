package com.stealthyone.bukkit.todolist.storage;

import java.util.List;

import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;

import com.stealthyone.bukkit.todolist.BasePlugin;
import com.stealthyone.bukkit.todolist.PluginLogger;
import com.stealthyone.bukkit.todolist.utils.CustomFileManager;

public final class ListFileManager {

	private BasePlugin plugin;
	
	private CustomFileManager structureFile;
	private CustomFileManager taskFile;
	
	public ListFileManager(BasePlugin plugin) {
		this.plugin = plugin;
		
		//Setup files
		structureFile = new CustomFileManager(plugin, "structure");
		taskFile = new CustomFileManager(plugin, "tasks");
	}
	
	public final void addTask(CommandSender sender, String taskMessage) {
		/* Define variables for easy reference */
		PluginLogger log = plugin.getLog();		//Plugin logger for debugging purposes
		FileConfiguration structConfig = structureFile.getConfig();		//Easy reference to the structure config
		FileConfiguration taskConfig = taskFile.getConfig();		//Easy reference to the task config
		
		String playerName = sender.getName();		//Easy definition of the sender's name in lowercase
		int playerTaskCount = structConfig.getConfigurationSection(playerName + ".owned").getValues(false).size();		//the amount of tasks the player has registered
		int curTaskNum = playerTaskCount + 1;		//the current task number that we're dealing with
		
		/* Set values in task and structure files */
		
		//Define that the owner owns this ticket
		structConfig.set(playerName + ".owned." + curTaskNum, playerName + "." + curTaskNum);
		
		//Save the ticket to taskFile
		taskConfig.set(playerName + Integer.toString(curTaskNum), taskMessage);
		
		/* Save changes to files */
		structureFile.saveFile();
		taskFile.saveFile();
	}
	
	public final boolean shareTask(CommandSender sender, int ticketNum, List<String> otherPlayers) {
		/* Define variables for easy reference */
		PluginLogger log = plugin.getLog();
		FileConfiguration structConfig = structureFile.getConfig();
		FileConfiguration taskConfig = taskFile.getConfig();
		
		String ownerName = sender.getName();
		
		/* Make sure ticket isn't null */
		if (taskConfig.getString(ownerName + Integer.toString(ticketNum)) == null) {
			return false;
		}
		
		/* Update structure file with all people ticket is shared with */
		for (int i = 0; i < otherPlayers.size(); i++) {
			String targetPlayer = otherPlayers.get(i);
			int targetShareCount = structConfig.getConfigurationSection(targetPlayer + ".shared").getValues(false).size();
			structConfig.set(targetPlayer + ".shared." + Integer.toString(targetShareCount + 1), ticketNum);
		}
		return true;
	}
}
