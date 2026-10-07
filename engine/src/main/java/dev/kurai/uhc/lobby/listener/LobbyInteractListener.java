package dev.kurai.uhc.lobby.listener;

import dev.kurai.uhc.lobby.gui.NavigationMenu;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;

public class LobbyInteractListener implements Listener {

    @EventHandler
    public void onInteract(PlayerInteractEvent event) {
        Player player = event.getPlayer();
        ItemStack item = event.getItem();

        if (item == null || item.getType() == Material.AIR) return;

        if (event.getAction() == Action.RIGHT_CLICK_AIR || event.getAction() == Action.RIGHT_CLICK_BLOCK) {
            if (item.getType() == Material.COMPASS) {
                event.setCancelled(true);
                NavigationMenu.open(player);
            }
        }
    }

    @EventHandler
    public void onInventoryClick(InventoryClickEvent event) {
        if (!event.getView().getTitle().equals(NavigationMenu.TITLE)) return;

        event.setCancelled(true); // Empêche de prendre les items

        if (event.getCurrentItem() == null) return;

        Player player = (Player) event.getWhoClicked();

        if (event.getCurrentItem().getType() == Material.GOLDEN_APPLE) {
            player.sendMessage("§a[UHC] Connexion au serveur UHC...");
            // Logique de TP BungeeCord/Velocity à ajouter ici
        }
    }
}