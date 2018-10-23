package nl.han.dea.ricky.service;

import nl.han.dea.ricky.entity.Playlist;
import nl.han.dea.ricky.entity.Track;
import nl.han.dea.ricky.exception.LoginException;
import nl.han.dea.ricky.response.PlaylistResponse;
import nl.han.dea.ricky.response.TracksResponse;

import java.util.List;

public interface IPlaylistService {
    PlaylistResponse getOwnedPlaylists(String token);

    PlaylistResponse editPlaylistName(String newPlaylistName, String token, int id) throws LoginException;

    PlaylistResponse addNewPlaylist(Playlist playlist, String token);

    PlaylistResponse deletePlaylist(int id, String token);

    TracksResponse deleteTrackFromPlaylist(int playlistId, int trackId, String token);

    TracksResponse getAllTracksInPlaylist(int id, String token);

    TracksResponse addTrackToPlaylist(int id, String token, Track track);

    int getTotalLength(List<String> playlistNames);
}
