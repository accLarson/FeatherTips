package com.zerek.feathertips.commands;

import com.zerek.feathertips.FeatherTips;
import net.md_5.bungee.api.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.ConsoleCommandSender;
import org.jetbrains.annotations.NotNull;

public class BroadcastCommand implements CommandExecutor {

    private final FeatherTips plugin;
    public BroadcastCommand(FeatherTips plugin) {
        this.plugin = plugin;
    }

    private boolean isValidTopic(String[] args){
        return plugin.getTopicManager().getTopics().contains(args [0]);
    }

    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        if (args.length == 1 && (sender.hasPermission("feather.tips.staff") || sender instanceof ConsoleCommandSender)){
            if (isValidTopic(args))plugin.getTopicManager().broadcast(args[0]);
            else sender.sendMessage(ChatColor.of("#E4453A") + "Invalid Topic");
        }
        else sender.sendMessage(ChatColor.of("#E4453A") + "no permission or invalid command - /broadcast [topic]");
        return true;
    }
}
