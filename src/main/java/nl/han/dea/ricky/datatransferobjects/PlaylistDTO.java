package nl.han.dea.ricky.datatransferobjects;

import nl.han.dea.ricky.entity.Playlist;

import java.util.List;

public class PlaylistDTO {

    List<Playlist> playlists;
    int length;

    public PlaylistDTO(List<Playlist> playlists, int length) {
        this.length = length;
        this.playlists = playlists;
    }

    public List<Playlist> getPlaylists() {
        return playlists;
    }

    public void setPlaylists(List<Playlist> playlists) {
        this.playlists = playlists;
    }

    public int getLength() {
        return length;
    }

    public void setLength(int length) {
        this.length = length;
    }
}
