package org.example.Spotify;

import java.util.ArrayList;

public class User {
    private String username;
    private String password;
    private String email;
    private String firstName;
    private String lastName;
    private String birthDate;
    private String gender;
    private ArrayList<String> playlist = new ArrayList<>();

    public User(String username, String password, String email, String firstName, String lastName, String birthDate, String gender) {
        this.username = username;
        this.password = password;
        this.email = email;
        this.firstName = firstName;
        this.lastName = lastName;
        this.birthDate = birthDate;
        this.gender = gender;
        playlist = new ArrayList<>();
    }

    public ArrayList<String> getPlaylist() {
        return playlist;
    }

    public void setPlaylist(ArrayList<String> playlist) {
        this.playlist = playlist;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setBirthDate(String birthDate) {
        this.birthDate = birthDate;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public String getEmail() {
        return email;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getBirthDate() {
        return birthDate;
    }

    public String getGender() {
        return gender;
    }
}
