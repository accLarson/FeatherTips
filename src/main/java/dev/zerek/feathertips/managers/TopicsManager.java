package dev.zerek.feathertips.managers;

import dev.zerek.feathertips.FeatherTips;
import dev.zerek.feathertips.data.Topic;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.TextComponent;
import net.kyori.adventure.text.event.ClickEvent;
import net.kyori.adventure.text.event.HoverEvent;
import net.kyori.adventure.text.minimessage.MiniMessage;
import net.kyori.adventure.text.minimessage.tag.resolver.Placeholder;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;

import java.io.File;
import java.util.HashMap;
import java.util.Set;
import java.util.stream.Collectors;

import static net.kyori.adventure.text.Component.text;

public class TopicsManager {

    private final FeatherTips plugin;

    File file;

    private FileConfiguration yml;

    private final HashMap<String, Topic> topicsMap = new HashMap<>();

    MiniMessage mm = MiniMessage.miniMessage();


    public TopicsManager(FeatherTips plugin) {

        this.plugin = plugin;

        this.init();
    }


    private void init() {

        file = new File(plugin.getDataFolder() + File.separator + "topics.yml");

        yml = YamlConfiguration.loadConfiguration(file);

        this.generateMessagesMap();
    }

    private void generateMessagesMap() {

        //iterate through all topics and get all message formats from the topics.yml
        for (String topic : yml.getKeys(false)){

            //get the short-format message
            TextComponent shortFormat = (TextComponent) mm.deserialize(yml.getString(topic + ".short-format"));

            //get the long-format message
            TextComponent longFormat = (TextComponent) mm.deserialize(yml.getString(topic + ".long-format"));

            //add a Topic object to the hash map with the above values for short-format and long-format and topic;
            this.topicsMap.put(topic, new Topic(shortFormat, longFormat, topic));
        }
    }


    public Set<String> getTopicsMapKeys(){

        return topicsMap.keySet();
    }

    public HashMap<String, Topic> getTopicsMap() {

        return topicsMap;
    }

    //broadcast short format
    public void broadcast (String topic){

        plugin.getServer().broadcast(topicsMap.get(topic).getShortFormat());
    }

    //Tip to self
    public void tipSelf (CommandSender sender, String topic){

        sender.sendMessage(plugin.getMessagesManager().getMessageAsComponent("PrefixLine"));

        sender.sendMessage(topicsMap.get(topic).getLongFormat());

        sender.sendMessage(plugin.getMessagesManager().getMessageAsComponent("SuffixLine"));
    }

    //Tip to other player
    public void tipOther (CommandSender sender, Player player, String topic){

        plugin.getServer().broadcast(mm.deserialize(plugin.getMessagesManager().getMessageAsString("TipOthers"), Placeholder.unparsed("staff_username",sender.getName()), Placeholder.unparsed("topic",topic), Placeholder.unparsed("username",player.getName())));

        player.sendMessage(plugin.getMessagesManager().getMessageAsComponent("PrefixLine"));

        player.sendMessage(topicsMap.get(topic).getLongFormat());

        player.sendMessage(plugin.getMessagesManager().getMessageAsComponent("SuffixLine"));
    }


    //tip menu
    public void displayTipsMenu (CommandSender sender) {

        sender.sendMessage(plugin.getMessagesManager().getMessageAsComponent("PrefixLine"));

        TextComponent tipMenuDisplay = text("");

        for (String topic : getTopicsMapKeys().stream().sorted().collect(Collectors.toList())) {

            Component hover = mm.deserialize(plugin.getMessagesManager().getMessageAsString("MenuHoverableTitle"), Placeholder.unparsed("topic", topic))
                    .append(Component.text("\n"))
                    .append(topicsMap.get(topic).getLongFormat());

            Component hoverable = Component.text(topic).hoverEvent(HoverEvent.showText(hover)).clickEvent(ClickEvent.suggestCommand("/tip " + topic + " "));

            Component label = mm.deserialize(plugin.getMessagesManager().getMessageAsString("MenuLabel"), Placeholder.component("topic",hoverable));

            tipMenuDisplay = tipMenuDisplay.append(label).append(Component.text(" "));
        }

        sender.sendMessage(tipMenuDisplay);

        sender.sendMessage(plugin.getMessagesManager().getMessageAsComponent("SuffixLine"));
    }
}
