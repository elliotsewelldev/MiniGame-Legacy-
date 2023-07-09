package me.fantomsy.minigames.events;

import me.fantomsy.minigames.Main;
import org.bukkit.GameMode;
import org.bukkit.Location;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockPlaceEvent;

public class WorldProtect implements Listener {

    @EventHandler
    public void onPlace(BlockPlaceEvent event) {
        Player player = event.getPlayer();
        Block block = event.getBlock();
        if (player.getGameMode() == GameMode.CREATIVE) {
            Main.getInstance().getLocationsManager().parseLocations(event.getBlock(), player);
        } else event.setCancelled(true);
    }
}
