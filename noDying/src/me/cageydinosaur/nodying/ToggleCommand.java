package me.cageydinosaur.nodying;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class ToggleCommand implements CommandExecutor {
	public static boolean gamestarted = false;
	
	Main plugin;
	public ToggleCommand(Main plugin) {
		this.plugin = plugin;
	}

	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		if (sender.hasPermission("nodie")) {
			if (args.length == 0) {
				sender.sendMessage(ChatColor.RED + "Usage: ");
				sender.sendMessage(ChatColor.RED + "/nodie toggle");
				sender.sendMessage(ChatColor.RED + "/nodie info");
				sender.sendMessage(ChatColor.RED + "/nodie reload");
				sender.sendMessage(ChatColor.RED + "/nodie [player]");
			} else if (args.length > 0) {
				if (args[0].equalsIgnoreCase("toggle")) {
					if (!gamestarted) {
						gamestarted = true;
					} else if (gamestarted) {
						gamestarted = false;
					}
				} else if (args[0].equalsIgnoreCase("info")) {
					sender.sendMessage(
							ChatColor.GREEN + "When players take damage and they are at half heart, they don't die");
					sender.sendMessage(ChatColor.RED + "Put names into config then do /nodie reload to reload the config");
				} else if (args[0].equalsIgnoreCase("reload")) {
					plugin.reloadConfig();
				} else {
					Player excluded = Bukkit.getServer().getPlayerExact(args[0]);
					if (!plugin.getPlayerIds().contains(excluded.getUniqueId())) {
						plugin.addPlayer(excluded);
						sender.sendMessage(ChatColor.GREEN + args[0] + " is not going to die");
					} else if (plugin.getPlayerIds().contains(excluded.getUniqueId())) {
						plugin.removePlayer(excluded);
						sender.sendMessage(ChatColor.GREEN + args[0] + " is going to die");
					}
				}
			}
		} else {
			sender.sendMessage(ChatColor.RED + "You do not have permission to use this command");
		}
		return true;

	}
}