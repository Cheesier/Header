package com.cheesier.Header;


import java.util.List;

import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.PluginDescriptionFile;

import com.nijikokun.bukkit.Permissions.Permissions;

public class HeaderLibrary {
    public static Player getPlayerFromName(String playerName) {
		List<Player> players = Header.plugin.getServer().matchPlayer(playerName);
		// if not just one player popped up, say so :)
		return (players.size() == 1 ? players.get(0) : null);
	}
    
    //The implementation of Nijikokun's "Permission" plugin
    public static void setupPermissions() {
    	PluginDescriptionFile pdfFile = Header.plugin.getDescription();
    	Plugin permissions = Header.plugin.getServer().getPluginManager().getPlugin("Permissions");

    	if(Header.Permissions == null) {
    		if(permissions != null) {
    			Header.Permissions = ((Permissions)permissions).getHandler();
    		} else {
    			System.out.println("["+pdfFile.getName()+"] Permission system not enabled. Disabling plugin.");
    			Header.plugin.getServer().getPluginManager().disablePlugin(Header.plugin);
    		}
    	}
    }
    
}
