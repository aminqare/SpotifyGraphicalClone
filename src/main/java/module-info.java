module org.example.Spotify {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;
    requires javafx.media;


    opens org.example.Spotify to javafx.fxml;
    exports org.example.Spotify;
}