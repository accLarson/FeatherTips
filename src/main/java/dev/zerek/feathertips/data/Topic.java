package dev.zerek.feathertips.data;

import net.kyori.adventure.text.TextComponent;

public class Topic {

    private final TextComponent shortFormat;
    private final TextComponent longFormat;
    private final String name;


    //constructor
    public Topic(TextComponent shortFormat, TextComponent longFormat, String topic) {
        this.shortFormat = shortFormat;
        this.longFormat = longFormat;
        this.name = topic;
    }

    public TextComponent getShortFormat(){
        return shortFormat;
    }

    public TextComponent getLongFormat(){
        return longFormat;
    }

    public String getName() {
        return name;
    }
}