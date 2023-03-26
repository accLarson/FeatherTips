package com.zerek.feathertips;

import com.zerek.feathertips.commands.*;
import com.zerek.feathertips.listeners.PlayerJoinListener;
import com.zerek.feathertips.managers.ConfigManager;
import com.zerek.feathertips.managers.LoginTipsManager;
import com.zerek.feathertips.managers.MessagesManager;
import com.zerek.feathertips.managers.TopicsManager;
import com.zerek.feathertips.tasks.AutoBroadcastTask;
import org.bukkit.command.CommandSender;
import org.bukkit.plugin.java.JavaPlugin;


public final class FeatherTips extends JavaPlugin {

    private ConfigManager configManager;

    private MessagesManager messagesManager;

    private TopicsManager topicManager;

    private LoginTipsManager loginTipsManager;

    @Override
    public void onEnable() {

        this.saveDefaultConfig();

        this.configManager = new ConfigManager(this);

        if (this.getResource("messages.yml") == null) this.saveResource("messages.yml", false);

        this.messagesManager = new MessagesManager(this);

        if (this.getResource("topics.yml") == null) this.saveResource("topics.yml", false);

        this.topicManager = new TopicsManager(this);

        if (this.getResource("login_tips.yml") == null) this.saveResource("login_tips.yml", false);

        this.loginTipsManager = new LoginTipsManager(this);


        getServer().getPluginManager().registerEvents(new PlayerJoinListener(this), this);


        getServer().getScheduler().scheduleSyncRepeatingTask(this, new AutoBroadcastTask(this), getConfig().getInt("period"), getConfig().getInt("period"));


        this.getCommand("tip").setExecutor(new TipCommand(this));

        this.getCommand("tip").setTabCompleter(new TipTabCompleter(this));


        this.getCommand("tips").setExecutor(new TipsCommand(this));

        this.getCommand("tips").setTabCompleter(new TipsTabCompleter());


        this.getCommand("broadcast").setExecutor((new BroadcastCommand(this)));

        this.getCommand("broadcast").setTabCompleter(new BroadcastTabCompleter(this));


        this.getCommand("servertime").setExecutor((new ServerTimeCommand(this)));

        this.getCommand("servertime").setTabCompleter((new ServerTimeTabCompleter()));
    }

    @Override
    public void onDisable() {

        getServer().getScheduler().cancelTasks(this);
    }

    public void reload(CommandSender sender) {

        getServer().getScheduler().cancelTasks(this);


        this.reloadConfig();


        this.configManager = new ConfigManager(this);

        this.messagesManager = new MessagesManager(this);

        this.topicManager = new TopicsManager(this);

        this.loginTipsManager = new LoginTipsManager(this);


        getServer().getScheduler().scheduleSyncRepeatingTask(this, new AutoBroadcastTask(this), configManager.getBroadcastDelayPeriod(), configManager.getBroadcastDelayPeriod());

        getServer().getPluginManager().registerEvents(new PlayerJoinListener(this), this);

        sender.sendMessage(this.messagesManager.getMessageAsComponent("Reloaded"));
    }

    public ConfigManager getConfigManager() {
        return configManager;
    }

    public MessagesManager getMessagesManager() {
        return messagesManager;
    }

    public TopicsManager getTopicManager() {
        return topicManager;
    }

    public LoginTipsManager getLoginTipsManager() {
        return loginTipsManager;
    }


}

