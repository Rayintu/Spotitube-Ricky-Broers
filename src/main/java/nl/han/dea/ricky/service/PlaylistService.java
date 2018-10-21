package nl.han.dea.ricky.service;

import nl.han.dea.ricky.entity.Playlist;
import nl.han.dea.ricky.exception.LoginException;
import nl.han.dea.ricky.persistence.PlaylistDAO;
import nl.han.dea.ricky.response.PlaylistResponse;

import java.util.List;

public class PlaylistService {

    private PlaylistDAO playlistDAO = new PlaylistDAO();

    public PlaylistService() {

    }

    public PlaylistResponse getOwnedPlaylists(String token) {
        return new PlaylistResponse(getAllPlaylists(token));
    }

    public PlaylistResponse editPlaylistName(String newPlaylistName, String token, int id) throws LoginException {
        playlistDAO.editPlaylistName(newPlaylistName, token, id);
        return new PlaylistResponse(getAllPlaylists(token));
    }

    public PlaylistResponse addNewPlaylist(Playlist playlist, String token) {
        playlistDAO.createNewPlaylist(playlist, token);
        return new PlaylistResponse(getAllPlaylists(token));
    }

    private List<Playlist> getAllPlaylists(String token) {
        return playlistDAO.getAllPlaylistsOnToken(token);
    }
}
