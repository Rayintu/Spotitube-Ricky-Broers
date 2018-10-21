package nl.han.dea.ricky.service;

import nl.han.dea.ricky.exception.LoginException;
import nl.han.dea.ricky.persistence.PlaylistDAO;
import nl.han.dea.ricky.response.PlaylistResponse;

public class PlaylistService {

    PlaylistDAO playlistDAO = new PlaylistDAO();

    public PlaylistService() {

    }

    public PlaylistResponse getOwnedPlaylists(String token) {
        return new PlaylistResponse(playlistDAO.getAllPlaylistsOnToken(token));
    }

    public PlaylistResponse editPlaylistName(String newPlaylistName, String token, int id) throws LoginException {
        playlistDAO.editPlaylistName(newPlaylistName, token, id);
        return new PlaylistResponse(playlistDAO.getAllPlaylistsOnToken(token));
    }
}
