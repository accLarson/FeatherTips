package com.zerek.feathertips.managers;

import com.zerek.feathertips.FeatherTips;

import java.util.ArrayList;
import java.util.List;

public class ConfigManager {

    private final FeatherTips plugin;

    private int broadcastDelayPeriod;

    private final List<String> assTips = new ArrayList<>();

    private final List<String> modTips = new ArrayList<>();

    private String timeZone;


    public ConfigManager(FeatherTips plugin) {

        this.plugin = plugin;

        this.init();
    }

    private void init() {

        this.broadcastDelayPeriod = plugin.getConfig().getInt("period");

        assTips.addAll(plugin.getConfig().getStringList("AssTips"));

        modTips.addAll(plugin.getConfig().getStringList("ModTips"));

        timeZone = plugin.getConfig().getString("TimeZone");


    }

    public int getBroadcastDelayPeriod() {
        return broadcastDelayPeriod;
    }

    public List<String> getAssTips() {
        return assTips;
    }

    public List<String> getModTips() {
        return modTips;
    }

    public String getTimeZone() {
        return timeZone;
    }
}
