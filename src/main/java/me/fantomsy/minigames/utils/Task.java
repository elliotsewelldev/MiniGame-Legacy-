package me.fantomsy.minigames.utils;

import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class Task {

    public static void giveItem(Player player, ItemStack item, String name, int slot) {
        ItemMeta meta = item.getItemMeta();
        meta.setDisplayName(Color.tr(name));
        item.setItemMeta(meta);
        player.getInventory().setItem(slot, item);
    }

    public static void giveArmour(Player player, ItemStack helmet, ItemStack chestplate, ItemStack leggings, ItemStack boots) {
        player.getInventory().setHelmet(helmet);
        player.getInventory().setChestplate(chestplate);
        player.getInventory().setLeggings(leggings);
        player.getInventory().setBoots(boots);
    }
}
