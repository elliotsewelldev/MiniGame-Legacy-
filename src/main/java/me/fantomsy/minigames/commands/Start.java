package me.fantomsy.minigames.commands;

import me.fantomsy.minigames.Main;
import me.fantomsy.minigames.managers.GameManager;
import me.fantomsy.minigames.utils.Color;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class Start implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (sender instanceof Player) {
            Player player = (Player) sender;
            if (!player.hasPermission("Rank.Admin")) {
                Color.noPerm(player);
                return false;
            }
            player.sendMessage(Color.tr("&eStarting the game..."));
            Main.getInstance().getGameManager().startGame();
        }
        return false;
    }
}
