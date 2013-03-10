package com.stealthyone.bukkit.todolist.messages;

import java.util.List;
import java.util.regex.Pattern;

import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;

import com.stealthyone.bukkit.todolist.PluginMethods;

public enum GenericMessage {

	ITEM_MUST_BE_INT(ChatColor.RED + "{ITEM} must be an integer!", false);
	
	private String message;
	private boolean isTagged;
	
	private GenericMessage(String msg) {
		this(msg, true);
	}
	
	private GenericMessage(String msg, boolean isTagged) {
		this.message = msg;
		this.isTagged = isTagged;
	}
	
	public final void sendTo(CommandSender sender) {
		this.sendTo(sender, null);
	}
	
	public final void sendTo(CommandSender sender, List<String> replacements) {
		String newMessage = this.message;
		
		if (replacements != null) {
			for (int i = 0; i < replacements.size(); i++) {
				String[] toReplace = replacements.get(i).split(Pattern.quote("|"));
				
				newMessage.replace(toReplace[0], toReplace[1]);
			}
		}
		
		if (this.isTagged) {
			PluginMethods.sendTaggedMessage(sender, this);
		} else {
			sender.sendMessage(newMessage);
		}
	}
	
	public final String getFullMessage() {
		return this.message;
	}
	
}
