package musicplayer;

import java.io.*;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import storage.AlbumReader;

public class MusicPlayer implements Serializable {
    // Music player information
    private static final long serialVersionUID = 1L; // For serialization

    private String name;
    private List<Album> albums;
    private List<Playlist> playlists;

    public MusicPlayer(String name) { // Constructor
        this.name = name;
        this.albums = new ArrayList<>();
        this.playlists = new ArrayList<>();
    }

    // methods used by Run1
    public void addAlbum(Path p) {
        try {
            Album a = AlbumReader.readAlbum(p);
            if (a != null) albums.add(a);
        } catch (Exception e) {
            System.out.println("Error reading album file: " + p + " -> " + e.getMessage());
        }
    }
    
    // display methods
    public void viewAllSongs() {
        System.out.println("~||==================================================================================||~");
        System.out.println("All songs currently in player " + name + ":");
        for (Album a : albums) {
            for (Song s : a.getSongs()) {
                System.out.println(s.displayLine());
            }
        }
        System.out.println("~||==================================================================================||~");
    }

    public void viewAllAlbums() {
        System.out.println("~||==================================================================================||~");
        System.out.println("All albums currently in player " + name + ":");
        for (int i = 0; i < albums.size(); i++) {
            System.out.println((i+1) + " " + albums.get(i).getName() );
        }
        System.out.println("~||==================================================================================||~");
    }

    public void findAlbum(String albumName) {
        System.out.println("~||==================================================================================||~");
        Album found = null;
        for (Album a : albums) {
            if (a.getName().equals(albumName)) { found = a; break; }
        }
        if (found == null) {
            System.out.println("Album " + albumName + " does not exist.");
            System.out.println("~||==================================================================================||~");
            return;
        }

        // print album header and track list
        System.out.println(found.headerLine());
        for (Song s : found.getSongs()) {
            System.out.println(s.displayTrackLine());
        }
        System.out.println("~||==================================================================================||~");
    }

    public void viewAllSongsByArtist(String artist) {
        System.out.println("~||==================================================================================||~");
        System.out.println("Songs by " + artist + " :");
        System.out.println();
        for (Album a : albums) {
            for (Song s : a.getSongs()) {
                if (s.getArtist().equals(artist) || s.getArtist().contains(artist)) {
                    System.out.println(" " + String.format("%-33s", s.getTitle()) + " - " + String.format("%-25s", s.getArtist()) + " | " + s.getDuration());
                }
            }
        }
        System.out.println("~||==================================================================================||~");
    }
    // playlist methods
    public void addPlaylist(String name) { // add new empty playlist
        playlists.add(new Playlist(name));
    }

    public Song getSongFromAlbum(String albumName, String songTitle) { // find song in album
        for (Album a : albums) {
            if (a.getName().equals(albumName)) {
                return a.findSongByTitle(songTitle);
            }
        }
        return null;
    }

    public void addSongToPlaylist(String playlistName, Song s) {   // add song to playlist
        if (s == null) { // song does not exist
            System.out.println("Song does not exist.");
            return;
        }
        Playlist p = null;
        for (Playlist pl : playlists) if (pl.getName().equals(playlistName)) { p = pl; break; } // find playlist by name
        if (p == null) return; 
        p.addSong(s);
    }

    public void viewAllPlaylists() { // display all playlists
        System.out.println("~||==================================================================================||~");
        System.out.println("All playlists currently in player " + name + ":");
        for (int i = 0; i < playlists.size(); i++) {
            System.out.println((i+1) + " " + playlists.get(i).getName());
        }
        System.out.println("~||==================================================================================||~");
    }

    public void findPlaylist(String playlistName) { // display songs in playlist
        System.out.println("Playlist name: " + playlistName);
        System.out.println("Tracks:");
        Playlist pl = null; // find playlist by name
        for (Playlist p : playlists) if (p.getName().equals(playlistName)) { pl = p; break; }
        if (pl == null) return;
        List<Song> s = pl.getSongs();
        for (int i = 0; i < s.size(); i++) {
            System.out.println((i+1) + "  | " + s.get(i).displayLine().trim());
        }
        System.out.println("~||==================================================================================||~");
    }

    // persistence used by Run1.save and Run2.load
    public void save(Path path) { // save player to file
        try {
            System.out.println("Saving.....");
            ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(path.toFile())); // overwrite existing file
            oos.writeObject(this); // write the whole player object
            oos.close(); // close stream
            System.out.println("Saving complete.");
        } catch (Exception e) {
            System.out.println("Error saving player: " + e.getMessage());
        }
    }

    public static MusicPlayer load(Path path) { // load player from file
        try {
            System.out.println("Loading.....");
            ObjectInputStream ois = new ObjectInputStream(new FileInputStream(path.toFile())); // open file
            Object obj = ois.readObject(); // read object
            ois.close(); // close stream
            System.out.println("Loading complete.");
            if (obj instanceof MusicPlayer) return (MusicPlayer) obj; 
        } catch (Exception e) {
            System.out.println("Error loading player: " + e.getMessage());
        }
        // if anything goes wrong return a new empty player with default name
        return new MusicPlayer("barfoo");
    }

    // helper getters used by Run1
    public List<Album> getAlbums() { return albums; }
}
