package org.example.Spotify;

import javafx.application.Application;
import javafx.beans.binding.Bindings;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import org.example.Spotify.Database;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import org.example.Spotify.User;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class SpotifyApp extends Application {
    private Database db;
    private User currentUser;
    private MediaPlayer mediaPlayer;

    @Override
    public void start(Stage primaryStage) {
        db = new Database();

        primaryStage.setTitle("سه پاتی فای");
        primaryStage.setScene(createLoginScene(primaryStage));
        primaryStage.show();
    }

    private Scene createHomeScene(Stage primaryStage) {
        VBox homeLayout = new VBox();
        homeLayout.setPadding(new Insets(20, 20, 20, 20));
        homeLayout.setSpacing(20);
        homeLayout.getStyleClass().add("home-container");

        HBox topMenu = new HBox();
        topMenu.setAlignment(Pos.CENTER_RIGHT);
        topMenu.setSpacing(10);

        Button searchButton = new Button("Search");
        searchButton.getStyleClass().add("button");
        searchButton.setOnAction(e -> showSearchDialog(primaryStage));

        Button userButton = new Button("User");
        userButton.getStyleClass().add("button");
        userButton.setOnAction(e -> primaryStage.setScene(createUserProfileScene(primaryStage)));

        Button logoutButton = new Button("Log Out");
        logoutButton.getStyleClass().add("button");
        logoutButton.setOnAction(e -> primaryStage.setScene(createLoginScene(primaryStage)));

        topMenu.getChildren().addAll(searchButton, userButton, logoutButton);

        Label homeLabel = new Label("Home");
        homeLabel.getStyleClass().add("section-label");
        Label artistsLabel = new Label("Artists");
        artistsLabel.getStyleClass().add("section-label");

        HBox artistsBox = new HBox(20);
        artistsBox.setAlignment(Pos.CENTER_LEFT);
        artistsBox.getStyleClass().add("artist-box");
        artistsBox.getChildren().addAll(
                createArtistCard("The Weeknd", "src/main/resources/org.example.demo2/example/demo2/TheWeeknd.jpg"),
                createArtistCard("Kendrick Lamar", "src/main/resources/org.example.demo2/example/demo2/kdot.jpg"),
                createArtistCard("Eminem", "src/main/resources/org.example.demo2/example/demo2/Eminem.jpg")
        );

        // Genres Section
        Label genresLabel = new Label("Genres");
        genresLabel.getStyleClass().add("section-label");

        HBox genresBox = new HBox(20);
        genresBox.setAlignment(Pos.CENTER_LEFT);
        genresBox.getStyleClass().add("genre-box");
        genresBox.getChildren().addAll(
                createGenreCard("Pop", "src/main/resources/org.example.demo2/example/demo2/pop.jpg"),
                createGenreCard("Rock", "src/main/resources/org.example.demo2/example/demo2/rock.jpg"),
                createGenreCard("Hip-Hop", "src/main/resources/org.example.demo2/example/demo2/hiphop.jpg")
        );
        Label playlistsLabel = new Label("Playlists");
        playlistsLabel.getStyleClass().add("section-label");

        HBox playlistsBox = new HBox(20);
        playlistsBox.setAlignment(Pos.CENTER_LEFT);
        playlistsBox.getStyleClass().add("playlist-box");
        playlistsBox.getChildren().addAll(
                createPlaylistCard("Workout Mix", "src/main/resources/org.example.demo2/example/demo2/playlist1.jpg"),
                createPlaylistCard("Chill Vibes", "src/main/resources/org.example.demo2/example/demo2/playlist2.jpg"),
                createPlaylistCard("Top Hits", "src/main/resources/org.example.demo2/example/demo2/playlist3.jpg")
        );
        Label recommendedLabel = new Label("Recommended Songs");
        recommendedLabel.getStyleClass().add("section-label");

        HBox recommendedBox = new HBox(20);
        recommendedBox.setAlignment(Pos.CENTER_LEFT);
        recommendedBox.getStyleClass().add("recommended-box");
        recommendedBox.getChildren().addAll(
                createSongItem("Not Like Us", "src/main/resources/org.example.demo2/example/demo2/NotLikeUs.jpg"),
                createSongItem("Blinding Lights", "src/main/resources/org.example.demo2/example/demo2/BlindingLights.jpg"),
                createSongItem("Houdini", "src/main/resources/org.example.demo2/example/demo2/Houdini.jpg")
        );

        homeLayout.getChildren().addAll(
                topMenu,
                homeLabel,
                artistsLabel, artistsBox,
                genresLabel, genresBox,
                playlistsLabel, playlistsBox,
                recommendedLabel, recommendedBox
        );

        Scene homeScene = new Scene(homeLayout, 800, 600);
        File cssFile = new File("src/main/resources/org.example.demo2/example/demo2/style.css");
        homeScene.getStylesheets().add(cssFile.toURI().toString());

        return homeScene;
    }
    private VBox createArtistCard(String artistName, String imagePath) {
        VBox artistCard = new VBox();
        artistCard.setAlignment(Pos.CENTER);
        artistCard.setSpacing(10);
        artistCard.getStyleClass().add("artist-card");

        ImageView artistImage = new ImageView(new Image(new File(imagePath).toURI().toString()));
        artistImage.getStyleClass().add("artist-image");

        Label artistLabel = new Label(artistName);
        artistLabel.getStyleClass().add("label");

        artistCard.getChildren().addAll(artistImage, artistLabel);
        artistCard.setOnMouseClicked(e -> showArtistDetails(artistName));

        return artistCard;
    }

    private VBox createGenreCard(String genreName, String imagePath) {
        VBox genreCard = new VBox();
        genreCard.setAlignment(Pos.CENTER);
        genreCard.setSpacing(10);
        genreCard.getStyleClass().add("genre-card");

        ImageView genreImage = new ImageView(new Image(new File(imagePath).toURI().toString()));
        genreImage.getStyleClass().add("genre-image");

        Label genreLabel = new Label(genreName);
        genreLabel.getStyleClass().add("label");

        genreCard.getChildren().addAll(genreImage, genreLabel);
        genreCard.setOnMouseClicked(e -> showGenreDetails(genreName));

        return genreCard;
    }

    private VBox createPlaylistCard(String playlistName, String imagePath) {
        VBox playlistCard = new VBox();
        playlistCard.setAlignment(Pos.CENTER);
        playlistCard.setSpacing(10);
        playlistCard.getStyleClass().add("playlist-card");

        ImageView playlistImage = new ImageView(new Image(new File(imagePath).toURI().toString()));
        playlistImage.getStyleClass().add("playlist-image");

        Label playlistLabel = new Label(playlistName);
        playlistLabel.getStyleClass().add("label");

        playlistCard.getChildren().addAll(playlistImage, playlistLabel);

        return playlistCard;
    }

    private VBox createSongItem(String songName, String imagePath) {
        VBox songCard = new VBox();
        songCard.setAlignment(Pos.CENTER);
        songCard.setSpacing(10);
        songCard.getStyleClass().add("song-card");

        ImageView songImage = new ImageView(new Image(new File(imagePath).toURI().toString()));
        songImage.getStyleClass().add("song-image");

        Label songLabel = new Label(songName);
        songLabel.getStyleClass().add("label");

        Button playButton = new Button("Play");
        playButton.getStyleClass().add("play-button");
        playButton.setOnAction(e -> playSong(songName));

        Button addToPlaylistButton = new Button("Add to Playlist");
        addToPlaylistButton.getStyleClass().add("playlist-button");
        addToPlaylistButton.setOnAction(e -> addToPlaylist(songName));

        songCard.getChildren().addAll(songImage, songLabel, playButton, addToPlaylistButton);
        songCard.setOnMouseClicked(e -> showSongDetails(songName));

        return songCard;
    }

    private void showSearchDialog(Stage primaryStage) {
        TextInputDialog dialog = new TextInputDialog();
        dialog.setTitle("Search");
        dialog.setHeaderText("Search for a genre, artist, or song");
        dialog.setContentText("Enter name:");

        Optional<String> result = dialog.showAndWait();
        result.ifPresent(name -> handleSearch(primaryStage, name));
    }

    private void handleSearch(Stage primaryStage, String query) {
        if (query.equalsIgnoreCase("Pop") || query.equalsIgnoreCase("Rock") || query.equalsIgnoreCase("Hip-Hop")) {
            showGenreDetails(query);
        } else if (query.equalsIgnoreCase("The Weeknd") || query.equalsIgnoreCase("Kendrick Lamar") || query.equalsIgnoreCase("Eminem")) {
            showArtistDetails(query);
        } else if (query.equalsIgnoreCase("Houdini") || query.equalsIgnoreCase("Not Like Us") || query.equalsIgnoreCase("Blinding Lights")) {
            showSongDetails(query);
        } else {
            showAlert(Alert.AlertType.ERROR, "Search Failed", "No results found for: " + query);
        }
    }

    private Scene createUserProfileScene(Stage primaryStage) {
        VBox layout = new VBox();
        layout.setPadding(new Insets(20, 20, 20, 20));
        layout.setSpacing(20);
        layout.setAlignment(Pos.CENTER);

        Label profileLabel = new Label("Edit Profile");
        profileLabel.getStyleClass().add("section-label");

        Label emailLabel = new Label("Email:");
        emailLabel.getStyleClass().add("label");
        TextField emailInput = new TextField(currentUser.getEmail());
        emailInput.getStyleClass().add("text-field");

        Label genderLabel = new Label("Gender:");
        genderLabel.getStyleClass().add("label");
        TextField genderInput = new TextField(currentUser.getGender());
        genderInput.getStyleClass().add("text-field");

        Label birthDateLabel = new Label("Birth Date:");
        birthDateLabel.getStyleClass().add("label");
        TextField birthDateInput = new TextField(currentUser.getBirthDate());
        birthDateInput.getStyleClass().add("text-field");

        Button saveButton = new Button("Save");
        saveButton.getStyleClass().add("button");
        saveButton.setOnAction(e -> {
            currentUser.setEmail(emailInput.getText());
            currentUser.setGender(genderInput.getText());
            currentUser.setBirthDate(birthDateInput.getText());
            showAlert(Alert.AlertType.INFORMATION, "Profile Updated", "Your profile has been updated.");
            primaryStage.setScene(createHomeScene(primaryStage));
        });

        Button viewPlaylistButton = new Button("View Playlist");
        viewPlaylistButton.getStyleClass().add("button");
        viewPlaylistButton.setOnAction(e -> showUserPlaylist(primaryStage));

        layout.getChildren().addAll(profileLabel, emailLabel, emailInput, genderLabel, genderInput, birthDateLabel, birthDateInput, saveButton, viewPlaylistButton);

        Scene scene = new Scene(layout, 400, 400);
        File cssFile = new File("src/main/resources/org.example.demo2/example/demo2/style.css");
        scene.getStylesheets().add(cssFile.toURI().toString());

        return scene;
    }

    private void showUserPlaylist(Stage primaryStage) {
        Stage stage = new Stage();
        stage.setTitle("User Playlist");

        VBox layout = new VBox();
        layout.setPadding(new Insets(20, 20, 20, 20));
        layout.setSpacing(20);
        layout.setAlignment(Pos.CENTER);

        Label playlistLabel = new Label("Your Playlist");
        playlistLabel.getStyleClass().add("section-label");

        ListView<String> playlist = new ListView<>();
        playlist.getItems().addAll(currentUser.getPlaylist());

        layout.getChildren().addAll(playlistLabel, playlist);

        Scene scene = new Scene(layout, 400, 300);
        File cssFile = new File("src/main/resources/org.example.demo2/example/demo2/style.css");
        scene.getStylesheets().add(cssFile.toURI().toString());

        stage.setScene(scene);
        stage.show();
    }

    private Scene createLoginScene(Stage primaryStage) {
        VBox loginLayout = new VBox();
        loginLayout.setAlignment(Pos.CENTER);
        loginLayout.setSpacing(20);
        loginLayout.getStyleClass().add("container");

        VBox background = new VBox(loginLayout);
        background.getStyleClass().add("background");

        Label logoLabel = new Label("سه پاتی فای");
        logoLabel.getStyleClass().add("logo");

        Button googleButton = createSocialButton("Continue with Google", "src/main/resources/org.example.demo2/example/demo2/google-icon.png");
        Button facebookButton = createSocialButton("Continue with Facebook", "src/main/resources/org.example.demo2/example/demo2/facebook-icon.png");
        Button appleButton = createSocialButton("Continue with Apple", "src/main/resources/org.example.demo2/example/demo2/apple-icon.png");

        Label emailLabel = new Label("Email or username");
        emailLabel.getStyleClass().add("label");
        TextField emailInput = new TextField();
        emailInput.getStyleClass().add("text-field");
        emailInput.setPromptText("Email or username");

        Label passwordLabel = new Label("Password");
        passwordLabel.getStyleClass().add("label");
        PasswordField passwordInput = new PasswordField();
        passwordInput.getStyleClass().add("password-field");
        passwordInput.setPromptText("Password");

        HBox rememberMeBox = new HBox();
        rememberMeBox.setAlignment(Pos.CENTER_LEFT);
        rememberMeBox.setSpacing(10);
        CheckBox rememberMeCheck = new CheckBox("Remember me");
        rememberMeCheck.getStyleClass().add("label");
        rememberMeBox.getChildren().addAll(rememberMeCheck);

        Button loginButton = new Button("Log In");
        loginButton.getStyleClass().add("button");
        loginButton.setOnAction(e -> handleLogin(primaryStage, emailInput.getText(), passwordInput.getText()));

        Button signupButton = new Button("Sign Up");
        signupButton.getStyleClass().add("button");
        signupButton.setOnAction(e -> primaryStage.setScene(createSignupScene(primaryStage)));

        loginLayout.getChildren().addAll(
                logoLabel,
                googleButton, facebookButton, appleButton,
                emailLabel, emailInput,
                passwordLabel, passwordInput,
                rememberMeBox,
                loginButton,
                signupButton
        );

        Scene loginScene = new Scene(background, 500, 700);
        File cssFile = new File("src/main/resources/org.example.demo2/example/demo2/style.css");
        loginScene.getStylesheets().add(cssFile.toURI().toString());

        return loginScene;
    }

    private Scene createSignupScene(Stage primaryStage) {
        VBox signupLayout = new VBox();
        signupLayout.setAlignment(Pos.CENTER);
        signupLayout.setSpacing(20);
        signupLayout.getStyleClass().add("container");

        VBox background = new VBox(signupLayout);
        background.getStyleClass().add("background");

        Label logoLabel = new Label("سه پاتی فای");
        logoLabel.getStyleClass().add("logo");

        Label emailLabel = new Label("What's your email?");
        emailLabel.getStyleClass().add("label");
        TextField emailInput = new TextField();
        emailInput.getStyleClass().add("text-field");
        emailInput.setPromptText("Enter your email");

        Label confirmEmailLabel = new Label("Confirm your email");
        confirmEmailLabel.getStyleClass().add("label");
        TextField confirmEmailInput = new TextField();
        confirmEmailInput.getStyleClass().add("text-field");
        confirmEmailInput.setPromptText("Enter your email again");

        Label passwordLabel = new Label("Create a password");
        passwordLabel.getStyleClass().add("label");
        PasswordField passwordInput = new PasswordField();
        passwordInput.getStyleClass().add("password-field");
        passwordInput.setPromptText("Create a password");

        Label usernameLabel = new Label("What should we call you?");
        usernameLabel.getStyleClass().add("label");
        TextField usernameInput = new TextField();
        usernameInput.getStyleClass().add("text-field");
        usernameInput.setPromptText("Enter a profile name");

        Label birthDateLabel = new Label("What's your date of birth?");
        birthDateLabel.getStyleClass().add("label");

        TextField dayInput = createTextField("DD", 2);
        TextField monthInput = createTextField("MM", 2);
        TextField yearInput = createTextField("YYYY", 4);

        HBox birthDateBox = new HBox(10);
        birthDateBox.setAlignment(Pos.CENTER);
        birthDateBox.getChildren().addAll(
                new Label("Day"), dayInput,
                new Label("Month"), monthInput,
                new Label("Year"), yearInput
        );

        Label genderLabel = new Label("What's your gender?");
        genderLabel.getStyleClass().add("label");

        ToggleGroup genderGroup = new ToggleGroup();
        RadioButton maleRadio = new RadioButton("Male");
        maleRadio.setToggleGroup(genderGroup);
        maleRadio.getStyleClass().add("label");
        RadioButton femaleRadio = new RadioButton("Female");
        femaleRadio.setToggleGroup(genderGroup);
        femaleRadio.getStyleClass().add("label");
        RadioButton nonBinaryRadio = new RadioButton("Non-binary");
        nonBinaryRadio.setToggleGroup(genderGroup);
        nonBinaryRadio.getStyleClass().add("label");

        HBox genderBox = new HBox(10, maleRadio, femaleRadio, nonBinaryRadio);
        genderBox.setAlignment(Pos.CENTER);

        Button registerButton = new Button("Sign Up");
        registerButton.getStyleClass().add("button");
        registerButton.setOnAction(e -> handleSignup(
                primaryStage,
                usernameInput.getText(),
                passwordInput.getText(),
                emailInput.getText(),
                confirmEmailInput.getText(),
                dayInput.getText() + "/" + monthInput.getText() + "/" + yearInput.getText(),
                ((RadioButton) genderGroup.getSelectedToggle()).getText()
        ));

        signupLayout.getChildren().addAll(
                logoLabel,
                emailLabel, emailInput,
                confirmEmailLabel, confirmEmailInput,
                passwordLabel, passwordInput,
                usernameLabel, usernameInput,
                birthDateLabel, birthDateBox,
                genderLabel, genderBox,
                registerButton
        );

        Scene signupScene = new Scene(background, 500, 700);
        File cssFile = new File("src/main/resources/org.example.demo2/example/demo2/style.css");
        signupScene.getStylesheets().add(cssFile.toURI().toString());

        return signupScene;
    }

    private TextField createTextField(String promptText, int maxLength) {
        TextField textField = new TextField();
        textField.setPromptText(promptText);
        textField.getStyleClass().add("text-field");
        textField.setPrefColumnCount(maxLength);
        return textField;
    }

    private Button createSocialButton(String text, String iconPath) {
        HBox socialButton = new HBox();
        socialButton.setAlignment(Pos.CENTER_LEFT);
        socialButton.setSpacing(10);
        socialButton.getStyleClass().add("social-button");

        ImageView icon = new ImageView(new Image(new File(iconPath).toURI().toString()));
        icon.setFitHeight(20);
        icon.setFitWidth(20);
        icon.getStyleClass().add("social-icon");

        Label label = new Label(text);
        label.getStyleClass().add("label");

        socialButton.getChildren().addAll(icon, label);

        return new Button("", socialButton);
    }

    private void handleLogin(Stage primaryStage, String username, String password) {
        User user = db.getUser(username, password);
        if (user != null) {
            currentUser = user;
            primaryStage.setScene(createHomeScene(primaryStage));
        } else {
            showAlert(Alert.AlertType.ERROR, "Login Failed", "Invalid username or password");
        }
    }

    private void handleSignup(Stage primaryStage, String username, String password, String email, String confirmEmail, String birthDate, String gender) {
        if (!email.equals(confirmEmail)) {
            showAlert(Alert.AlertType.ERROR, "Sign Up Failed", "Emails do not match");
            return;
        }

        User user = new User(username, password, email, "", "", birthDate, gender);
        db.addUser(user);
        showAlert(Alert.AlertType.INFORMATION, "Sign Up Successful", "User registered successfully");
        primaryStage.setScene(createHomeScene(primaryStage));
    }

    private void addToPlaylist(String songName) {
        currentUser.getPlaylist().add(songName);
        showAlert(Alert.AlertType.INFORMATION, "Add to Playlist", songName + " has been added to your playlist.");
    }

    private void playSong(String songName) {

        if(mediaPlayer != null){
            mediaPlayer.stop();
        }
        String musicPath = "src/main/resources/org.example.demo2/example/demo2/notLikeUs.mp3";
        Media sound = new Media(new File(musicPath).toURI().toString());
        mediaPlayer = new MediaPlayer(sound);
        mediaPlayer.play();
        showAlert(Alert.AlertType.INFORMATION, "playing song", "Playing");
    }

    private void showArtistDetails(String artistName) {
        Stage stage = new Stage();
        stage.setTitle(artistName + " Details");

        VBox layout = new VBox();
        layout.setPadding(new Insets(20, 20, 20, 20));
        layout.setSpacing(20);
        layout.setAlignment(Pos.CENTER);
        Label artistLabel = new Label(artistName);
        artistLabel.getStyleClass().add("section-label");

        Label artistBio = new Label("Bio: " + artistName + " is a famous artist known for their unique style in the music industry.");
        artistBio.getStyleClass().add("artist-bio");

        Label monthlyListeners = new Label("Monthly Listeners: 1,234,567");
        monthlyListeners.getStyleClass().add("artist-listeners");
        Label songsLabel = new Label("Songs by " + artistName);
        songsLabel.getStyleClass().add("section-label");

        ListView<VBox> songsList = new ListView<>();
        songsList.getItems().addAll(
                createSongItem("Song 1", "src/main/resources/org/example/demo2/song1.jpg"),
                createSongItem("Song 2", "src/main/resources/org/example/demo2/song2.jpg"),
                createSongItem("Song 3", "src/main/resources/org/example/demo2/song3.jpg")
        );

        layout.getChildren().addAll(artistLabel, artistBio, monthlyListeners, songsLabel, songsList);

        Scene scene = new Scene(layout, 500, 600);
        File cssFile = new File("src/main/resources/org.example.demo2/example/demo2/style.css");
        scene.getStylesheets().add(cssFile.toURI().toString());

        stage.setScene(scene);
        stage.show();
    }

    private HBox createGenreArtistItem(String artistName) {
        HBox artistItem = new HBox();
        artistItem.setAlignment(Pos.CENTER_LEFT);
        artistItem.setSpacing(10);
        artistItem.getStyleClass().add("artist-item");

        Label artistLabel = new Label(artistName);
        artistLabel.getStyleClass().add("label");

        artistItem.getChildren().addAll(artistLabel);
        artistItem.setOnMouseClicked(e -> showArtistDetails(artistName));

        return artistItem;
    }

    private void showGenreDetails(String genreName) {
        Stage stage = new Stage();
        stage.setTitle(genreName + " Artists");

        VBox layout = new VBox();
        layout.setPadding(new Insets(20, 20, 20, 20));
        layout.setSpacing(20);
        layout.setAlignment(Pos.CENTER);

        Label genreLabel = new Label("Artists in " + genreName);
        genreLabel.getStyleClass().add("section-label");

        ListView<HBox> artistsList = new ListView<>();
        artistsList.getItems().addAll(
                createGenreArtistItem("Artist 1"),
                createGenreArtistItem("Artist 2"),
                createGenreArtistItem("Artist 3")
        );

        layout.getChildren().addAll(genreLabel, artistsList);

        Scene scene = new Scene(layout, 400, 300);
        File cssFile = new File("src/main/resources/org.example.demo2/example/demo2/style.css");
        scene.getStylesheets().add(cssFile.toURI().toString());

        stage.setScene(scene);
        stage.show();
    }

    private void showSongDetails(String songName) {
        Stage stage = new Stage();
        stage.setTitle(songName + " Details");

        VBox layout = new VBox();
        layout.setPadding(new Insets(20, 20, 20, 20));
        layout.setSpacing(20);
        layout.setAlignment(Pos.CENTER);

        // Song details
        Label songLabel = new Label(songName);
        songLabel.getStyleClass().add("section-label");

        Label releaseYear = new Label("Release Year: 2022");
        releaseYear.getStyleClass().add("song-detail");

        Label album = new Label("Album: گاد پوری");
        album.getStyleClass().add("song-detail");

        Label playCount = new Label("Play Count: 1,234,567");
        playCount.getStyleClass().add("song-detail");

        Label lyrics = new Label("خیلی مخلصیم");
        lyrics.getStyleClass().add("song-detail");

        Button playButton = new Button("Play");
        playButton.getStyleClass().add("play-button");
        playButton.setOnAction(e -> playSong(songName));

        layout.getChildren().addAll(songLabel, releaseYear, album, playCount, lyrics, playButton);

        Scene scene = new Scene(layout, 500, 400);
        File cssFile = new File("src/main/resources/org.example.demo2/example/demo2/style.css");
        scene.getStylesheets().add(cssFile.toURI().toString());

        stage.setScene(scene);
        stage.show();
    }

    private void showAlert(Alert.AlertType alertType, String title, String message) {
        Alert alert = new Alert(alertType);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }

    public static void main(String[] args) {
        launch(args);
    }
} 