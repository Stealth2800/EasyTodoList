package com.stealthyone.bukkit.todolist.messages;

import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;

public enum UsageMessage {

	TODO(ChatColor.DARK_RED + "USAGE:" + ChatColor.RED + " /todo"),
	TODO_ADD(ChatColor.DARK_RED + "USAGE:" + ChatColor.RED + " /todo add <message>");
	
	private String usageMessage;
	private boolean isTagged;
	
	private UsageMessage(String msg) {
		this(msg, true);
	}
	
	private UsageMessage(String msg, boolean isTagged) {
		this.usageMessage = msg;
		this.isTagged = isTagged;
	}
	
	public final String getFullMessage() {
		return this.usageMessage;
	}
	
	public final void sendTo(CommandSender sender) {
		if (this.isTagged) {
			
		} else {
			sender.sendMessage(this.usageMessage);
		}
	}
}
