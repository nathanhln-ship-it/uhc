package dev.kurai.uhc.lobby.listener;

import dev.kurai.uhc.lobby.VisibilityManager;
import dev.kurai.uhc.lobby.gui.NavigationMenu;
import dev.kurai.uhc.lobby.gui.ProfileMenu;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;

public class LobbyInteractListener implements Listener {

    private final ProfileMenu profileMenu;
    private final VisibilityManager visibilityManager;

    public LobbyInteractListener() {
        this.profileMenu = new ProfileMenu();
        this.visibilityManager = new VisibilityManager();
    }

    @EventHandler
    public void onInteract(PlayerInteractEvent event) {
        Player player = event.getPlayer();
        ItemStack item = event.getItem();

        if (item == null || item.getType() == Material.AIR) {
            return;
        }

        if (event.getAction() == Action.RIGHT_CLICK_AIR || event.getAction() == Action.RIGHT_CLICK_BLOCK) {
            if (item.getType() == Material.COMPASS) {
                event.setCancelled(true);
                NavigationMenu.open(player);
            } else if (item.getType() == Material.SKULL_ITEM) {
                event.setCancelled(true);
                this.profileMenu.open(player);
            } else if (item.getType() == Material.INK_SACK) {
                event.setCancelled(true);
                this.visibilityManager.toggleVisibility(player);
            }
        }
    }

    @EventHandler
    public void onInventoryClick(InventoryClickEvent event) {
        String viewTitle = event.getView().getTitle();

        if (viewTitle.equals(NavigationMenu.TITLE) || viewTitle.equals(this.profileMenu.getTitle())) {
            event.setCancelled(true);

            if (event.getCurrentItem() == null) {
                return;
            }

            Player player = (Player) event.getWhoClicked();

            if (event.getCurrentItem().getType() == Material.GOLDEN_APPLE) {
                player.sendMessage("§a[UHC] Connexion à la file d'attente...");
            }
        }
    }
}