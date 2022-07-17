package com.zerek.feathertips.managers;

import com.zerek.feathertips.FeatherTips;
import com.zerek.feathertips.data.Topic;
import net.kyori.adventure.text.TextComponent;
import net.kyori.adventure.text.event.ClickEvent;
import net.kyori.adventure.text.event.HoverEvent;
import net.kyori.adventure.text.format.TextColor;
import net.kyori.adventure.text.minimessage.MiniMessage;
import net.kyori.adventure.text.minimessage.tag.resolver.Placeholder;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.HashMap;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import static net.kyori.adventure.text.Component.text;

public class TopicManager {

    //fields
    private final FeatherTips plugin;
    private final HashMap<String, Topic> topics = new HashMap<String, Topic>();
    private final TextComponent prefixLine;
    private final TextComponent suffixLine;
    private final String tipOthersText;

    MiniMessage mm = MiniMessage.miniMessage();

    //constructor
    public TopicManager(FeatherTips plugin) {

        this.plugin = plugin;

        //get prefix line, suffix line, and help text from config
        this.prefixLine = (TextComponent) mm.deserialize(plugin.getConfig().getString("prefix-line"));
        this.suffixLine = (TextComponent) mm.deserialize(plugin.getConfig().getString("suffix-line"));
        TextComponent usage = (TextComponent) mm.deserialize(plugin.getConfig().getString("tip-usage"));
        this.tipOthersText = plugin.getConfig().getString("tip-others");

        //iterate through all topics and get all message formats from the config.yml
        for (String topic : plugin.getConfig().getConfigurationSection("topics").getKeys(false)){
            //get the short-format message
            TextComponent shortFormat = (TextComponent) mm.deserialize(plugin.getConfig().getString("topics." + topic + ".short-format"));
            //get the long-format message
            TextComponent longFormat = (TextComponent) mm.deserialize(plugin.getConfig().getString("topics." + topic + ".long-format"));
            //add a Topic object to the hash map with the above values for short-format and long-format and topic;
            this.topics.put(topic, new Topic(shortFormat, longFormat, topic));
        }
    }

    //get list of all topics
    public Set<String> getTopics(){
        return this.topics.keySet();
    }

    //broadcast short format
    public void broadcast(String topic){
        plugin.getServer().broadcast(topics.get(topic).getShortFormat());
    }

    //Tip to self
    public void tipSelf (CommandSender sender, String topic){
        sender.sendMessage(prefixLine);
        sender.sendMessage(topics.get(topic).getLongFormat());
        sender.sendMessage(suffixLine);
    }

    //Tip to other player
    public void tipOther (CommandSender sender, Player player, String topic){
        //plugin.getServer().broadcast(mm.parse(tipOthersText,"staff_username",sender.getName(),"topic",topic,"username",player.getName()));
        plugin.getServer().broadcast(mm.deserialize(tipOthersText, Placeholder.unparsed("staff_username",sender.getName()), Placeholder.unparsed("topic",topic), Placeholder.unparsed("username",player.getName())));
        player.sendMessage(prefixLine);
        player.sendMessage(topics.get(topic).getLongFormat());
        player.sendMessage(suffixLine);
    }

    //tip menu
    public void tipMenu (CommandSender sender) {
        sender.sendMessage(prefixLine);
        TextComponent topicDisplay = text("");
        TextComponent labelPrefix = text("[").color(TextColor.fromHexString("#949bd1"));
        TextComponent labelSuffix = text("] ").color(TextColor.fromHexString("#949bd1"));
        List<String> topicList = getTopics().stream().sorted().collect(Collectors.toList());
        for (String topic : topicList) {
            TextComponent label = text(topic).color(TextColor.fromHexString("#FFFFFF"));
            TextComponent hover = text("/tip " + topic).color(TextColor.fromHexString("#949bd1")).append(text("\n")).append(topics.get(topic).getLongFormat());
            label = labelPrefix.append(label.hoverEvent(HoverEvent.showText(hover))).append(labelSuffix).clickEvent(ClickEvent.suggestCommand("/tip " + topic + " "));
            topicDisplay = topicDisplay.append(label);
        }
        sender.sendMessage(topicDisplay);
        sender.sendMessage(suffixLine);
    }
}
