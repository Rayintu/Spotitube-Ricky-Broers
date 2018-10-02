package nl.han.dea.ricky;

import java.util.ArrayList;
import java.util.List;


public class Playlist {
    private String id;
    private String name;
    private String owner;
    private List<Song> tracks = new ArrayList<Song>();

    public Playlist(String id, String name, String owner, List<Song> songs) {
        this.id = id;
        this.name = name;
        this.owner = owner;
        this.tracks = songs;
    }

    public Playlist() {

    }

    public List<Song> addSongToPlaylist(Song song) {
        tracks.add(song);
        return tracks;
    }

    public List<Song> getTracks() {
        return tracks;
    }

    public void setTracks(List<Song> tracks) {
        this.tracks = tracks;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getOwner() {
        return owner;
    }

    public void setOwner(String owner) {
        this.owner = owner;
    }
}
