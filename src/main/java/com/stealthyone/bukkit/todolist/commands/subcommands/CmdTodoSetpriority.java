package com.stealthyone.bukkit.todolist.commands.subcommands;

import java.util.Arrays;

import org.bukkit.command.CommandSender;

import com.stealthyone.bukkit.todolist.BasePlugin;
import com.stealthyone.bukkit.todolist.messages.GenericMessage;
import com.stealthyone.bukkit.todolist.tasks.TaskManager;

public final class CmdTodoSetpriority implements ISubCmd {

	private BasePlugin plugin;
	
	public CmdTodoSetpriority(BasePlugin plugin) {
		this.plugin = plugin;
	}

	@Override
	public void run(CommandSender sender, String[] args) {
		TaskManager storage = plugin.getStorageManager();
		
		try {
			Integer.valueOf(args[2]);
		} catch (NumberFormatException e) {
			GenericMessage.ITEM_MUST_BE_INT.sendTo(sender, Arrays.asList("{ITEM}|Priority"));
			return;
		}
		
		storage.setPriority(sender, args[1], Integer.valueOf(args[2]));
	}
}