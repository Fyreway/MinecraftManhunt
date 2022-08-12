package com.isaacyee.minecraft.mcmanhunt;

import net.md_5.bungee.api.ChatMessageType;
import net.md_5.bungee.api.chat.TextComponent;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

public final class Timer extends BukkitRunnable {
	long seconds;

	public Timer(long seconds) {
		this.seconds = seconds;
	}

	@Override
	public void run() {
		long minutes = Math.floorDiv(seconds, 60);
		long hours = Math.floorDiv(minutes, 60);
		minutes %= 60;
		for (Player player : Bukkit.getServer().getOnlinePlayers()) {
			player.spigot().sendMessage(ChatMessageType.ACTION_BAR, new TextComponent("%d:%d:%d".formatted(hours, minutes, seconds % 60)));
		}
		--seconds;
	}

}
