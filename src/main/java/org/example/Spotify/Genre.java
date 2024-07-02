package org.example.Spotify;

import java.util.ArrayList;
import java.util.List;

public class Genre {
    private String name;
    private List<Artist> artists;

    public Genre(String name) {
        this.name = name;
        this.artists = new ArrayList<>();
    }

    public void addArtist(Artist artist) {
        this.artists.add(artist);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Artist> getArtists() {
        return artists;
    }

    public void setArtists(List<Artist> artists) {
        this.artists = artists;
    }
}
