package io.github.notze.simpleKillStats;

import io.github.notze.simpleKillStats.view.ViewManager;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class CommandExecutor implements org.bukkit.command.CommandExecutor {

	Main main;
	
	public CommandExecutor(Main main){
		this.main = main;
	}
	
	@Override
	public boolean onCommand(CommandSender sender, Command command,
			String label, String[] args) {
		if( command.getName().equalsIgnoreCase("killstats") ){
			if( sender instanceof Player){
				Player player = (Player) sender;
				if( args.length == 0 ){
				
					ViewManager.getInstance(player).showStat();
					return true;
					
				}else if( args.length == 1 ){
				
					if( args[0].equals("on") ){
						ViewManager.getInstance(player).showStatPermanently();
						return true;
					}else if( args[0].equals("off") ){
						ViewManager.getInstance(player).hideStat();
						return true;
					}
					
				}
			}else{
				sender.sendMessage("This command can only be run by a player!");
				return true;
			}
		}
		return false;
	}

}
