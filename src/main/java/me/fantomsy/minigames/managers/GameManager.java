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
import org.bukkit.event.EventHandler;
import org.bukkit.inventory.ItemStack;

import java.util.ArrayList;

public class GameManager extends Manager {

    public GameManager(Main plugin) {
        super(plugin);
        onlinePlayers = new ArrayList<Player>();
        alivePlayers = new ArrayList<Player>();
        deadPlayers = new ArrayList<Player>();
    }

    public static ArrayList<Player> onlinePlayers;
    public static ArrayList<Player> alivePlayers;
    public static ArrayList<Player> deadPlayers;

    public void startGame() {
        if (plugin.getGameState() != GameState.LOBBY) return;
        alivePlayers.clear();
        deadPlayers.clear();
        if (!LocationsManager.locationsCheck()) {
            Bukkit.broadcastMessage(Color.tr(Color.prefix + "Null Locations. Please contact a Developer."));
            plugin.setGameState(GameState.LOBBY);
            return;
        }
        plugin.setGameState(GameState.ACTIVE);
        Bukkit.broadcastMessage(Color.tr(Color.prefix + "The game has started!"));
        for (int i = 0; i < onlinePlayers.size(); i++) {
            if (i == 8) {
                Bukkit.broadcastMessage(Color.tr(Color.prefix + "This game can only have 8 players. Please wait."));
                break;
            }
            Player player = onlinePlayers.get(i);
            alivePlayers.add(player);
            Location location = LocationsManager.gameSpawn.get(i);
            player.teleport(location);
            PlayerManager.equip(player);
        }
        Color.debug(String.valueOf(alivePlayers.size()));
    }

    // Start and Stop use different systems for items - due to the fact that start already needs a for-loop.

    public void stopGame() {
        if (plugin.getGameState() != GameState.ACTIVE) return;
        plugin.setGameState(GameState.LOBBY);
        Bukkit.broadcastMessage(Color.tr(Color.prefix + "Game Ended!"));
        Bukkit.getOnlinePlayers().stream().forEach(PlayerManager::resetPlayer);
        alivePlayers.clear();
    }
}
