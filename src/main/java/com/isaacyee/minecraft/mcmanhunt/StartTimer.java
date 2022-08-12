package com.isaacyee.minecraft.mcmanhunt;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;
import org.jetbrains.annotations.NotNull;

public record StartTimer(JavaPlugin plugin) implements CommandExecutor {

	@Override
	public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
		if (sender instanceof Player) {
			new Timer(3600).runTaskTimer(plugin, 0, 20);
		}
		return true;
	}

}
