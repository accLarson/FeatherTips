package dev.zerek.feathertips.data;

public class Topic {

    private final String shortFormatRaw;
    private final String longFormatRaw;

    // constructor
    public Topic(String shortFormatRaw, String longFormatRaw) {
        this.shortFormatRaw = shortFormatRaw;
        this.longFormatRaw = longFormatRaw;
    }

    public String getShortFormatRaw(){
        return shortFormatRaw;
    }

    public String getLongFormatRaw(){
        return longFormatRaw;
    }
}
