package dev.zerek.feathertips.listeners;

import dev.zerek.feathertips.FeatherTips;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

public class PlayerJoinListener implements Listener {

    private final FeatherTips plugin;

    public PlayerJoinListener(FeatherTips plugin) {

        this.plugin = plugin;
    }


    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent event){

        plugin.getLoginTipsManager().getLoginTipsMap().keySet().forEach(p -> {

            // If the player has the correct permission, have the LoginTipManager send a random login tip.
            if (event.getPlayer().hasPermission(p)) plugin.getLoginTipsManager().sendLoginTip(event.getPlayer(),p);
        });
    }
}
