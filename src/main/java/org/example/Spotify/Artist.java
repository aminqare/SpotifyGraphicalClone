package org.example.Spotify;

import java.util.ArrayList;
import java.util.List;

public class Artist {
    private String name;
    private String bio;
    private int monthlyListeners;
    private List<Songs> songs;

    public Artist(String name, String bio, int monthlyListeners) {
        this.name = name;
        this.bio = bio;
        this.monthlyListeners = monthlyListeners;
        this.songs = new ArrayList<>();
    }

    public void addSong(Songs song) {
        this.songs.add(song);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getBio() {
        return bio;
    }

    public void setBio(String bio) {
        this.bio = bio;
    }

    public int getMonthlyListeners() {
        return monthlyListeners;
    }

    public void setMonthlyListeners(int monthlyListeners) {
        this.monthlyListeners = monthlyListeners;
    }

    public List<Songs> getSongs() {
        return songs;
    }

    public void setSongs(List<Songs> songs) {
        this.songs = songs;
    }

}
