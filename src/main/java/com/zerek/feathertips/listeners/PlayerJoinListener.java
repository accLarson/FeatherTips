package com.zerek.feathertips.listeners;

import com.zerek.feathertips.FeatherTips;
import net.kyori.adventure.text.minimessage.MiniMessage;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

import java.util.List;
import java.util.Random;

public class PlayerJoinListener implements Listener {

    private final FeatherTips plugin;

    private final MiniMessage mm = MiniMessage.miniMessage();

    private final Random random = new Random();

    public PlayerJoinListener(FeatherTips plugin) {

        this.plugin = plugin;
    }


    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent event){

        if (event.getPlayer().hasPermission("group.administrator"));

        else if (event.getPlayer().hasPermission("group.moderator")) {

            List<String> modTips = plugin.getConfigManager().getModTips();

            event.getPlayer().sendMessage(modTips.get(random.nextInt(modTips.size())));
        }

        else if (event.getPlayer().hasPermission("group.assistant")) {

            List<String> assTips = plugin.getConfigManager().getAssTips();

            event.getPlayer().sendMessage(assTips.get(random.nextInt(assTips.size())));
        }

    }
}
