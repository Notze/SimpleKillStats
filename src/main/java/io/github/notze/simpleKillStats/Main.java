package io.github.notze.simpleKillStats;

import org.bukkit.plugin.java.JavaPlugin;

public final class Main extends JavaPlugin {

	static Main main;
	Persistence persistence;
	
	public Main(){
		main = this;
	}
	
	@Override
	public void onEnable(){
		// initialize persistence module
		persistence = Persistence.getInstance();
		persistence.initializeConfig();
		
		// set command executor
		this.getCommand("killstats").setExecutor(new CommandExecutor(this));
		
		// Observer
		Observer observer = new Observer(this);
		getServer().getPluginManager().registerEvents(observer, this);
	}
	
	@Override
	public void onDisable(){
		// save stats
		persistence.save();
	}
	
	public static Main getInstace(){
		return main;
	}
}
