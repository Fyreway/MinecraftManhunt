package com.isaacyee.minecraft.mcmanhunt;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.entity.EntityDeathEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.CompassMeta;

import java.util.Objects;

public final class ManhuntListeners implements Listener {
	@EventHandler
	public void onCompassUpdate(PlayerInteractEvent event) {
		Player player = event.getPlayer();
		if (!player.getScoreboardTags().contains("manhunt:hunter")) return;
		if (event.getAction() != Action.RIGHT_CLICK_AIR && event.getAction() != Action.RIGHT_CLICK_BLOCK) return;
		if (Objects.requireNonNull(event.getItem()).getType() != Material.COMPASS) return;
		event.setCancelled(true);
		Player nearestRunner = findNearestPlayer(player);
		if (nearestRunner == null) {
			player.sendRawMessage("§cNobody to track");
		} else {
			player.sendRawMessage("§aCompass now pointing to " + nearestRunner.getDisplayName());
			ItemStack compass = player.getInventory().getItemInMainHand();
			CompassMeta compassMeta = (CompassMeta) compass.getItemMeta();
			assert compassMeta != null;
			compassMeta.setLodestone(nearestRunner.getLocation());
			compassMeta.setDisplayName("Tracking " + nearestRunner.getDisplayName());
			compass.setItemMeta(compassMeta);
		}
	}

	@EventHandler
	public void onHunterDeath(EntityDeathEvent event) {
		if (event.getEntity() instanceof Player player) {
			if (player.getScoreboardTags().contains("manhunt:hunter")) {
				ItemStack compass = new ItemStack(Material.COMPASS);
				CompassMeta meta = (CompassMeta) compass.getItemMeta();
				assert meta != null;
				meta.setLodestoneTracked(false);
				compass.setItemMeta(meta);
				player.getInventory().addItem(compass);
			}
		}
	}

	private Player findNearestPlayer(Player player) {
		Player result = null;
		double lastDistance = Double.MAX_VALUE;
		for (Player p : player.getWorld().getPlayers()) {
			if (player == p) continue;
			if (p.getScoreboardTags().contains("manhunt:hunter")) continue;

			double distance = player.getLocation().distance(p.getLocation());
			if (distance < lastDistance) {
				lastDistance = distance;
				result = p;
			}
		}

		return result;
	}
}
