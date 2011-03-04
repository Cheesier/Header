package com.cheesier.Header;

import java.util.List;
import java.util.logging.Logger;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.PluginDescriptionFile;
import org.bukkit.plugin.java.JavaPlugin;

import com.nijiko.permissions.PermissionHandler;
import com.nijikokun.bukkit.Permissions.Permissions;

/**
 * Header for Bukkit
 *
 * @author Cheesier
 */

public class Header extends JavaPlugin {
    private static Logger log = Logger.getLogger("Minecraft");
    private static PermissionHandler Permissions = null;
    private static Plugin plugin = null;
    

    public void onEnable() {
    	plugin = this;
        
        // Say hello in a fancy way :P
        PluginDescriptionFile pdfFile = this.getDescription();
        
        // start Permissions
        setupPermissions();
        
        
        
        log.info("[" + pdfFile.getName() + "] version [" + pdfFile.getVersion() + "] loaded");
    }
    
    
    public void onDisable() {

    }
    
    // Check for all the commands that the plugin registered
    public boolean onCommand(CommandSender sender, Command command, String commandLabel, String[] args) {
    	String cmd = command.getName().toLowerCase();
    	
    	if (cmd.equalsIgnoreCase("head"))
    		return headCmd(sender, args);
    	
    	return false;
    }
    
    public static Player getPlayerFromName(String playerName) {
		List<Player> players = plugin.getServer().matchPlayer(playerName);
		// if not just one player popped up, say so :)
		return (players.size() == 1 ? players.get(0) : null);
	}

    private static boolean headCmd(CommandSender sender, String[] args) {  	
    	if ((sender instanceof Player)) {
    		Player player = (Player) sender;
    		if (!player.isOp() && !Permissions.has(player, "Header.use")) {
				player.sendMessage(ChatColor.RED + "You do not have permission to use that command.");
				return true;
			}
    		
    		Player target = null;
    		if (args.length == 1) {
    			target = player;
    		}
    		else if (args.length == 2) {
    			if (!Permissions.has(player, "Header.others")) {
    				player.sendMessage("You do not have permission to do this on someone else.");
    			}
    			target = getPlayerFromName(args[1]);
    		}
    		
    		if (!(target instanceof Player)) {
    			player.sendMessage("No player with that name.");
    			return true;
    		}
    		
    		int blockId;
    		try{
    			blockId = Integer.parseInt(args[0]);
    		} catch(Exception e) {
    			return false;
    		}
    		target.getInventory().setHelmet(new ItemStack(blockId));
    		return true;
    	}
    	else {
    		log.info("This command is only usable in-game");
    		return true;
    	}
    }
    
    
    //The implementation of Nijikokun's "Permission" plugin
    public void setupPermissions() {
    	PluginDescriptionFile pdfFile = this.getDescription();
    	Plugin permissions = this.getServer().getPluginManager().getPlugin("Permissions");

    	if(Header.Permissions == null) {
    		if(permissions != null) {
    			Permissions = ((Permissions)permissions).getHandler();
    		} else {
    			System.out.println("["+pdfFile.getName()+"] Permission system not enabled. Disabling plugin.");
    			this.getServer().getPluginManager().disablePlugin(this);
    		}
    	}
    }
}