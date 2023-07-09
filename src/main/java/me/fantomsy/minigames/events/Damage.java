package me.fantomsy.minigames.events;

import me.fantomsy.minigames.Main;
import me.fantomsy.minigames.brawlgame.BrawlManager;
import me.fantomsy.minigames.gamehandler.GameState;
import me.fantomsy.minigames.utils.Color;
import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.FoodLevelChangeEvent;
import org.bukkit.event.entity.PlayerDeathEvent;

public class Damage implements Listener {

    @EventHandler
    public void onFood(FoodLevelChangeEvent event) {
        event.setCancelled(true);
        Player player = (Player) event.getEntity();
        player.setFoodLevel(20);
    }

    @EventHandler
    public void onAttack(EntityDamageByEntityEvent event) {
        if (!(event.getDamager() instanceof Player)) return;
        Player player = (Player) event.getDamager();
        if (player.getGameMode() == GameMode.CREATIVE) return;
        if (Main.getInstance().getGameState() != GameState.ACTIVE) event.setCancelled(true);
    }


    @EventHandler
    public void onPlayerDeath(PlayerDeathEvent event) {
        Player player = event.getEntity();
        Player killer = player.getKiller();
        String _player = player.getName();
        String _killer = killer.getName();
        event.setDeathMessage("");
        Bukkit.broadcastMessage(Color.tr("&cDeath> &7" + _killer + " has defeated " + _player + "."));
        event.setDroppedExp(0);
        event.getDrops().clear();
        Main.getInstance().getBrawlManager().handlePlayerDeath(player);
    }
}
