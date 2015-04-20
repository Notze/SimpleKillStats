package io.github.notze.simpleKillStats;

import io.github.notze.simpleKillStats.data.*;

import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;

public class Persistence {

	static Persistence instance;
	
	Main main = Main.getInstace();
	
	StatList kills = new StatList();
	
	private Persistence(){
		
	}
	
	public static Persistence getInstance(){
		if( instance==null )
			instance = new Persistence();
		return instance;
	}
	
	public void setMain(Main main){
		this.main = main;
	}
	
	/**
	 * Initialize ConfigurationFile and load values.
	 */
	public void initializeConfig(){
		main.saveDefaultConfig();
		for(String name : main.getConfig().getKeys(true)){
			String splitString[] = name.split(":");
			kills.put(splitString[0], splitString[1].replaceAll("_", " "), main.getConfig().getInt(name));
		}
		main.getLogger().info("Loaded stats for " + kills.getLength() + " players.");
	}
	
	/**
	 * Save HashMap to config.
	 */
	public void save(){
		for( StatListItem item : kills.getItems() )
			main.getConfig().set(
					item.getPlayer() + ":" + item.getMob().replaceAll(" ", "_"),
					item.getCount()
					);
		main.saveConfig();
	}
	
	public int increase(Player player, Entity mob){
		String playerName = player.getName();
		String mobName = mob.getName();
		
		int newCount = kills.getCount(playerName, mobName) + 1;
		kills.put(playerName, mobName, newCount);
		
		return newCount;
	}
	
	public StatList getStatList(){
		return kills;
	}
	
}
