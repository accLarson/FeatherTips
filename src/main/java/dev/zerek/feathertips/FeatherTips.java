package dev.zerek.feathertips;

import dev.zerek.feathertips.commands.*;
import dev.zerek.feathertips.listeners.PlayerJoinListener;
import dev.zerek.feathertips.managers.ConfigManager;
import dev.zerek.feathertips.managers.LoginTipsManager;
import dev.zerek.feathertips.managers.MessagesManager;
import dev.zerek.feathertips.managers.TopicsManager;
import dev.zerek.feathertips.tasks.AutoBroadcastTask;
import org.bukkit.command.CommandSender;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;


public final class FeatherTips extends JavaPlugin {

    private ConfigManager configManager;
    private MessagesManager messagesManager;
    private TopicsManager topicManager;
    private LoginTipsManager loginTipsManager;

    @Override
    public void onEnable() {

        this.configManager = new ConfigManager(this);
        this.messagesManager = new MessagesManager(this);
        this.topicManager = new TopicsManager(this);
        this.loginTipsManager = new LoginTipsManager(this);


        getServer().getPluginManager().registerEvents(new PlayerJoinListener(this), this);


        getServer().getScheduler().scheduleSyncRepeatingTask(this, new AutoBroadcastTask(this), getConfig().getInt("period"), getConfig().getInt("period"));


        this.getCommand("tip").setExecutor(new TipCommand(this));
        this.getCommand("tip").setTabCompleter(new TipTabCompleter(this));

        this.getCommand("tips").setExecutor(new TipsCommand(this));
        this.getCommand("tips").setTabCompleter(new TipsTabCompleter());

        this.getCommand("broadcast").setExecutor((new BroadcastCommand(this)));
        this.getCommand("broadcast").setTabCompleter(new BroadcastTabCompleter(this));

        this.getCommand("servertime").setExecutor(new ServerTimeCommand(this));
        this.getCommand("servertime").setTabCompleter(new ServerTimeTabCompleter());

        this.getCommand("announce").setExecutor(new AnnounceCommand(this));
        this.getCommand("announce").setTabCompleter(new AnnounceTabCompleter());

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

