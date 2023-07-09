package me.fantomsy.minigames.brawlgame;

import me.fantomsy.minigames.Main;
import me.fantomsy.minigames.managers.GameManager;
import me.fantomsy.minigames.utils.Color;
import me.fantomsy.minigames.utils.Manager;
import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.entity.Player;

public class BrawlManager extends Manager {

    public BrawlManager(Main plugin) {
        super(plugin);
    }

    public void handlePlayerDeath(Player player) {
        Color.debug("handlePlayerDeath triggered");
        player.sendMessage(Color.tr("&cYou have been eliminated!"));
        player.setGameMode(GameMode.SPECTATOR);
        player.getInventory().clear();
        Player _win = null;
        GameManager.alivePlayers.remove(player);
        GameManager.deadPlayers.add(player);
        if (GameManager.alivePlayers.size() == 1) {
            Color.debug("blerb");
            for (Player _player : GameManager.alivePlayers) {
                if (!GameManager.deadPlayers.contains(_player)) {
                    _win = _player;
                }
            }
            Bukkit.broadcastMessage(Color.tr(Color.prefix + "The winner is " + _win.getName() + "!"));
            plugin.getGameManager().stopGame();
            return;
        }
        int _alive = GameManager.alivePlayers.size();
        Bukkit.broadcastMessage(Color.tr(Color.prefix + "There are " + _alive + " players alive."));
    }
}
