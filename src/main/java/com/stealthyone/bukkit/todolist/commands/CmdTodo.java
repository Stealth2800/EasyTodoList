package com.stealthyone.bukkit.todolist.commands;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

import com.stealthyone.bukkit.todolist.BasePlugin;
import com.stealthyone.bukkit.todolist.commands.subcommands.CmdTodoAdd;
import com.stealthyone.bukkit.todolist.commands.subcommands.ISubCmd;
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
				if (args.length > 1) {
					/* Show usage */
					UsageMessage.TODO_ADD.sendTo(sender);
					return true;
				}
				ISubCmd command = new CmdTodoAdd(plugin);
				command.run(sender, args);
			}
		}
		
		/* Show usage */
		UsageMessage.TODO_ADD.sendTo(sender);
		return true;
	}
}
