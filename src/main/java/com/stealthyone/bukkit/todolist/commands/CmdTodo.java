package com.stealthyone.bukkit.todolist.commands;

import java.util.Arrays;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

import com.stealthyone.bukkit.todolist.BasePlugin;
import com.stealthyone.bukkit.todolist.PluginMethods;
import com.stealthyone.bukkit.todolist.commands.subcommands.CmdTodoAdd;
import com.stealthyone.bukkit.todolist.commands.subcommands.CmdTodoSetpriority;
import com.stealthyone.bukkit.todolist.commands.subcommands.CmdTodoShare;
import com.stealthyone.bukkit.todolist.commands.subcommands.ISubCmd;
import com.stealthyone.bukkit.todolist.messages.GenericMessage;
import com.stealthyone.bukkit.todolist.messages.UsageMessage;

public final class CmdTodo implements CommandExecutor {

	private BasePlugin plugin;
	
	public CmdTodo(BasePlugin plugin) {
		this.plugin = plugin;
	}

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		if (args.length == 0) {
			/* Show plugin info */
			sender.sendMessage(ChatColor.GOLD + plugin.getName() + ChatColor.GREEN + " v" + plugin.getVersion());
			sender.sendMessage(ChatColor.GOLD + "BukkitDev: " + ChatColor.GREEN + "http://google.com/");
			sender.sendMessage(ChatColor.DARK_GREEN + "For help, type " + ChatColor.GREEN + "/simplepromoter help");
			return true;
		} else {
			if (args[0].equalsIgnoreCase("add")) {
				if (args.length < 2) {
					/* Show usage */
					UsageMessage.TODO_ADD.sendTo(sender);
					return true;
				}
				
				ISubCmd command = new CmdTodoAdd(plugin);
				command.run(sender, args);
				return true;
			} else if (args[0].equalsIgnoreCase("share")) {
				if (args.length < 2) {
					/* Show usage */
					UsageMessage.TODO_SHARE.sendTo(sender);
					return true;
				}
				
				ISubCmd command = new CmdTodoShare(plugin);
				command.run(sender, args);
				return true;
			} else if (args[0].equalsIgnoreCase("setpriority")) {
				/*
				 * Sets the priority of a ticket for a player
				 */
				if (args.length < 2) {
					/* Show usage */
					UsageMessage.TODO_SETPRIORITY.sendTo(sender);
					return true;
				}
				
				ISubCmd command = new CmdTodoSetpriority(plugin);
				command.run(sender, args);
				return true;
			} else if (args[0].equalsIgnoreCase("check")) {
				/*
				 * Shows all the tasks a player has
				 */
				
				int maxPages = 1;
				int pageNum = 1;
				
				if (args.length > 2) {
					/* Show usage */
					UsageMessage.TODO_CHECK.sendTo(sender);
					return true;
				} else if (args.length == 2) {
					try {
						Integer.valueOf(args[1]);
					} catch (NumberFormatException e) {
						GenericMessage.ITEM_MUST_BE_INT.sendTo(sender, Arrays.asList("{ITEM}|Page"));
						return true;
					}
				}
				
				sender.sendMessage(ChatColor.GREEN + "----Tasks pg." + pageNum + "/" + maxPages + "----");
				/*for (int i = 1; i <= 5; i++) {
					String taskMessage
					sender.sendMessage(ChatColor.GOLD + Integer.toString(i * pageNum) + ":" + ChatColor.AQUA + taskMessage);
				}*/
				
				return true;
			} else if (args[0].equalsIgnoreCase("reload")) { 
				/* Reloads config from file */
				PluginMethods.sendTaggedMessage(sender, ChatColor.RED + "Reloading config...");
				plugin.reloadConfig();
				PluginMethods.sendTaggedMessage(sender, ChatColor.RED + "Reloaded config from file");
				return true;
			}
		}
		
		/* Show usage */
		UsageMessage.TODO_ADD.sendTo(sender);
		return true;
	}
}
