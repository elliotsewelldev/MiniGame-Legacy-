package me.fantomsy.minigames.commands;

import me.fantomsy.minigames.managers.LocationsManager;
import me.fantomsy.minigames.utils.Color;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class ClearDatapoints implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (sender instanceof Player) {
            Player player = (Player) sender;
            if (!player.hasPermission("Rank.Dev")) {
                Color.noPerm(player);
                return false;
            }
            LocationsManager.gameSpawn.clear();
            LocationsManager.mainSpawn = null;
            player.sendMessage(Color.tr("&aCleared all datapoints."));
        }
        return false;
    }
}
