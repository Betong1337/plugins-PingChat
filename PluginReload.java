package me._betong.broadcast.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.ChatColor;

import me._betong.broadcast.main;

public class PluginReload implements CommandExecutor {
	
	@SuppressWarnings("unused")
	private main plugin;
	
	public PluginReload(main plugin) {
		this.plugin = plugin;
		plugin.getCommand("pingreload").setExecutor(this);
	}
	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		String permission = ChatColor.RED + "You don't have permission execute this command!";
		
		if (!sender.hasPermission("pingchat.reload")) {
			sender.sendMessage(permission);
			return true;
		}
		plugin.reloadConfig();
		sender.sendMessage(ChatColor.GREEN + "[PingChat] Reloaded Successfully!");


			return true;
	}
}
