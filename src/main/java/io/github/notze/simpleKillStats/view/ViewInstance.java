package io.github.notze.simpleKillStats.view;

import java.util.List;
import java.util.UUID;

import io.github.notze.simpleKillStats.Main;
import io.github.notze.simpleKillStats.Persistence;
import io.github.notze.simpleKillStats.data.StatListItem;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.scoreboard.DisplaySlot;
import org.bukkit.scoreboard.Objective;
import org.bukkit.scoreboard.Score;
import org.bukkit.scoreboard.Scoreboard;
import org.bukkit.scoreboard.ScoreboardManager;

public class ViewInstance{

	Main main;
	UUID playerID;
	ScoreboardManager manager = Bukkit.getScoreboardManager();
	Scoreboard board = manager.getNewScoreboard();
	boolean isShown = false;
	
	public ViewInstance(Main main, Player player){
		this.main = main;
		this.playerID = player.getUniqueId();
	}
	
	/**
	 * Shows the given player a scoreboard with his stats for 10 seconds.
	 * 
	 * @param player Player
	 */
	public void showStat(){
		updateScoreboard();
	    
	    new BukkitRunnable(){
			@Override
			public void run() {
				hideStat();
			}
	    }.runTaskLater(main, 200); // run after 10 seconds
	}
	
	/**
	 * Shows the given player a scoreboard with his stats until isShown is set to false
	 * 
	 * @param player Player
	 */
	public void showStatPermanently() {
		isShown = true;
		
		new BukkitRunnable(){
			@Override
			public void run() {
				if( !(isShown) || getPlayer()==null ){
					cancel();
					isShown = false;
				}else{
					updateScoreboard();
				}
			}
	    }.runTaskTimer(main, 0, 200); // run all 10 seconds
	}
	
	/**
	 * clears the scoreboard and sets isShown to false
	 */
	public void hideStat(){
		if( isShown ){
			board = manager.getNewScoreboard();
			getPlayer().setScoreboard(board);
			isShown = false;
		}
	}
	
	/**
	 * updates the scoreboard.
	 */
	private void updateScoreboard(){
		board = manager.getNewScoreboard();
	    Objective objective = board.registerNewObjective("test", "dummy");
	    objective.setDisplaySlot(DisplaySlot.SIDEBAR);
	    objective.setDisplayName(ChatColor.YELLOW + "Kill Statistic");
	    
	    List<StatListItem> items = Persistence.getInstance().getStatList().getItems(getPlayer());
	    if( items != null ){
		    for( StatListItem item : items ){
		    	Score score = objective.getScore(item.getMob());
			    score.setScore(item.getCount());
		    }
		    
		    getPlayer().setScoreboard(board);
		    isShown = true;
	    }
	}
	
	/**
	 * returns the instance of the player by uuid
	 * 
	 * @return the player
	 */
	private Player getPlayer(){
		return main.getServer().getPlayer(playerID);
	}
	
}
