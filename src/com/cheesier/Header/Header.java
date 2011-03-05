package com.cheesier.Header;

import java.util.logging.Logger;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.PluginDescriptionFile;
import org.bukkit.plugin.java.JavaPlugin;

import com.nijiko.permissions.PermissionHandler;

/**
 * Header for Bukkit
 *
 * @author Cheesier
 */

public class Header extends JavaPlugin {
    public static Logger log = Logger.getLogger("Minecraft");
    public static PermissionHandler Permissions = null;
    public static Plugin plugin = null;
    

    public void onEnable() {
    	plugin = this;
        
        // Say hello in a fancy way :P
        PluginDescriptionFile pdfFile = this.getDescription();
        
        // start Permissions
        HeaderLibrary.setupPermissions();
        
        
        
        log.info("[" + pdfFile.getName() + "] version [" + pdfFile.getVersion() + "] loaded");
    }
    
    
    public void onDisable() {

    }
    
    // Check for all the commands that the plugin registered
    public boolean onCommand(CommandSender sender, Command command, String commandLabel, String[] args) {
    	String cmd = command.getName().toLowerCase();
    	
    	if (cmd.equalsIgnoreCase("head"))
    		return HeaderCommand.headCmd(sender, args);
    	
    	return false;
    }

}