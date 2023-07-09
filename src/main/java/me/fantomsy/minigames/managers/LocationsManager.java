package me.fantomsy.minigames.managers;

import me.fantomsy.minigames.Main;
import me.fantomsy.minigames.utils.Color;
import me.fantomsy.minigames.utils.Manager;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;

import java.util.HashMap;

public class LocationsManager extends Manager {

    public LocationsManager(Main plugin) {
        super(plugin);
        gameSpawn = new HashMap<Integer, Location>();
        mainSpawn = null;
        gameSpawn.clear();
    }

    public static Location mainSpawn;
    public static HashMap<Integer, Location> gameSpawn;

    public void parseLocations(Block block, Player player) {
        if (!player.hasPermission("Rank.Dev")) return;
        if (block.getType() == Material.SPONGE) {
            int current = gameSpawn.size();
            if (current == 8) {
                player.sendMessage(Color.tr("&cMax game spawns (8)."));
                block.setType(Material.AIR);
                return;
            }
            gameSpawn.put(current, block.getLocation());
            int _c = current + 1;
            player.sendMessage(Color.tr("&aGame spawn #" + _c + " set to " + gameSpawn.get(current).toString() + "."));
        } else if (block.getType() == Material.REDSTONE_BLOCK) {
            mainSpawn = block.getLocation();
            player.sendMessage(Color.tr("&aMain spawn set to " + mainSpawn.toString() + "."));
        }
    }

    public static boolean locationsCheck() {
        return gameSpawn.size() == 8 && mainSpawn != null;
    }
}
