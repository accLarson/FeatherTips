package com.zerek.feathertips.listeners;

import com.zerek.feathertips.FeatherTips;
import io.papermc.paper.event.player.AsyncChatEvent;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.TextColor;
import net.kyori.adventure.text.serializer.plain.PlainTextComponentSerializer;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;

public class AsyncChatListener implements Listener {

    private final FeatherTips plugin;

    public AsyncChatListener(FeatherTips plugin) {
        this.plugin = plugin;
    }

    @EventHandler
    public void onAsyncChat(AsyncChatEvent event) {

        if (PlainTextComponentSerializer.plainText().serialize(event.message()).startsWith(plugin.getDistinguishTag()) && event.getPlayer().hasPermission("feather.tips.distinguish")) {

            event.message(event.message().replaceText(b -> b.matchLiteral(plugin.getDistinguishTag()).replacement(Component.text(""))));

            if (event.getPlayer().hasPermission("group.administrator")) event.message(event.message().color(TextColor.fromCSSHexString(plugin.getDistinguishColors().get("administrator"))));

            else if (event.getPlayer().hasPermission("group.moderator")) event.message(event.message().color(TextColor.fromCSSHexString(plugin.getDistinguishColors().get("moderator"))));

            else if (event.getPlayer().hasPermission("group.assistant")) event.message(event.message().color(TextColor.fromCSSHexString(plugin.getDistinguishColors().get("assistant"))));

            else if (event.getPlayer().hasPermission("group.donor")) event.message(event.message().color(TextColor.fromCSSHexString(plugin.getDistinguishColors().get("donor"))));
        }
    }
}
