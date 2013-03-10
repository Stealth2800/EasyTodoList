package com.stealthyone.bukkit.todolist.storage;

import java.util.List;

import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;

import com.stealthyone.bukkit.todolist.BasePlugin;
import com.stealthyone.bukkit.todolist.PluginLogger;
import com.stealthyone.bukkit.todolist.PluginMethods;
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
		structureFile.reloadConfig();
		structureFile.saveFile();
		taskFile.reloadConfig();
		taskFile.saveFile();
	}
	
	public final void addTask(CommandSender sender, String taskMessage) {
		/* Define variables for easy reference */
		PluginLogger log = plugin.getLog();		//Plugin logger for debugging purposes
		FileConfiguration structConfig = structureFile.getConfig();		//Easy reference to the structure config
		FileConfiguration taskConfig = taskFile.getConfig();		//Easy reference to the task config
		
		String playerName = sender.getName().toLowerCase();		//Easy definition of the sender's name in lowercase
		int playerTaskCount;
		try {
			playerTaskCount = structConfig.getConfigurationSection(playerName + ".owned").getValues(false).size();
		} catch (NullPointerException e) {
			playerTaskCount = 0;
		}
		
		int curTaskNum = playerTaskCount + 1;		//the current task number that we're dealing with
		
		/* Set values in task and structure files */
		
		//Define that the owner owns this ticket
		structConfig.set(playerName + ".owned." + curTaskNum, playerName + "." + curTaskNum);
		
		//Save the ticket to taskFile
		taskConfig.set(playerName + "." + Integer.toString(curTaskNum), taskMessage);
		
		/* Save changes to files */
		structureFile.saveFile();
		taskFile.saveFile();
		
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
	}
}
