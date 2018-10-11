package nl.han.dea.ricky.response;

import nl.han.dea.ricky.Playlist;

import java.util.List;

public class AllPlaylistViewResponse {

    List<Playlist> playlists;
    int length;

    public AllPlaylistViewResponse(List<Playlist> playlists) {

        this.playlists = playlists;
        this.length = 5000;
//        for (Playlist pList : playlist) {
//            setLength(pList.getPlaylistLength());
//        }

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