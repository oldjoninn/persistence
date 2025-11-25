package main;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;

import musicplayer.MusicPlayer;

public class Run1 {
	public static void main(String[] args) {
		//load all paths into an arraylist for easy looping
		ArrayList<Path> albums = new ArrayList<Path>(Arrays.asList(Paths.get("src/storage/LastDance.txt"), Paths.get("src/storage/Metaphor.txt"), Paths.get("src/storage/MintJams.txt"), Paths.get("src/storage/ModalSoul.txt"), Paths.get("src/storage/NITO.txt")));
		
		Path savepath = Paths.get("src/storage/save.txt");
		//create player
		MusicPlayer player = new MusicPlayer("barfoo");
		//read text files and load them to player
		for (Path path: albums) {
			player.addAlbum(path);
		}
		
		//show player data
		
		player.viewAllSongs();
		player.viewAllAlbums();
		//show an album and an album that does not exist
		player.findAlbum("Last Dance");
		player.findAlbum("Glitched Universe");
		
		//show all songs by an artist
		player.viewAllSongsByArtist("Ichika Nito");
		
		
		//create a new playlist
		player.addPlaylist("Test list");
		
		//load songs to a playlist
		player.addSongToPlaylist("Test list", player.getSongFromAlbum("N I T O", "Orb"));
		player.addSongToPlaylist("Test list", player.getSongFromAlbum("Metaphor", "Metaphor"));
		player.addSongToPlaylist("Test list", player.getSongFromAlbum("N I T O", "Ethereal Feel"));
		player.addSongToPlaylist("Test list", player.getSongFromAlbum("Last Dance", "Reprologue")); //should fail, the song is RePrologue
		player.addSongToPlaylist("Test list", player.getSongFromAlbum("Last Dance", "RePrologue"));
		
		//view all playlists
		player.viewAllPlaylists();
		player.findPlaylist("Test list");
		
		//save to obj files
		player.save(savepath);
	}
}
