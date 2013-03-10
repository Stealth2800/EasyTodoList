package com.stealthyone.bukkit.todolist.utils;

import java.io.File;
import java.io.IOException;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

import com.stealthyone.bukkit.todolist.BasePlugin;

public class CustomFileManager {

	private BasePlugin plugin;
	
	private String fileName;
	private File customFile;
	private FileConfiguration customConfig;
	
	public CustomFileManager(BasePlugin plugin, String fileName) {
		this.plugin = plugin;
		this.fileName = fileName;
	}
	
	/**
	 * Reload file from disk
	 */
	public void reloadConfig() {
		plugin.getLog().debug("(" + fileName + ") reloadConfig");
		if (customFile == null) {
			plugin.getLog().debug("(" + fileName + ") customFile == null");
			customFile = new File(plugin.getDataFolder(), fileName + ".yml");
		}
		customConfig = YamlConfiguration.loadConfiguration(customFile);
	}
	
	/**
	 * Return the config from file
	 * @return
	 */
	public FileConfiguration getConfig() {
		if (customConfig == null) {
			this.reloadConfig();
		}
		return customConfig;
	}
	
	/**
	 * Save changes to file on disk
	 */
	public void saveFile() {
		plugin.getLog().debug("Saving file: " + this.fileName);
        if (customConfig == null || customFile == null) {
        	this.reloadConfig();
        } else {
        	plugin.getLog().debug("Attempting to save to file");
            try {
            	this.getConfig().save(customFile);
            	plugin.getLog().debug("File saved successfully");
            } catch (IOException ex) {
                plugin.getLog().severe("Could not save file: " + this.fileName);
            }
        }
	}
	
}
