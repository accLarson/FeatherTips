package com.zerek.feathertips.commands;

import com.zerek.feathertips.FeatherTips;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.jetbrains.annotations.NotNull;

public class TipsCommand implements CommandExecutor {

    private final FeatherTips plugin;

    public TipsCommand(FeatherTips plugin) {
        this.plugin = plugin;
    }

    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {

        if (args.length == 0) plugin.getTopicManager().tipMenu(sender);

        else if (sender.hasPermission("feather.tips.reload") && args[0].equals("reload")) plugin.reload(sender);

        return false;
    }
}
