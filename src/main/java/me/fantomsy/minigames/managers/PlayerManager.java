package me.fantomsy.minigames.managers;

import me.fantomsy.minigames.Main;
import me.fantomsy.minigames.gamehandler.GameState;
import me.fantomsy.minigames.utils.Color;
import me.fantomsy.minigames.utils.Manager;
import me.fantomsy.minigames.utils.Task;
import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

public class PlayerManager extends Manager {

    public PlayerManager(Main plugin) {
        super(plugin);
    }

    public void handleJoin(Player player) {
        player.getInventory().clear();
        GameManager.onlinePlayers.add(player);
        if (plugin.getGameState() == GameState.ACTIVE.LOBBY) {
            player.setExp(0);
            player.setTotalExperience(0);
            player.setHealth(20);
            player.setGameMode(GameMode.ADVENTURE);
            player.setAllowFlight(false);
            player.sendMessage(Color.tr(Color.prefix + "Welcome to the game!"));
            Bukkit.broadcastMessage(Color.tr("&8[&aJoin&8] &7" + player.getName()));
            Location location = player.getWorld().getSpawnLocation().clone();
            location.setYaw(90);
            location.setPitch(0);
            player.teleport(location);
        } else {
            player.sendMessage(Color.tr(Color.prefix + "You have joined as a spectator!"));
            player.setGameMode(GameMode.SPECTATOR);
            Bukkit.broadcastMessage(Color.tr("&8[&aJoin&8] &7" + player.getName()));
        }
    }

    public static void equip(Player player) {
        player.getInventory().clear();
        player.getInventory().setHeldItemSlot(0);
        Task.giveItem(player, new ItemStack(Material.DIAMOND_SWORD, 1), "&eBrawler Sword", 0);
        Task.giveItem(player, new ItemStack(Material.FISHING_ROD, 1), "&3Helper Slapper", 2);
        Task.giveArmour(player, new ItemStack(Material.DIAMOND_HELMET), new ItemStack(Material.DIAMOND_CHESTPLATE),
                new ItemStack(Material.DIAMOND_LEGGINGS), new ItemStack(Material.DIAMOND_BOOTS));
    }

    public static void resetPlayer(Player player) {
        player.getInventory().clear();
        if (player.hasPermission("Rank.Dev")) {
            Task.giveItem(player, new ItemStack(Material.SPONGE, 1), "Datapoints", 7);
            Task.giveItem(player, new ItemStack(Material.REDSTONE_BLOCK, 1), "Datapoints", 8);
            player.setGameMode(GameMode.CREATIVE);
        }
        player.setExp(0);
        player.setTotalExperience(0);
        player.setHealth(20);
        player.setFoodLevel(20);
        player.setGameMode(GameMode.ADVENTURE);
        player.setAllowFlight(false);
        Location location = player.getWorld().getSpawnLocation().clone();
        location.setYaw(90);
        location.setPitch(0);
        player.teleport(location);
    }
}
