package com.zerek.feathertips.commands;

import com.zerek.feathertips.FeatherTips;
import net.md_5.bungee.api.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.ConsoleCommandSender;
import org.jetbrains.annotations.NotNull;

public class TipCommand implements CommandExecutor {

    private final FeatherTips plugin;
    public TipCommand(FeatherTips plugin) {
        this.plugin = plugin;
    }

    private boolean isValidTopic(String[] args){
        return plugin.getTopicManager().getTopics().contains(args [0]);
    }
    private boolean isValidPlayer(String[] args){
        return plugin.getServer().getOfflinePlayer(args[1]).isOnline();
    }

    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        if (args.length == 0) plugin.getTopicManager().tipMenu(sender);
        else if (args.length == 1){
            if (isValidTopic(args)) plugin.getTopicManager().tipSelf(sender, args[0]);
            else sender.sendMessage(ChatColor.of("#656b96") + "Invalid Topic");
        }
        else if (args.length == 2 && (sender.hasPermission("feather.tips.staff") || sender instanceof ConsoleCommandSender)){
            if (isValidTopic(args) && isValidPlayer(args)) plugin.getTopicManager().tipOther(sender, plugin.getServer().getPlayer(args[1]), args[0]);
            else sender.sendMessage(ChatColor.of("#656b96") + "Invalid topic or player not online");
        }
        else sender.sendMessage(ChatColor.of("#656b96") + "invalid command - /info [topic]");
        return true;
    }
}
