package com.zerek.feathertips.listeners;

import com.zerek.feathertips.FeatherTips;
import net.kyori.adventure.text.minimessage.MiniMessage;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

import java.util.Random;

public class PlayerJoinListener implements Listener {

    private final FeatherTips plugin;
    public PlayerJoinListener(FeatherTips plugin) {
        this.plugin = plugin;
    }

    MiniMessage mm = MiniMessage.miniMessage();

    public static Integer getRandomInt(Integer max) {
        Random ran = new Random();
        return ran.nextInt(max);
    }

    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent event){
        if (event.getPlayer().hasPermission("group.administrator"));
        else if (event.getPlayer().hasPermission("group.moderator"))event.getPlayer().sendMessage(mm.deserialize(plugin.getModTips().get(getRandomInt(plugin.getModTips().size()))));
        else if (event.getPlayer().hasPermission("group.assistant"))event.getPlayer().sendMessage(mm.deserialize(plugin.getAssTips().get(getRandomInt(plugin.getAssTips().size()))));

    }
}
