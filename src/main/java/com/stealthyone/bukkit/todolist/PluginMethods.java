package com.stealthyone.bukkit.todolist;

import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;

import com.stealthyone.bukkit.todolist.messages.UsageMessage;

public final class PluginMethods {

	public final static void sendTaggedMessage(CommandSender sender, UsageMessage message) {
		PluginMethods.sendTaggedMessage(sender, message.getFullMessage());
	}
	
	public final static void sendTaggedMessage(CommandSender sender, String message) {
		sender.sendMessage(ChatColor.GOLD + "[EasyTodoList] " + ChatColor.GREEN + message);
	}
}
