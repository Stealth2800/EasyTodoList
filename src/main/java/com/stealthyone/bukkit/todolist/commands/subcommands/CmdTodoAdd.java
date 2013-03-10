package com.stealthyone.bukkit.todolist.commands.subcommands;

import org.bukkit.command.CommandSender;

import com.stealthyone.bukkit.todolist.BasePlugin;
import com.stealthyone.bukkit.todolist.storage.ListFileManager;

public final class CmdTodoAdd implements ISubCmd {

	private BasePlugin plugin;
	
	public CmdTodoAdd(BasePlugin plugin) {
		this.plugin = plugin;
	}
	
	@Override
	public final void run(CommandSender sender, String[] args) {
		ListFileManager storage = plugin.getStorageManager();
		
		String taskMessage = "";
		for (int i = 1; i < args.length; i++) {
			taskMessage += args[i];
			if (i != args.length - 1) {
				taskMessage += " ";
			}
		}
		
		storage.addTask(sender, taskMessage);
		return;
	}
}
