package com.zerek.feathertips.tasks;

import com.zerek.feathertips.FeatherTips;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class AutoBroadcastTask implements Runnable{

    private final FeatherTips plugin;
    private List<String> availableTopicList;

    public AutoBroadcastTask(FeatherTips plugin) {
        this.plugin = plugin;
        availableTopicList = new ArrayList<>(plugin.getTopicManager().getTopics());
    }

    public static Integer getRandomInt(Integer max) {
        Random ran = new Random();
        return ran.nextInt(max);
    }

    @Override
    public void run() {
        if (availableTopicList.size() == 0) availableTopicList = new ArrayList<>(plugin.getTopicManager().getTopics());
        else {
            int randomInt = getRandomInt(availableTopicList.size());
            plugin.getTopicManager().broadcast(availableTopicList.get(randomInt));
            availableTopicList.remove(randomInt);
        }
    }
}
