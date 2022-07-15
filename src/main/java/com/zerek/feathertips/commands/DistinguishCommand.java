package com.zerek.feathertips.commands;

import com.zerek.feathertips.FeatherTips;
import net.kyori.adventure.text.minimessage.MiniMessage;
import net.kyori.adventure.text.minimessage.tag.resolver.Placeholder;
import net.kyori.adventure.text.serializer.legacy.LegacyComponentSerializer;
import net.md_5.bungee.api.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

public class DistinguishCommand implements CommandExecutor {

    private final FeatherTips plugin;
    public DistinguishCommand(FeatherTips plugin) {
        this.plugin = plugin;
    }

    MiniMessage mm = MiniMessage.miniMessage();
    public static final LegacyComponentSerializer ampersandRGB = LegacyComponentSerializer.builder().character('§').hexCharacter('#').hexColors().build();

    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        if ((args.length >= 1) && (sender.hasPermission("feather.tips.distinguish")) && (sender instanceof Player)){
            if (sender.hasPermission("group.administrator")) ((Player) sender).chat(ampersandRGB.serialize(mm.deserialize(plugin.getDistinguishColors().get("administrator"), Placeholder.unparsed("message", String.join(" ", args)))));
            else if (sender.hasPermission("group.moderator")) ((Player) sender).chat(ampersandRGB.serialize(mm.deserialize(plugin.getDistinguishColors().get("moderator"), Placeholder.unparsed("message", String.join(" ",args)))));
            else if (sender.hasPermission("group.assistant")) ((Player) sender).chat(ampersandRGB.serialize(mm.deserialize(plugin.getDistinguishColors().get("assistant"), Placeholder.unparsed("message", String.join(" ",args)))));
            else if (sender.hasPermission("group.donor")) ((Player) sender).chat(ampersandRGB.serialize(mm.deserialize(plugin.getDistinguishColors().get("donor"), Placeholder.unparsed("message", String.join(" ",args)))));
            return true;
        } else sender.sendMessage(ChatColor.of("#E4453A") + "no permission or no message included - /distinguish <message>");
            return true;
    }
}
