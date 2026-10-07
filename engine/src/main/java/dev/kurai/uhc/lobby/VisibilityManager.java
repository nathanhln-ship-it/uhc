package dev.kurai.uhc.lobby;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;
import java.util.List;

public class VisibilityManager {

    private final List<Player> hiddenPlayers;

    public VisibilityManager() {
        this.hiddenPlayers = new ArrayList<>();
    }

    public void toggleVisibility(Player player) {
        if (this.hiddenPlayers.contains(player)) {
            this.hiddenPlayers.remove(player);
            this.showAllPlayers(player);
            this.updateItem(player, true);
            player.sendMessage("§a[Valhalla] Vous voyez désormais tous les joueurs.");
        } else {
            this.hiddenPlayers.add(player);
            this.hideAllPlayers(player);
            this.updateItem(player, false);
            player.sendMessage("§c[Valhalla] Les autres joueurs sont maintenant masqués.");
        }
    }

    private void showAllPlayers(Player player) {
        for (Player target : Bukkit.getOnlinePlayers()) {
            player.showPlayer(target);
        }
    }

    private void hideAllPlayers(Player player) {
        for (Player target : Bukkit.getOnlinePlayers()) {
            player.hidePlayer(target);
        }
    }

    private void updateItem(Player player, boolean isVisible) {
        short colorData = isVisible ? (short) 10 : (short) 8;
        String itemName = isVisible ? "§b§lVisibilité §7: §aTous §7(Clic droit)" : "§b§lVisibilité §7: §cPersonne §7(Clic droit)";

        ItemStack dye = new ItemStack(Material.INK_SACK, 1, colorData);
        ItemMeta meta = dye.getItemMeta();
        if (meta != null) {
            meta.setDisplayName(itemName);
            dye.setItemMeta(meta);
        }

        player.getInventory().setItem(7, dye);
        player.updateInventory();
    }
}