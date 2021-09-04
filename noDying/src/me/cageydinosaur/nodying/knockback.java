package me.cageydinosaur.nodying;

import org.bukkit.Bukkit;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.util.Vector;

public class knockback implements Listener {

	Main plugin;

	public knockback(Main plugin) {
		this.plugin = plugin;
	}

	@EventHandler
	public void doKnockback(EntityDamageByEntityEvent e) {
		if (ToggleCommand.gamestarted) {
			if (e.getEntity() instanceof Player) {
				Player p = (Player) e.getEntity();
				Entity a = e.getDamager();
				if (plugin.getPlayerIds().contains(p.getUniqueId())) {
					p.setVelocity(a.getLocation().getDirection().multiply(1));
				}
			}
		}
	}
}
