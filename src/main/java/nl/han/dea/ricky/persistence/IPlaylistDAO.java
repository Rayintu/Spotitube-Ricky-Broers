package nl.han.dea.ricky.persistence;

import nl.han.dea.ricky.entity.Playlist;
import nl.han.dea.ricky.entity.Track;
import nl.han.dea.ricky.exception.LoginException;

import java.util.List;

public interface IPlaylistDAO {
    List<Playlist> getAllPlaylistsOnToken(String token);

    void editPlaylistName(String newPlaylistName, String token, int id) throws LoginException;

    void createNewPlaylist(Playlist playlist, String token);

    void deletePlaylist(int id, String token);

    List<Track> getAllTracksInPlaylist(int id, String token);

    int getTotalLengthOfAllOwnedPlaylistsCombined(List<Playlist> playlistNames);

    void addTrackToPlaylist(int id, Track track);

    void deleteTrackFromPlaylist(int playlistId, int trackId, String token);
}
