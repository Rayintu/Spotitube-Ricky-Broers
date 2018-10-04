package nl.han.dea.ricky.Response;

import nl.han.dea.ricky.Playlist;

import java.util.List;

public class AllPlaylistViewResponse {

    List<Playlist> playlist;
    int length;

    public AllPlaylistViewResponse(List<Playlist> playlist) {

        this.playlist = playlist;
//        this.length = 5000;
        for (Playlist pList : playlist) {
            setLength(pList.getPlaylistLength());
        }

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
