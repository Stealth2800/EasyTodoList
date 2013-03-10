package com.stealthyone.bukkit.todolist;

import org.bukkit.plugin.java.JavaPlugin;

import com.stealthyone.bukkit.todolist.storage.ListFileManager;


public final class BasePlugin extends JavaPlugin {

	private static BasePlugin instance;
	{
		instance = this;
	}
	
	/* Returns the current instance of the plugin */
	public final static BasePlugin getInstance() {
		return instance;
	}
	
	private PluginConfig config;
	private PluginLogger log;
	private ListFileManager storageManager;
	
	@Override
	public final void onEnable() {
		//Setup config
		config = new PluginConfig(this);
		config.load();
		
		//Setup log
		log = new PluginLogger(this);
		
		//Setup storage manager
		storageManager = new ListFileManager(this);
		
		log.info("EasyTodoList v" + this.getVersion() + " enabled!");
	}
	
	@Override
	public final void onDisable() {
		log.info("EasyTodoList v" + this.getVersion() + " disabled!");
	}
	
	/* Returns the version of the plugin */
	public final String getVersion() {
		return this.getDescription().getVersion();
	}
	
	/* Returns the log manager */
	public final PluginLogger getLog() {
		return this.log;
	}
	
	/* Returns if the plugin is in debug mode or not */
	public final boolean isDebug() {
		return this.getConfig().getBoolean("Debug");
	}
	
	/* Returns the file storage manager */
	public final ListFileManager getStorageManager() {
		return this.storageManager;
	}
}
