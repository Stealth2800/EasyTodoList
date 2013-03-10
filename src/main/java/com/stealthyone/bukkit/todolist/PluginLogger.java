package com.stealthyone.bukkit.todolist;

import java.util.logging.Level;
import java.util.logging.Logger;


public final class PluginLogger {

	private static final Logger logger = Logger.getLogger("minecraft");
	
	private BasePlugin plugin;
	
	public PluginLogger(BasePlugin plugin) {
		this.plugin = plugin;
	}
	
	public final void debug(String s) {
		if (plugin.isDebug()) {
			logger.log(Level.INFO, "[EasyTodoList DEBUG] " + s);
		}
	}
	
	public final void info(String s) {
		logger.log(Level.INFO, "[EasyTodoList] " + s);
	}
	
	public final void warning(String s) {
		logger.log(Level.WARNING, "[EasyTodoList] " + s);
	}
	
	public final void severe(String s) {
		logger.log(Level.SEVERE, "[EasyTodoList] " + s);
	}
}
