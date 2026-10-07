package dev.kurai.uhc.lobby;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.meta.SkullMeta;

public class LobbyItems {

    public static void giveItems(Player player) {
        player.getInventory().clear();

        // 1. Boussole (Slot 0)
        ItemStack compass = new ItemStack(Material.COMPASS);
        ItemMeta compassMeta = compass.getItemMeta();
        if (compassMeta != null) {
            compassMeta.setDisplayName("§a§lNavigation §7(Clic droit)");
            compass.setItemMeta(compassMeta);
        }

        // 2. Profil / Tête (Slot 1)
        ItemStack skull = new ItemStack(Material.SKULL_ITEM, 1, (short) 3);
        SkullMeta skullMeta = (SkullMeta) skull.getItemMeta();
        if (skullMeta != null) {
            skullMeta.setOwner(player.getName());
            skullMeta.setDisplayName("§e§lMon Profil §7(Clic droit)");
            skull.setItemMeta(skullMeta);
        }

        // 3. Visibilité / Teinture (Slot 7)
        ItemStack dye = new ItemStack(Material.INK_SACK, 1, (short) 10);
        ItemMeta dyeMeta = dye.getItemMeta();
        if (dyeMeta != null) {
            dyeMeta.setDisplayName("§b§lVisibilité §7: §aTous §7(Clic droit)");
            dye.setItemMeta(dyeMeta);
        }

        // 4. Amis (Slot 8)
        ItemStack friends = new ItemStack(Material.PRISMARINE_SHARD);
        ItemMeta friendsMeta = friends.getItemMeta();
        if (friendsMeta != null) {
            friendsMeta.setDisplayName("§d§lAmis §7(Clic droit)");
            friends.setItemMeta(friendsMeta);
        }

        player.getInventory().setItem(0, compass);
        player.getInventory().setItem(1, skull);
        player.getInventory().setItem(7, dye);
        player.getInventory().setItem(8, friends);

        player.updateInventory();
    }
}