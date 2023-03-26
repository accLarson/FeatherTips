package com.zerek.feathertips.managers;

import com.zerek.feathertips.FeatherTips;
import com.zerek.feathertips.data.Topic;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.TextComponent;
import net.kyori.adventure.text.minimessage.MiniMessage;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;

import java.io.File;
import java.util.HashMap;
import java.util.List;
import java.util.Random;

public class LoginTipsManager {

    private final FeatherTips plugin;

    File file;

    private FileConfiguration yml;

    private final HashMap<String, List<String>> loginTipsMap = new HashMap<>();

    private final Random random = new Random();

    MiniMessage mm = MiniMessage.miniMessage();


    public LoginTipsManager(FeatherTips plugin) {

        this.plugin = plugin;

        this.init();
    }


    private void init() {

        file = new File(plugin.getDataFolder() + File.separator + "login_tips.yml");

        yml = YamlConfiguration.loadConfiguration(file);

        //iterate through and map all tip-groups specified in login_tips.yml.
        yml.getKeys(false).forEach(g -> this.loginTipsMap.put(g, yml.getStringList(g)));
    }

    public void sendLoginTip(Player player, String group) {

        player.sendMessage(mm.deserialize(loginTipsMap.get(group).get(random.nextInt(loginTipsMap.get(group).size()))));
    }

    public HashMap<String, List<String>> getLoginTipsMap() {
        return loginTipsMap;
    }
}
