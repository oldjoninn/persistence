package musicplayer;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Album implements Serializable {
    // Album information
    private static final long serialVersionUID = 1L; // For serialization

    private String name;
    private String artist;
    private int year;
    private List<Song> songs;

    public Album(String name, String artist, int year) { // Constructor
        this.name = name;
        this.artist = artist;
        this.year = year;
        this.songs = new ArrayList<>();
    }

    // Getters and setters
    public String getName() { return name; }
    public String getArtist() { return artist; }
    public int getYear() { return year; }
    public List<Song> getSongs() { return songs; }

    // Add a song to the album
    public void addSong(Song s) { songs.add(s); }

    // Find a song by its title
    public Song findSongByTitle(String title) {
        for (Song s : songs) {
            if (s.getTitle().equals(title)) return s;
        }
        return null;
    }
    // Generate a header line for display
    public String headerLine() {
        return name + " - " + artist + " (" + year + ")";
    }
}
