package nl.han.dea.ricky.service;

import nl.han.dea.ricky.datatransferobjects.PlaylistDTO;
import nl.han.dea.ricky.datatransferobjects.TracksDTO;
import nl.han.dea.ricky.entity.Playlist;
import nl.han.dea.ricky.entity.Track;
import nl.han.dea.ricky.exception.LoginException;
import nl.han.dea.ricky.persistence.IPlaylistDAO;

import javax.enterprise.inject.Default;
import javax.inject.Inject;
import java.util.List;

@Default
public class PlaylistService implements IPlaylistService {

    @Inject
    private IPlaylistDAO playlistDAO;

//    playlistDAO playlistDAO = new PlaylistDAO();


    public PlaylistService() {
    }

    @Override
    public PlaylistDTO getOwnedPlaylists(String token) {
        return new PlaylistDTO(this.getAllPlaylists(token), this.getTotalLength(this.getAllPlaylists(token)));
    }

    @Override
    public PlaylistDTO editPlaylistName(String newPlaylistName, String token, int id) throws LoginException {
        playlistDAO.editPlaylistName(newPlaylistName, token, id);
        return new PlaylistDTO(this.getAllPlaylists(token), this.getTotalLength(this.getAllPlaylists(token)));
    }

    @Override
    public PlaylistDTO addNewPlaylist(Playlist playlist, String token) {
        playlistDAO.createNewPlaylist(playlist, token);
        return new PlaylistDTO(this.getAllPlaylists(token), this.getTotalLength(this.getAllPlaylists(token)));
    }

    @Override
    public PlaylistDTO deletePlaylist(int id, String token) {
        playlistDAO.deletePlaylist(id, token);
        return new PlaylistDTO(this.getAllPlaylists(token), this.getTotalLength(this.getAllPlaylists(token)));
    }

    @Override
    public TracksDTO deleteTrackFromPlaylist(int playlistId, int trackId, String token) {
        playlistDAO.deleteTrackFromPlaylist(playlistId, trackId, token);
        return new TracksDTO(playlistDAO.getAllTracksInPlaylist(playlistId, token));
    }

    @Override
    public TracksDTO getAllTracksInPlaylist(int id, String token) {
        return new TracksDTO(playlistDAO.getAllTracksInPlaylist(id, token));
    }

    @Override
    public TracksDTO addTrackToPlaylist(int id, String token, Track track) {
        playlistDAO.addTrackToPlaylist(id, track);
        return new TracksDTO(playlistDAO.getAllTracksInPlaylist(id, token));
    }

    @Override
    public int getTotalLength(List<Playlist> playlistNames) {
        return playlistDAO.getTotalLengthOfAllOwnedPlaylistsCombined(playlistNames);
    }

    private List<Playlist> getAllPlaylists(String token) {
        return playlistDAO.getAllPlaylistsOnToken(token);
    }
}
