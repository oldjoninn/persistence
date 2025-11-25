package storage;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.nio.file.Path;
import musicplayer.Album;
import musicplayer.Song;

public class AlbumReader {
    // Read an album from a file
    public static Album readAlbum(Path path) throws Exception { // throws Exception on error
        File file = path.toFile(); // convert Path to File
        if (!file.exists()) throw new Exception("Album file not found: " + path.toString()); // check file existence
 
        BufferedReader br = new BufferedReader(new FileReader(file)); // open file for reading
        String header = br.readLine(); // read the first line
        if (header == null || header.trim().isEmpty()) { // empty file
            br.close(); // close reader
            throw new Exception("Empty album file: " + path);
        }

        // Parse  first line AlbumName, Artist, Year, TotalDuration
        String[] headerParts = header.split(",");
        if (headerParts.length < 3) {
            br.close(); // close reader
            throw new Exception("Invalid header format in " + path);
        }

        String albumName = headerParts[0].trim(); // Album name
        String artist = headerParts[1].trim();
        int year = 0;
        try {
            year = Integer.parseInt(headerParts[2].trim());
        } catch (NumberFormatException e) {
            year = 0;
        }

        Album album = new Album(albumName, artist, year);

        // Read songs (format: track#,title,duration,artist)
        String line; 
        while ((line = br.readLine()) != null) { 
            if (line.trim().isEmpty()) continue;
            String[] parts = line.split(",");
            if (parts.length < 4) continue;
            // Parse song details
            int trackNumber = 0;
            try { trackNumber = Integer.parseInt(parts[0].trim()); } catch (NumberFormatException e) {}
            String title = parts[1].trim();
            String duration = parts[2].trim();
            String songArtist = parts[3].trim();

            album.addSong(new Song(trackNumber, title, songArtist, duration)); // add song to album
        }

        br.close();
        return album;
    }
}
