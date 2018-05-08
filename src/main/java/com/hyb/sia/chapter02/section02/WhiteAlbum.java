package com.hyb.sia.chapter02.section02;

public class WhiteAlbum implements CompactDisc {
    private String title = "white album";
    private String artist = "some white guy";

    @Override
    public void play() {
        System.out.println("Playing " + title + " by " + artist);
    }
}
