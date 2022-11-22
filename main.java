package me._betong.broadcast;

import org.bukkit.plugin.java.JavaPlugin;

import com.tjplaysnow.api.config.Config;

import me._betong.broadcast.commands.*;

public class main extends JavaPlugin {
	
	public Config config;

	@Override
	public void onEnable() {
		new PingCMD(this);
		new PluginReload(this);
		this.saveDefaultConfig();
	}
}
