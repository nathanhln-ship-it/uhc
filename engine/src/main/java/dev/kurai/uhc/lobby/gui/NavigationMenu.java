package dev.kurai.uhc.lobby.gui;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.Arrays;

public class NavigationMenu {

    public static final String TITLE = "§8Navigation";

    public static void open(Player player) {
        Inventory inv = Bukkit.createInventory(null, 45, TITLE);

        // Vitres grises de fond
        ItemStack glass = new ItemStack(Material.STAINED_GLASS_PANE, 1, (short) 7);
        ItemMeta glassMeta = glass.getItemMeta();
        if (glassMeta != null) {
            glassMeta.setDisplayName(" ");
            glass.setItemMeta(glassMeta);
        }

        for (int i = 0; i < inv.getSize(); i++) {
            inv.setItem(i, glass);
        }

        // Item central UHC (Golden Apple)
        ItemStack uhc = new ItemStack(Material.GOLDEN_APPLE);
        ItemMeta uhcMeta = uhc.getItemMeta();
        if (uhcMeta != null) {
            uhcMeta.setDisplayName("§e§lUHC");
            uhcMeta.setLore(Arrays.asList(
                    "§7(§a?§7) Disponible en §b1.8+",
                    "",
                    "§7Prépare ton équipement et",
                    "§caffronte §7le reste des joueurs",
                    "§7avec l'aide de dizaines de",
                    "§ascénarios §7immersifs.",
                    "",
                    "§f• Il y a §a46 §fjoueurs en jeu",
                    "",
                    "§e§l> Clique-ici pour y accéder"
            ));
            uhc.setItemMeta(uhcMeta);
        }

        inv.setItem(22, uhc);

        player.openInventory(inv);
    }
}