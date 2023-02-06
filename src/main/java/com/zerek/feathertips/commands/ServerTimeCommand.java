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

    String serverTime;

    public ServerTimeCommand(FeatherTips plugin) {

        serverTime = plugin.getMessagesManager().getMessageAsString("server-time");
    }

    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {

        sender.sendMessage(MiniMessage.miniMessage().deserialize(serverTime, Placeholder.unparsed("time",Instant.now().atZone(ZoneId.of("Canada/Eastern")).format(DateTimeFormatter.ofPattern("hh:mm a")))));

        return true;
    }
}
