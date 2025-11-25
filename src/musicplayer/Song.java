package musicplayer;

import java.io.Serializable;

public class Song implements Serializable {
    // Song information
    private static final long serialVersionUID = 1L; // For serialization

    private int trackNumber;
    private String title;
    private String artist;
    private String duration;

    public Song(int trackNumber, String title, String artist, String duration) { // Constructor
        this.trackNumber = trackNumber;
        this.title = title;
        this.artist = artist;
        this.duration = duration;
    }

    // Getters
    public int getTrackNumber() { return trackNumber; }
    public String getTitle() { return title; }
    public String getArtist() { return artist; }
    public String getDuration() { return duration; }

    // Display methods
    public String displayLine() { // simple display line
        // padding for alignment
        String titlePad = String.format("%-33s", title);
        String artistPad = String.format("%-25s", artist);
        return " " + titlePad + " - " + artistPad + " | " + duration;
    }

    
    public String displayTrackLine() { // display line with track number
        // padding for alignment
        String titlePad = String.format("%-33s", title);
        String artistPad = String.format("%-25s", artist);
        return String.format("%2d  |%-33s - %-25s | %s", trackNumber, title, artist, duration);
    }
}
