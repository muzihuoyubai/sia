package com.hyb.sia.chapter02.section02;

public class HardDaysNight implements CompactDisc {
    private String title = "HardDaysNight";
    private String artist = "some hard guy";

    @Override
    public void play() {
        System.out.println("Playing " + title + " by " + artist);
    }
}
