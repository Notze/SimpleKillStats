package io.github.notze.simpleKillStats.data;

public class MobItem {

	String mob;
	int count;
	
	public MobItem(String mob, int count){
		this.mob = mob;
		this.count = count;
	}
	
	public String getMob(){
		return mob;
	}
	
	public int getCount(){
		return count;
	}
	
	public void setCount(int count){
		this.count = count;
	}
	
}
