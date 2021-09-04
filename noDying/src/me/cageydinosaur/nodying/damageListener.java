package me.cageydinosaur.nodying;

import org.bukkit.Bukkit;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityDamageEvent;

public class damageListener implements Listener {

	Main plugin;

	public damageListener(Main plugin) {
		this.plugin = plugin;
	}

	@EventHandler
	public void onPlayerDamage(EntityDamageEvent e) {
		if (ToggleCommand.gamestarted) {
			if (e.getEntity() instanceof Player) {
				Player p = (Player) e.getEntity();
				if (plugin.getPlayerIds().contains(p.getUniqueId())) {
					if (p.getHealth() - e.getFinalDamage() < 0.5) {
						e.setCancelled(true);
						p.setHealth(2);
						p.damage(1);
					}
				}
			}
		}
	}
}
