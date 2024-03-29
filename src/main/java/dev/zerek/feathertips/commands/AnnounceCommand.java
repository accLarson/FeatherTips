package dev.zerek.feathertips.commands;

import dev.zerek.feathertips.FeatherTips;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.jetbrains.annotations.NotNull;

public class AnnounceCommand implements CommandExecutor {

    private final FeatherTips plugin;

    public AnnounceCommand(FeatherTips plugin) {
        this.plugin = plugin;
    }

    @Override
    public boolean onCommand(@NotNull CommandSender commandSender, @NotNull Command command, @NotNull String arg, @NotNull String[] args) {

        // Check if sender has permission
        if (!commandSender.hasPermission("feather.tips.announce")) {
            commandSender.sendMessage(plugin.getMessagesManager().getMessageAsComponent("ErrorNoPermission"));
            return true;
        }

        // Check if player provided correct amount of arguments
        if (args.length >= 1) {
            commandSender.sendMessage(plugin.getMessagesManager().getMessageAsComponent("ErrorInvalidArgumentCount"));
            return true;
        }

        // Checks passed ----------------------------------------------------------------









        return false;
    }
}
