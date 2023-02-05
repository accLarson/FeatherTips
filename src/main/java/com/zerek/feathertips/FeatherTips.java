package com.zerek.feathertips;

import com.zerek.feathertips.commands.*;
import com.zerek.feathertips.listeners.PlayerJoinListener;
import com.zerek.feathertips.managers.TopicManager;
import com.zerek.feathertips.tasks.AutoBroadcastTask;
import org.bukkit.command.CommandSender;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.ArrayList;

public final class FeatherTips extends JavaPlugin {

    private TopicManager topicManager;

    private final ArrayList<String> modTips = new ArrayList<String>();

    private final ArrayList<String> assTips = new ArrayList<String>();


    @Override
    public void onEnable() {

        this.saveDefaultConfig();

        this.topicManager = new TopicManager(this);

        getServer().getPluginManager().registerEvents(new PlayerJoinListener(this), this);

        this.assTips.addAll(this.getConfig().getStringList("AssTips"));

        this.modTips.addAll(this.getConfig().getStringList("ModTips"));

        getServer().getScheduler().scheduleSyncRepeatingTask(this, new AutoBroadcastTask(this), getConfig().getInt("period"), getConfig().getInt("period"));

        this.getCommand("tip").setExecutor(new TipCommand(this));

        this.getCommand("tip").setTabCompleter(new TipTabCompleter(this));

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

        this.topicManager = new TopicManager(this);

        this.assTips.clear();

        this.assTips.addAll(this.getConfig().getStringList("AssTips"));

        this.modTips.clear();

        this.modTips.addAll(this.getConfig().getStringList("ModTips"));

        getServer().getScheduler().scheduleSyncRepeatingTask(this, new AutoBroadcastTask(this), getConfig().getInt("period"), getConfig().getInt("period"));

        sender.sendMessage("FeatherTips reloaded");
    }

    public TopicManager getTopicManager() {
        return topicManager;
    }

    public ArrayList<String> getModTips() {
        return modTips;
    }

    public ArrayList<String> getAssTips() {
        return assTips;
    }

}

