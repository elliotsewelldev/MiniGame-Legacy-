package me.fantomsy.minigames.events;

import me.fantomsy.minigames.Main;
import me.fantomsy.minigames.managers.GameManager;
import me.fantomsy.minigames.utils.Color;
import me.fantomsy.minigames.utils.Task;
import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.inventory.ItemStack;

public class Join implements Listener {

    @EventHandler
    public void onJoin(PlayerJoinEvent event) {
        event.setJoinMessage("");
        Player player = event.getPlayer();
        Main.getInstance().getPlayerManager().handleJoin(player);
        if (player.hasPermission("Rank.Dev")) {
            Task.giveItem(player, new ItemStack(Material.SPONGE, 1), "Datapoints", 7);
            Task.giveItem(player, new ItemStack(Material.REDSTONE_BLOCK, 1), "Datapoints", 8);
            player.setGameMode(GameMode.CREATIVE);
        }
    }

    @EventHandler
    public void onQuit(PlayerQuitEvent event) {
        event.setQuitMessage("");
        Bukkit.broadcastMessage(Color.tr("&8[&cQuit&8] &7" + event.getPlayer().getName()));
        GameManager.onlinePlayers.remove(event.getPlayer());
        if (GameManager.alivePlayers.contains(event.getPlayer())) {
            GameManager.onlinePlayers.remove(event.getPlayer());
        }
    }
}
