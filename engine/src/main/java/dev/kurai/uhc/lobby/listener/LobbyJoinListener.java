package dev.kurai.uhc.lobby.listener;

import dev.kurai.uhc.lobby.LobbyItems;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

public class LobbyJoinListener implements Listener {

    @EventHandler
    public void onJoin(PlayerJoinEvent event) {
        Player player = event.getPlayer();

        // Remise à zéro du joueur à la connexion
        player.setHealth(20.0);
        player.setFoodLevel(20);
        player.setLevel(0);

        // Distribution des items du lobby
        LobbyItems.giveItems(player);
    }
}