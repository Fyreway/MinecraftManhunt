package com.isaacyee.minecraft.mcmanhunt;

import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.Objects;

public final class MCManhunt extends JavaPlugin {

	@Override
	public void onEnable() {
		// Plugin startup logic
		Bukkit.getPluginManager().registerEvents(new ManhuntListeners(), this);
		Objects.requireNonNull(this.getCommand("addhunter")).setExecutor(new ManhuntAddHunter());
		Objects.requireNonNull(this.getCommand("removehunter")).setExecutor(new ManhuntRemoveHunter());
		Objects.requireNonNull(this.getCommand("starttimer")).setExecutor(new StartTimer(this));
	}

	@Override
	public void onDisable() {
		// Plugin shutdown logic
	}
}
