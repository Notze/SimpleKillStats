package io.github.notze.simpleKillStats.view;

import java.util.HashMap;
import java.util.UUID;

import io.github.notze.simpleKillStats.Main;
import org.bukkit.entity.Player;

public class ViewManager {
	
	// reference to plugin
	static Main main = Main.getInstace();
	
	// list of instances for players
	static HashMap<UUID, ViewInstance> instances = new HashMap<UUID, ViewInstance>();
	
	/**
	 * Returns the ViewInstance for the given player.
	 * 
	 * @param player player
	 * @return instance of ViewInstance for the player
	 */
	public static ViewInstance getInstance(Player player){
		if( !(instances.containsKey(player.getUniqueId())) )
			instances.put(player.getUniqueId(), new ViewInstance(main, player));
		return instances.get(player.getUniqueId());
	}
	
}
