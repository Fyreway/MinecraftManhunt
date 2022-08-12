package com.isaacyee.minecraft.mcmanhunt;

import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.CompassMeta;
import org.jetbrains.annotations.NotNull;

public class ManhuntAddHunter implements CommandExecutor {
	@Override
	public boolean onCommand(@NotNull CommandSender commandSender, @NotNull Command command, @NotNull String s, String[] strings) {
		if (!(commandSender instanceof Player player)) return false;
		if (player.getScoreboardTags().contains("manhunt:hunter")) {
			player.sendRawMessage("§c" + player.getDisplayName() + "§c is already a Hunter");
		} else {
			player.addScoreboardTag("manhunt:hunter");
			player.sendRawMessage("§aAdded " + player.getDisplayName() + "§a as a Hunter");
			ItemStack compass = new ItemStack(Material.COMPASS);
			CompassMeta meta = (CompassMeta) compass.getItemMeta();
			assert meta != null;
			meta.setLodestoneTracked(false);
			compass.setItemMeta(meta);
			player.getInventory().addItem(compass);
		}
		return true;
	}
}
