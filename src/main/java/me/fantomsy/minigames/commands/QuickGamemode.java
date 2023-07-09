package me.fantomsy.minigames.commands;

import me.fantomsy.minigames.utils.Color;
import org.bukkit.GameMode;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class QuickGamemode implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (sender instanceof Player) {
            Player player = (Player) sender;
            if (!player.hasPermission("Rank.Admin")) {
                Color.noPerm(player);
                return false;
            }
            player.setGameMode(GameMode.CREATIVE);
        }
        return false;
    }
}
