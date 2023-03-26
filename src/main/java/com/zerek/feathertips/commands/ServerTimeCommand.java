package com.zerek.feathertips.commands;

import com.zerek.feathertips.FeatherTips;
import net.kyori.adventure.text.minimessage.MiniMessage;
import net.kyori.adventure.text.minimessage.tag.resolver.Placeholder;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.jetbrains.annotations.NotNull;

import java.time.Instant;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;

public class ServerTimeCommand implements CommandExecutor {

    private final FeatherTips plugin;

    public ServerTimeCommand(FeatherTips plugin) {

        this.plugin = plugin;
    }

    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {

        if (!sender.hasPermission("feather.tips.servertime")) {

            sender.sendMessage(plugin.getMessagesManager().getMessageAsComponent("ErrorNoPermission"));

            return true;
        }

        sender.sendMessage(MiniMessage.miniMessage().deserialize(
                plugin.getMessagesManager().getMessageAsString("ServerTime"),
                Placeholder.unparsed("time",Instant.now().atZone(ZoneId.of(plugin.getConfigManager().getTimeZone())).format(DateTimeFormatter.ofPattern("hh:mm a")))));

        return true;
    }
}
