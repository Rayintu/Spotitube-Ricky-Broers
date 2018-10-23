package nl.han.dea.ricky.service;

import nl.han.dea.ricky.entity.Playlist;
import nl.han.dea.ricky.entity.Track;
import nl.han.dea.ricky.exception.LoginException;
import nl.han.dea.ricky.persistence.IPlaylistDAO;
import nl.han.dea.ricky.response.PlaylistResponse;
import nl.han.dea.ricky.response.TracksResponse;

import javax.inject.Inject;
import java.util.List;

public class PlaylistService implements IPlaylistService {

    @Inject
    IPlaylistDAO IPlaylistDAO;

    public PlaylistService() {

    }

    @Override
    public PlaylistResponse getOwnedPlaylists(String token) {
        return new PlaylistResponse(getAllPlaylists(token));
    }

    @Override
    public PlaylistResponse editPlaylistName(String newPlaylistName, String token, int id) throws LoginException {
        IPlaylistDAO.editPlaylistName(newPlaylistName, token, id);
        return new PlaylistResponse(getAllPlaylists(token));
    }

    @Override
    public PlaylistResponse addNewPlaylist(Playlist playlist, String token) {
        IPlaylistDAO.createNewPlaylist(playlist, token);
        return new PlaylistResponse(getAllPlaylists(token));
    }

    @Override
    public PlaylistResponse deletePlaylist(int id, String token) {
        IPlaylistDAO.deletePlaylist(id, token);
        return new PlaylistResponse(getAllPlaylists(token));
    }

    @Override
    public TracksResponse deleteTrackFromPlaylist(int playlistId, int trackId, String token) {
        IPlaylistDAO.deleteTrackFromPlaylist(playlistId, trackId, token);
        return new TracksResponse(IPlaylistDAO.getAllTracksInPlaylist(playlistId, token));
    }

    @Override
    public TracksResponse getAllTracksInPlaylist(int id, String token) {
        return new TracksResponse(IPlaylistDAO.getAllTracksInPlaylist(id, token));
    }

    @Override
    public TracksResponse addTrackToPlaylist(int id, String token, Track track) {
        IPlaylistDAO.addTrackToPlaylist(id, track);
        return new TracksResponse(IPlaylistDAO.getAllTracksInPlaylist(id, token));
    }

    @Override
    public int getTotalLength(List<String> playlistNames) {
        return IPlaylistDAO.getTotalLengthOfAllOwnedPlaylistsCombined(playlistNames);
    }

    private List<Playlist> getAllPlaylists(String token) {
        return IPlaylistDAO.getAllPlaylistsOnToken(token);
    }


}
