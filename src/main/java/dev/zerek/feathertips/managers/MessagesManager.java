package dev.zerek.feathertips.managers;

import dev.zerek.feathertips.FeatherTips;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.minimessage.MiniMessage;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

public class MessagesManager {

    private final FeatherTips plugin;

    File file;

    private FileConfiguration yml;

    private final Map<String,String> messagesMap = new HashMap<>();

    public MessagesManager(FeatherTips plugin) {

        this.plugin = plugin;

        this.init();
    }

    private void init() {

        if (!new File(plugin.getDataFolder(), "messages.yml").exists()) plugin.saveResource("messages.yml", false);

        file = new File(plugin.getDataFolder() + File.separator + "messages.yml");

        yml = YamlConfiguration.loadConfiguration(file);

        this.generateMessagesMap();
    }

    private void generateMessagesMap() {

        yml.getKeys(false).forEach(m -> messagesMap.put(m, yml.getString(m)));
    }

    public Map<String, String> getMessagesMap() {
        return messagesMap;
    }

    public String getMessageAsString(String key) {
        return messagesMap.get(key);
    }

    public Component getMessageAsComponent(String key) {
        return MiniMessage.miniMessage().deserialize(messagesMap.get(key));
    }
}
