package org.example.Spotify;

public class Songs {
    private String title;
    private String artist;
    private String genre;
    private String album;
    private int releaseYear;
    private String lyrics;
    private int playCount;

    public Songs(String title, String artist, String genre, String album, int releaseYear, String lyrics, int playCount) {
        this.title = title;
        this.artist = artist;
        this.genre = genre;
        this.album = album;
        this.releaseYear = releaseYear;
        this.lyrics = lyrics;
        this.playCount = playCount;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getArtist() {
        return artist;
    }

    public void setArtist(String artist) {
        this.artist = artist;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public String getAlbum() {
        return album;
    }

    public void setAlbum(String album) {
        this.album = album;
    }

    public int getReleaseYear() {
        return releaseYear;
    }

    public void setReleaseYear(int releaseYear) {
        this.releaseYear = releaseYear;
    }

    public String getLyrics() {
        return lyrics;
    }

    public void setLyrics(String lyrics) {
        this.lyrics = lyrics;
    }

    public int getPlayCount() {
        return playCount;
    }

    public void setPlayCount(int playCount) {
        this.playCount = playCount;
    }
}
