package com.stealthyone.bukkit.todolist.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

import com.stealthyone.bukkit.todolist.BasePlugin;

public final class CmdTodo implements CommandExecutor {

	private BasePlugin plugin;
	
	public CmdTodo(BasePlugin plugin) {
		this.plugin = plugin;
	}

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		return false;
	}
}
