package com.zerek.feathertips.commands;

import com.zerek.feathertips.FeatherTips;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.jetbrains.annotations.NotNull;

public class FeatherTipsCommand implements CommandExecutor {
    private final FeatherTips plugin;

    public FeatherTipsCommand(FeatherTips plugin) {
        this.plugin = plugin;
    }

    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        if (sender.isOp()){
            if (args.length == 0) return false;
            if (args[0].equalsIgnoreCase("reload")){
                plugin.reload();
                sender.sendMessage("FeatherTips reloaded");
                return true;
            }
        }
        sender.sendMessage("You do not have access to that command.");
        return true;
    }
}
