package com.zerek.feathertips.managers;

import com.zerek.feathertips.FeatherTips;
import org.bukkit.configuration.ConfigurationSection;

import java.util.HashMap;
import java.util.Map;

public class MessageManager {

    private final FeatherTips plugin;

    private final Map<String,String> messagesMap = new HashMap<>();

    public MessageManager(FeatherTips plugin) {

        this.plugin = plugin;

        this.init();
    }

    private void init() {

        ConfigurationSection yml = plugin.getConfig().getConfigurationSection("messages");

        yml.getKeys(false).forEach(m -> messagesMap.put(m, yml.getString(m)));

    }

    public Map<String, String> getMessagesMap() {
        return messagesMap;
    }


}
