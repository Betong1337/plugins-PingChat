package me._betong.broadcast.commands;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.ChatColor;
import org.bukkit.Sound;
import org.bukkit.entity.Player;

import me._betong.broadcast.main;

public class PingCMD implements CommandExecutor {
	
	@SuppressWarnings("unused")
	private main plugin;
	
	public PingCMD(main plugin) {
		this.plugin = plugin;
		plugin.getCommand("ping").setExecutor(this);
	}
	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		if (!(sender instanceof Player)) {
			return true;
		}
		Player p = (Player) sender;
		String permission = ChatColor.RED + "You don't have permission execute this command!";
		
		if (!p.hasPermission("pingchat.player.ping")) {
			p.sendMessage(permission);
			return true;
		}
		if (args.length < 1) {
			p.sendMessage("Usage: /ping <player>");
			return true;
		}
		Player target = Bukkit.getPlayer(args[0]);
		if (target == null) {
			p.sendMessage(ChatColor.RED + args[0] + ChatColor.WHITE + " is not online!");
			return true;
		}
		try {
		if (plugin.getConfig().getBoolean("ping_yourself") == false) {
		if (p.getName() == target.getName()) {
			p.sendMessage(ChatColor.RED + " You can not ping yourself!");
			return true;
		}
		}
		} catch(Exception e) {
			p.sendMessage(ChatColor.DARK_RED + "Something is wrong with the config! Inform Owner/Admin");
			Bukkit.broadcastMessage(ChatColor.RED + "[PingChatERROR401]: " + ChatColor.DARK_RED + "Something is wrong with the config! Inform Owner/Admin");
			System.out.print(ChatColor.DARK_RED + "[PingChatERROR]: ping_yourself Config is not valid! Try to remove config.yml and restart server!");
			System.out.print(e);
		}

		try {
			ChatColor username_color = ChatColor.valueOf(plugin.getConfig().getString("username_color").toUpperCase());
			ChatColor broadcast_text_color = ChatColor.valueOf(plugin.getConfig().getString("broadcast_text_color").toUpperCase());
			ChatColor sender_username_color = ChatColor.valueOf(plugin.getConfig().getString("sender_username_color").toUpperCase());
			ChatColor prefix_color = ChatColor.valueOf(plugin.getConfig().getString("prefix_color").toUpperCase());
			ChatColor ping_text_color = ChatColor.valueOf(plugin.getConfig().getString("ping_text_color").toUpperCase());
			
			String prefix_text = plugin.getConfig().getString("prefix_text");
			String broadcast_text = plugin.getConfig().getString("broadcast_text");
			String ping_text = plugin.getConfig().getString("ping_text");
			
			boolean prefix_bool = plugin.getConfig().getBoolean("prefix");
			boolean broadcast_bool = plugin.getConfig().getBoolean("broadcast_ping");
			
			String prefix = prefix_color + "[" + prefix_text + "] ";
			String ping_msg = username_color + "@" + p.getName() + ping_text_color + " " + ping_text;
			
			String sound = plugin.getConfig().getString("sound").toUpperCase();
			target.playSound(target.getLocation(), Sound.valueOf(sound), 1f, 1f);
			
			String public_msg = username_color + "@" + target.getName() + broadcast_text_color + " " + broadcast_text + " " + sender_username_color + "@" + p.getName();
			if (prefix_bool == true) {
				if (broadcast_bool == true) {
					Bukkit.broadcastMessage(prefix + public_msg);
				}
				target.sendMessage(prefix + ping_msg);
			} else {
				if (broadcast_bool == true) {
					Bukkit.broadcastMessage(public_msg);
				}
				target.sendMessage(ping_msg);
			}
		} catch(Exception e) {
			p.sendMessage(ChatColor.DARK_RED + "Something is wrong with the config! Inform Owner/Admin");
			Bukkit.broadcastMessage(ChatColor.RED + "[PingChatERROR402]: " + ChatColor.DARK_RED + "Something is wrong with the config! Inform Owner/Admin");
			System.out.print(ChatColor.DARK_RED + "[PingChatERROR]: Config is not valid! Try to remove config.yml and restart server!");
			System.out.print(e);
		}

			return true;
	}
}
