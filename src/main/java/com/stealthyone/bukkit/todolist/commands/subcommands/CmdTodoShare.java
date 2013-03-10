package com.stealthyone.bukkit.todolist.commands.subcommands;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.bukkit.command.CommandSender;

import com.stealthyone.bukkit.todolist.BasePlugin;
import com.stealthyone.bukkit.todolist.messages.GenericMessage;
import com.stealthyone.bukkit.todolist.storage.ListFileManager;

public final class CmdTodoShare implements ISubCmd {
	
	private BasePlugin plugin;
	
	public CmdTodoShare(BasePlugin plugin) {
		this.plugin = plugin;
	}

	@Override
	public void run(CommandSender sender, String[] args) {
		ListFileManager storage = plugin.getStorageManager();
		
		int taskNum;
		try {
			taskNum = Integer.valueOf(args[1]);
		} catch (NumberFormatException e) {
			GenericMessage.ITEM_MUST_BE_INT.sendTo(sender, Arrays.asList("{ITEM}|Task ID"));
			return;
		}
		List<String> otherPlayers = new ArrayList<String>();
		
		for (int i = 2; i < args.length; i++) {
			otherPlayers.add(args[2]);
		}
		
		storage.shareTask(sender, taskNum, otherPlayers);
		return;
	}

}
