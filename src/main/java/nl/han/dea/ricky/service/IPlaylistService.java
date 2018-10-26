package nl.han.dea.ricky.service;

import nl.han.dea.ricky.datatransferobjects.PlaylistDTO;
import nl.han.dea.ricky.datatransferobjects.TracksDTO;
import nl.han.dea.ricky.entity.Playlist;
import nl.han.dea.ricky.entity.Track;
import nl.han.dea.ricky.exception.LoginException;

import java.util.List;

public interface IPlaylistService {
    PlaylistDTO getOwnedPlaylists(String token);

    PlaylistDTO editPlaylistName(String newPlaylistName, String token, int id) throws LoginException;

    PlaylistDTO addNewPlaylist(Playlist playlist, String token);

    PlaylistDTO deletePlaylist(int id, String token);

    TracksDTO deleteTrackFromPlaylist(int playlistId, int trackId, String token);

    TracksDTO getAllTracksInPlaylist(int id, String token);

    TracksDTO addTrackToPlaylist(int id, String token, Track track);

    int getTotalLength(List<Playlist> playlistNames);
}
