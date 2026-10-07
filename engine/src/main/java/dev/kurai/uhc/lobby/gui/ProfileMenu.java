package dev.kurai.uhc.lobby.gui;

import net.luckperms.api.LuckPerms;
import net.luckperms.api.LuckPermsProvider;
import net.luckperms.api.model.user.User;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.meta.SkullMeta;

import java.util.Arrays;

public class ProfileMenu {

    private final String title;

    public ProfileMenu() {
        this.title = "§8Mon Profil";
    }

    public String getTitle() {
        return this.title;
    }

    public void open(Player player) {
        Inventory inv = Bukkit.createInventory(null, 27, this.title);

        ItemStack glass = new ItemStack(Material.STAINED_GLASS_PANE, 1, (short) 7);
        ItemMeta glassMeta = glass.getItemMeta();
        if (glassMeta != null) {
            glassMeta.setDisplayName(" ");
            glass.setItemMeta(glassMeta);
        }

        for (int i = 0; i < inv.getSize(); i++) {
            inv.setItem(i, glass);
        }

        ItemStack skull = new ItemStack(Material.SKULL_ITEM, 1, (short) 3);
        SkullMeta skullMeta = (SkullMeta) skull.getItemMeta();
        if (skullMeta != null) {
            skullMeta.setOwner(player.getName());
            skullMeta.setDisplayName("§e" + player.getName());

            String rankPrefix = this.getRankPrefix(player);

            skullMeta.setLore(Arrays.asList(
                    "§7Grade : " + rankPrefix,
                    "",
                    "§7Serveur : §bValhalla UHC",
                    "",
                    "§aStatistiques :",
                    "§7Kills : §e0",
                    "§7Victoires : §e0"
            ));
            skull.setItemMeta(skullMeta);
        }

        inv.setItem(13, skull);
        player.openInventory(inv);
    }

    private String getRankPrefix(Player player) {
        try {
            LuckPerms api = LuckPermsProvider.get();
            User user = api.getUserManager().getUser(player.getUniqueId());
            if (user != null) {
                String prefix = user.getCachedData().getMetaData().getPrefix();
                if (prefix != null) {
                    return prefix.replace("&", "§");
                }
            }
        } catch (Exception e) {
            // Permet de ne pas faire crasher le menu si LuckPerms n'est pas encore actif
        }
        return "§7Joueur";
    }
}