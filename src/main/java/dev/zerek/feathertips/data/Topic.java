package dev.zerek.feathertips.data;

import net.kyori.adventure.text.TextComponent;

public class Topic {

    private final TextComponent shortFormat;
    private final TextComponent longFormat;


    //constructor
    public Topic(TextComponent shortFormat, TextComponent longFormat) {
        this.shortFormat = shortFormat;
        this.longFormat = longFormat;
    }

    public TextComponent getShortFormat(){
        return shortFormat;
    }

    public TextComponent getLongFormat(){
        return longFormat;
    }
}