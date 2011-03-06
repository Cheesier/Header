package net.cheesier.Header;

import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;


public class HeaderCommand {
    public static boolean headCmd(CommandSender sender, String[] args) {  	
    	if ((sender instanceof Player)) {
    		Player player = (Player) sender;
    		if (!player.isOp() && !Header.Permissions.has(player, "Header.use")) {
				player.sendMessage(ChatColor.RED + "You do not have permission to use that command.");
				return true;
			}
    		
    		Player target = null;
    		if (args.length == 1) {
    			target = player;
    		}
    		else if (args.length == 2) {
    			if (!Header.Permissions.has(player, "Header.others")) {
    				player.sendMessage("You do not have permission to do this on someone else.");
    			}
    			target = HeaderLibrary.getPlayerFromName(args[1]);
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
    		Header.log.info("This command is only usable in-game");
    		return true;
    	}
    }
}
