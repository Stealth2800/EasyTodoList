package com.stealthyone.bukkit.todolist.commands.subcommands;

import org.bukkit.command.CommandSender;

public interface ISubCmd {

	void run(CommandSender sender, String[] args);
}