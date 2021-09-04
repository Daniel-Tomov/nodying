package me.cageydinosaur.nodying;

import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

public class Main extends JavaPlugin{
	private Set<UUID> playerIds = new HashSet<UUID>();
	public void onEnable() {
		this.getServer().getPluginManager().registerEvents(new damageListener(this), this);
		this.getServer().getPluginManager().registerEvents(new knockback(this), this);
		getCommand("nodie").setExecutor(new ToggleCommand(this));
		this.saveDefaultConfig();
	}
	
	public Set<UUID> getPlayerIds() {
		return this.playerIds;
	}
	
	public void addPlayer(Player excluded) {
		playerIds.add(excluded.getUniqueId());
	}
	
	public void removePlayer(Player excluded) {
		playerIds.remove(excluded.getUniqueId());
	}
}
