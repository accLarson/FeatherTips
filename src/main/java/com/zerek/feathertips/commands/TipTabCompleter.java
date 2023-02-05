package com.zerek.feathertips.commands;

import com.zerek.feathertips.FeatherTips;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.List;

public class TipTabCompleter implements TabCompleter {

    private final FeatherTips plugin;

    public TipTabCompleter(FeatherTips plugin) {
        this.plugin = plugin;
    }

    @Override
    public @Nullable List<String> onTabComplete(@NotNull CommandSender sender, @NotNull Command command, @NotNull String alias, @NotNull String[] args) {

        List<String> all = new ArrayList<String>(plugin.getTopicManager().getTopics());

        if (args.length == 1) {

            List<String> match = new ArrayList<String>();

            for (String topic : all) {

                if (topic.toLowerCase().startsWith(args[0].toLowerCase())) match.add(topic);
            }
            return match;
        }
        else if (args.length == 2 && sender.hasPermission("feather.tips.staff")) return null;

        return new ArrayList<String>();
    }
}
