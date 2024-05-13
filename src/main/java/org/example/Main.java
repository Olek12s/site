package org.example;
import database.DatabaseConnection;
import database.Song;

public class Main {
    public static void main(String[] args)
    {
        DatabaseConnection.connect("songs");

        Song song = new Song(50, "Krawczyk", "Parostatek", 1000);

        DatabaseConnection.insertTrack(48, "Zenek", "Spadająca gwiazda", 256);

        DatabaseConnection.disconnect();
    }
}