package com.stealthyone.bukkit.todolist;

import java.util.List;

import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;

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
	
	public final void shareTask(String owner, List<String> otherPlayers) {
		
	}
}
