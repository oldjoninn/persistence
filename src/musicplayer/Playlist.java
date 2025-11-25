package musicplayer;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Playlist implements Serializable {
    // Playlist information
    private static final long serialVersionUID = 1L; // For serialization
 
    private String name;
    private List<Song> songs;

    public Playlist(String name) { // Constructor
        this.name = name;
        this.songs = new ArrayList<>();
    }

    // Getters and setters
    public String getName() { return name; }
    public List<Song> getSongs() { return songs; }

    // Add a song to the playlist
    public void addSong(Song s) { 
        if (s != null) songs.add(s); 
    }
}
