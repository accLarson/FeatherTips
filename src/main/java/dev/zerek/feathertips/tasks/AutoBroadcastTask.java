package dev.zerek.feathertips.tasks;

import dev.zerek.feathertips.FeatherTips;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class AutoBroadcastTask implements Runnable{

    private final FeatherTips plugin;

    private List<String> availableTopicList;

    private final Random random = new Random();

    public AutoBroadcastTask(FeatherTips plugin) {

        this.plugin = plugin;

        availableTopicList = new ArrayList<>(plugin.getTopicManager().getTopicsMapKeys());

    }

    @Override
    public void run() {

        if (availableTopicList.size() == 0) availableTopicList = new ArrayList<>(plugin.getTopicManager().getTopicsMapKeys());

        int randomInt = random.nextInt(availableTopicList.size());

        plugin.getTopicManager().broadcast(availableTopicList.get(randomInt));

        availableTopicList.remove(randomInt);
    }
}
