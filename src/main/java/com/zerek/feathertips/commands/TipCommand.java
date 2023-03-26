package com.zerek.feathertips.commands;

import com.zerek.feathertips.FeatherTips;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.jetbrains.annotations.NotNull;

public class TipCommand implements CommandExecutor {

    private final FeatherTips plugin;

    public TipCommand(FeatherTips plugin) {
        this.plugin = plugin;
    }

    private boolean isValidTopic(String topic){

        return plugin.getTopicManager().getTopicsMap().containsKey(topic);
    }

    private boolean isValidPlayer(String username){

        return plugin.getServer().getOfflinePlayer(username).isOnline();
    }

    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {

        switch (args.length) {

            // /tip
            case 0:

                if (!sender.hasPermission("feather.tips.tip")) {

                    sender.sendMessage(plugin.getMessagesManager().getMessageAsComponent("ErrorNoPermission"));

                    return true;
                }

                // Checks passed ----------------------------------------------------------------


                sender.sendMessage(plugin.getMessagesManager().getMessageAsComponent("Help"));

                return true;

            case 1:

                if (!sender.hasPermission("feather.tips.tip")) {

                    sender.sendMessage(plugin.getMessagesManager().getMessageAsComponent("ErrorNoPermission"));

                    return true;
                }

                if (!isValidTopic(args[0])) {

                    sender.sendMessage(plugin.getMessagesManager().getMessageAsComponent("ErrorInvalidTopic"));

                    return true;
                }

                // Checks passed ----------------------------------------------------------------

                plugin.getTopicManager().tipSelf(sender, args[0]);

                return  true;

            case 2:

                if (!sender.hasPermission("feather.tips.tip.others")) {

                    sender.sendMessage(plugin.getMessagesManager().getMessageAsComponent("ErrorNoPermission"));

                    return true;
                }

                if (!isValidTopic(args[0])) {

                    sender.sendMessage(plugin.getMessagesManager().getMessageAsComponent("ErrorInvalidTopic"));

                    return true;
                }

                if (!isValidPlayer(args[1])) {

                    sender.sendMessage(plugin.getMessagesManager().getMessageAsComponent("ErrorPlayerOffline"));

                    return true;
                }

                // Checks passed ----------------------------------------------------------------

                plugin.getTopicManager().tipOther(sender, plugin.getServer().getPlayer(args[1]), args[0]);

                return true;

            default:

                sender.sendMessage(plugin.getMessagesManager().getMessageAsComponent("ErrorInvalidArgumentCount"));

                return true;
        }
    }
}
