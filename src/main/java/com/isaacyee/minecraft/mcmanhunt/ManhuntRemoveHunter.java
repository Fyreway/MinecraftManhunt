package com.isaacyee.minecraft.mcmanhunt;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

public class ManhuntRemoveHunter implements CommandExecutor {
	@Override
	public boolean onCommand(@NotNull CommandSender commandSender, @NotNull Command command, @NotNull String s, String[] strings) {
		if (!(commandSender instanceof Player player)) return false;
		if (!player.getScoreboardTags().contains("manhunt:hunter")) {
			player.sendRawMessage("§c" + player.getDisplayName() + " is not a Hunter");
		} else {
			player.removeScoreboardTag("manhunt:hunter");
			player.sendRawMessage("§aRemoved " + player.getDisplayName() + " as a Hunter");
		}
		return true;
	}
}
