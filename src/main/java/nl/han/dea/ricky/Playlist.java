package nl.han.dea.ricky;

import java.util.ArrayList;
import java.util.List;


public class Playlist {
    private String id;
    private String name;
    private String owner;

    private List<Track> tracks = new ArrayList<Track>();

    public Playlist(String id, String name, String owner, List<Track> tracks) {
        this.id = id;
        this.name = name;
        this.owner = owner;
        this.tracks = tracks;
    }

    public int getPlaylistLength() {
        int length = 0;
        for (Track track : tracks) {
            length += track.getLength();
        }
        return length;
    }

    public Playlist() {

    }

    public List<Track> addSongToPlaylist(Track track) {
        tracks.add(track);
        return tracks;
    }

    public List<Track> getTracks() {
        return tracks;
    }

    public void setTracks(List<Track> tracks) {
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
