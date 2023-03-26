package com.zerek.feathertips.managers;

import com.zerek.feathertips.FeatherTips;


public class ConfigManager {

    private final FeatherTips plugin;

    private int broadcastDelayPeriod;

    private String timeZone;


    public ConfigManager(FeatherTips plugin) {

        this.plugin = plugin;

        this.init();
    }

    private void init() {

        this.broadcastDelayPeriod = plugin.getConfig().getInt("period");

        this.timeZone = plugin.getConfig().getString("TimeZone");


    }

    public int getBroadcastDelayPeriod() {
        return broadcastDelayPeriod;
    }

    public String getTimeZone() {
        return timeZone;
    }
}
