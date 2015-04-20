package io.github.notze.simpleKillStats.data;

import java.util.ArrayList;
import java.util.List;

public class PlayerItem {
	String player;
	List<MobItem> mobs = new ArrayList<MobItem>();
	
	public PlayerItem(String player, String mob, int count){
		this.player = player;
		put(mob, count);
	}
	
	public void put(String mob, int count){
		for(MobItem item : mobs)
			if(item.getMob().equals(mob)){
				item.setCount(count);
				return;
			}
		mobs.add(new MobItem(mob, count));
	}
	
	public String getPlayer(){
		return player;
	}

	public int getCount(String mob) {
		for( MobItem item : mobs )
			if( item.getMob().equals(mob) )
				return item.getCount();
		return 0;
	}
	
	public List<StatListItem> getItems(){
		List<StatListItem> out = new ArrayList<StatListItem>();
		
		for( MobItem mob : mobs )
			out.add(new StatListItem(player, mob.getMob(), mob.getCount()));
		
		return out;
	}
	
}
