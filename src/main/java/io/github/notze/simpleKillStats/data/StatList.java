package io.github.notze.simpleKillStats.data;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.entity.Player;

public class StatList {

	List<PlayerItem> items = new ArrayList<PlayerItem>();
	
	// amount of players that have stats
	int length = 0;
	
	public StatList(){
		
	}
	
	public void put(String player, String mob, int count){
		for( PlayerItem item : items )
			if( item.getPlayer().equals(player)){
				item.put(mob, count);
				return;
			}
		items.add(new PlayerItem(player, mob, count));
		length = items.size();
	}
	
	public int getCount(String player, String mob){
		for(PlayerItem item : items)
			if(item.getPlayer().equals(player) )
				return item.getCount(mob);
		return 0;
	}
	
	public List<StatListItem> getItems(){
		List<StatListItem> out = new ArrayList<StatListItem>();
		
		for(PlayerItem playerItem : items)
			out.addAll( playerItem.getItems() );
		
		return out;
	}
	
	public List<StatListItem> getItems(Player player){
		String playerName = player.getName();
		
		for(PlayerItem playerItem : items)
			if( playerItem.getPlayer().equals(playerName) )
				return playerItem.getItems();
		
		return null;
	}
	
	public int getLength(){
		return length;
	}
	
}
