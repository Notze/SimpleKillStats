package io.github.notze.simpleKillStats;

import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDeathEvent;

public class Observer implements Listener{

	Main main;
	
	Persistence persistence = Persistence.getInstance();
	
	public Observer(Main main){
		this.main = main;
	}
	
	@EventHandler
	public void onEntityDeath(EntityDeathEvent e){
		LivingEntity entity = e.getEntity();
		Player killer = entity.getKiller();
		if( killer!=null ){
			persistence.increase(killer, entity);
		}
	}
	
}
