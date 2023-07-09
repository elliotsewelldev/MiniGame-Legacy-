package me.fantomsy.minigames.utils;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

public class Color {

    public static String tr(String string) {
        return ChatColor.translateAlternateColorCodes('&', string);
    }

    public static String form(String name, String info) {
        return ChatColor.GREEN + "[" + name + "] " + ChatColor.GRAY + info;
    }

    public static void log(String string) {
        Bukkit.getConsoleSender().sendMessage(form("Log", string));
    }

    public static void noPerm(Player player) {
        player.sendMessage(tr("&cYou do not have permission to do that!"));
    }

    public static String prefix = "&8[&bMiniGames&8] &7";

    public static void debug(String string) {
        Bukkit.broadcastMessage("Debug : " + string);
    }
}
