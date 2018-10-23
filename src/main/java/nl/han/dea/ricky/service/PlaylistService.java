package nl.han.dea.ricky.service;

import nl.han.dea.ricky.entity.Playlist;
import nl.han.dea.ricky.entity.Track;
import nl.han.dea.ricky.exception.LoginException;
import nl.han.dea.ricky.persistence.PlaylistDAO;
import nl.han.dea.ricky.response.PlaylistResponse;
import nl.han.dea.ricky.response.TracksResponse;

import java.util.List;

public class PlaylistService implements IPlaylistService {

    private PlaylistDAO playlistDAO = new PlaylistDAO();

    public PlaylistService() {

    }

    @Override
    public PlaylistResponse getOwnedPlaylists(String token) {
        return new PlaylistResponse(getAllPlaylists(token));
    }

    @Override
    public PlaylistResponse editPlaylistName(String newPlaylistName, String token, int id) throws LoginException {
        playlistDAO.editPlaylistName(newPlaylistName, token, id);
        return new PlaylistResponse(getAllPlaylists(token));
    }

    @Override
    public PlaylistResponse addNewPlaylist(Playlist playlist, String token) {
        playlistDAO.createNewPlaylist(playlist, token);
        return new PlaylistResponse(getAllPlaylists(token));
    }

    @Override
    public PlaylistResponse deletePlaylist(int id, String token) {
        playlistDAO.deletePlaylist(id, token);
        return new PlaylistResponse(getAllPlaylists(token));
    }

    @Override
    public TracksResponse deleteTrackFromPlaylist(int playlistId, int trackId, String token) {
        playlistDAO.deleteTrackFromPlaylist(playlistId, trackId, token);
        return new TracksResponse(playlistDAO.getAllTracksInPlaylist(playlistId, token));
    }

    @Override
    public TracksResponse getAllTracksInPlaylist(int id, String token) {
        return new TracksResponse(playlistDAO.getAllTracksInPlaylist(id, token));
    }

    @Override
    public TracksResponse addTrackToPlaylist(int id, String token, Track track) {
        playlistDAO.addTrackToPlaylist(id, track);
        return new TracksResponse(playlistDAO.getAllTracksInPlaylist(id, token));
    }

    @Override
    public int getTotalLength(List<String> playlistNames) {
        return playlistDAO.getTotalLengthOfAllOwnedPlaylistsCombined(playlistNames);
    }

    private List<Playlist> getAllPlaylists(String token) {
        return playlistDAO.getAllPlaylistsOnToken(token);
    }


}
