package nl.han.dea.ricky.response;

import nl.han.dea.ricky.entity.Playlist;
import nl.han.dea.ricky.service.PlaylistService;

import java.util.ArrayList;
import java.util.List;

public class PlaylistResponse {

    PlaylistService playlistService = new PlaylistService();

    List<String> playlistNames = new ArrayList<String>();

    List<Playlist> playlists;
    int length;

    public PlaylistResponse(List<Playlist> playlists) {
        for (Playlist playlist : playlists) {
            playlistNames.add(playlist.getName());
        }

        this.playlists = playlists;
        this.length = playlistService.getTotalLength(playlistNames);
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
