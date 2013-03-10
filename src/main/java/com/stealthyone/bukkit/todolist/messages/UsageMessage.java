package com.stealthyone.bukkit.todolist.messages;

import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;

public enum UsageMessage {

	TODO("/todo"),
	TODO_ADD("/todo add <message>"),
	TODO_CHECK("/todo check [page]"),
	TODO_SHARE("/todo share <task ID> <player> [player2] [player3] ...");
	
	private String usageMessage;
	
	private UsageMessage(String msg) {
		this.usageMessage = msg;
	}
	
	public final String getFullMessage() {
		return this.usageMessage;
	}
	
	public final void sendTo(CommandSender sender) {
		sender.sendMessage(ChatColor.DARK_RED + "USAGE: " + ChatColor.RED + this.usageMessage);
	}
}
