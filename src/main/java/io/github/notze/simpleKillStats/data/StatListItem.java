package io.github.notze.simpleKillStats.data;

public class StatListItem {

	String player, mob;
	int count;
	
	public StatListItem(String player, String mob, int count){
		this.player = player;
		this.mob = mob;
		this.count = count;
	}
	
	public String getPlayer(){
		return player;
	}
	
	public String getMob(){
		return mob;
	}
	
	public int getCount(){
		return count;
	}
}
