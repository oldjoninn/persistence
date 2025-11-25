package main;

import java.nio.file.Path;
import java.nio.file.Paths;

import musicplayer.MusicPlayer;

public class Run2 {

	public static void main(String[] args) {
		//load path for loading the saved player object
		Path loadpath = Paths.get("src/storage/save.txt");
		
		MusicPlayer player = MusicPlayer.load(loadpath);
		
		//check player contents
		player.viewAllSongs();
		player.viewAllAlbums();
		player.viewAllPlaylists();

	}

}
