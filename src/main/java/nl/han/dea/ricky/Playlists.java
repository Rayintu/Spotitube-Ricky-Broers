package nl.han.dea.ricky;

import java.util.List;

public class Playlists {
    List<Playlist> playlist;
    int length;

    public Playlists(List<Playlist> playlist, int length) {
        this.length = length;
        this.playlist = playlist;

    }

    public List<Playlist> getPlaylist() {
        return playlist;
    }

    public void setPlaylist(List<Playlist> playlist) {
        this.playlist = playlist;
    }

    public int getLength() {
        return length;
    }

    public void setLength(int length) {
        this.length = length;
    }
}
