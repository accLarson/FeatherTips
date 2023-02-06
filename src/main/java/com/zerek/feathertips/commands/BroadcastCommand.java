package com.zerek.feathertips.commands;

import com.zerek.feathertips.FeatherTips;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.jetbrains.annotations.NotNull;

public class BroadcastCommand implements CommandExecutor {

    private final FeatherTips plugin;

    public BroadcastCommand(FeatherTips plugin) {
        this.plugin = plugin;
    }

    private boolean isValidTopic(String topic){

        return plugin.getTopicManager().getTopicsMap().containsKey(topic);
    }

    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {

        // Check if sender has permission
        if (!sender.hasPermission("feather.tips.staff")) {

            sender.sendMessage(plugin.getMessagesManager().getMessageAsComponent("ErrorNoPermission"));

            return true;
        }

        // Check if player provided correct amount of arguments
        if (args.length != 1) {

            sender.sendMessage(plugin.getMessagesManager().getMessageAsComponent("ErrorInvalidArgumentCount"));

            return true;
        }

        // Check if sender has provided a valid topic
        if (!isValidTopic(args[0])) {

            sender.sendMessage(plugin.getMessagesManager().getMessageAsComponent("ErrorInvalidTopic"));

            return true;
        }

        // Checks passed ----------------------------------------------------------------

        plugin.getTopicManager().broadcast(args[0]);

        return true;
    }
}
