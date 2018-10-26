package nl.han.dea.ricky.persistence;

import nl.han.dea.ricky.entity.Playlist;
import nl.han.dea.ricky.entity.Track;
import nl.han.dea.ricky.exception.LoginException;

import javax.enterprise.inject.Default;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Default
public class PlaylistDAO implements IPlaylistDAO {
    private ConnectionFactory connectionFactory;

    public PlaylistDAO() {
        connectionFactory = new ConnectionFactory();
    }

    @Override
    public List<Playlist> getAllPlaylistsOnToken(String token) {
        List<Playlist> playlists = new ArrayList<Playlist>();

        try (
                Connection connection = connectionFactory.getConnection();
                PreparedStatement statement = connection.prepareStatement("SELECT playlists.playlist_id, playlists.name, t.user AS ownerusername, t.token AS usertoken " +
                        "FROM playlists JOIN tokens t on playlists.owner = t.user WHERE t.token = ?")
        ) {
            statement.setString(1, token);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                playlists.add(
                        new Playlist(
                                resultSet.getInt("playlist_id"),
                                resultSet.getString("name"),
                                true,
                                new Track[]{}
                        )
                );
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return playlists;
    }

    @Override
    public void editPlaylistName(String newPlaylistName, String token, int id) throws LoginException {
        if (checkIfTokenBelongsToOwner(token, id)) {
            try (
                    Connection connection = connectionFactory.getConnection();
                    PreparedStatement statement = connection.prepareStatement("UPDATE playlists SET name = ? WHERE playlist_id = ?")
            ) {
                statement.setString(1, newPlaylistName);
                statement.setInt(2, id);
                statement.execute();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        } else {
            throw new LoginException("you're not authorized to edit this playlist");
        }
    }

    private boolean checkIfTokenBelongsToOwner(String token, int id) {
        try (
                Connection connection = connectionFactory.getConnection();
                PreparedStatement statement = connection.prepareStatement("SELECT t.token FROM playlists JOIN tokens t ON playlists.owner = t.user WHERE playlists.playlist_id = ?")
        ) {
            statement.setInt(1, id);
            ResultSet resultset = statement.executeQuery();
            if (resultset.next()) {
                if (resultset.getString("token").equals(token)) {
                    return true;
                } else {
                    throw new LoginException("you're not authorized to edit this playlist");
                }
            } else {
                return false;
            }
        } catch (SQLException | LoginException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void createNewPlaylist(Playlist playlist, String token) {
        String owner = " ";
        try (
                Connection connection = connectionFactory.getConnection();
                PreparedStatement getPlaylistOwnerStatement = connection.prepareStatement("SELECT * FROM tokens WHERE token = ?");
                PreparedStatement badButNecessaryStatement1 = connection.prepareStatement("SET FOREIGN_KEY_CHECKS = 0");
                PreparedStatement createPlaylistStatement = connection.prepareStatement("INSERT INTO playlists (name, owner) VALUES (?,?)");
                PreparedStatement badButNecessaryStatement2 = connection.prepareStatement("SET FOREIGN_KEY_CHECKS = 1")
        ) {

            getPlaylistOwnerStatement.setString(1, token);

            ResultSet resultSetOwner = getPlaylistOwnerStatement.executeQuery();
            if (resultSetOwner.next()) {
                owner = resultSetOwner.getString("user");
            }

            createPlaylistStatement.setString(1, playlist.getName());
            createPlaylistStatement.setString(2, owner);
            badButNecessaryStatement1.execute();
            createPlaylistStatement.execute();
            badButNecessaryStatement2.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void deletePlaylist(int id, String token) {
        try (
                Connection connection = connectionFactory.getConnection();
                PreparedStatement statement = connection.prepareStatement("DELETE p FROM playlists p JOIN tokens t ON p.owner = t.user WHERE playlist_id = ? AND t.token = ?");
        ) {
            statement.setInt(1, id);
            statement.setString(2, token);
            statement.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<Track> getAllTracksInPlaylist(int id, String token) {
        List<Track> tracks = new ArrayList<Track>();
        try (
                Connection connection = connectionFactory.getConnection();
                PreparedStatement statement = connection.prepareStatement("SELECT tr.*, t.token, p.* FROM playlists p JOIN tokens t on p.owner = t.user JOIN playlist_track_connector ptc on p.playlist_id = ptc.playlist_id JOIN tracks tr on ptc.track_id = tr.track_id WHERE ptc.playlist_id = ?");
        ) {
            statement.setInt(1, id);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                tracks.add(
                        new Track(
                                resultSet.getInt("track_id"),
                                resultSet.getString("title"),
                                resultSet.getString("performer"),
                                resultSet.getInt("duration"),
                                resultSet.getString("album"),
                                resultSet.getInt("playcount"),
                                resultSet.getDate("publicationDate").toString(),
                                resultSet.getString("description"),
                                false
                        )
                );
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return tracks;
    }

    @Override
    public int getTotalLengthOfAllOwnedPlaylistsCombined(List<Playlist> playlistNames) {
        int length = 0;

        try (
                Connection connection = connectionFactory.getConnection();
                PreparedStatement statement = connection.prepareStatement("SELECT tr.duration FROM playlists p JOIN playlist_track_connector ptc on p.playlist_id = ptc.playlist_id JOIN tracks tr on ptc.track_id = tr.track_id WHERE p.name = ?;");
        ) {
            for (Playlist playlist : playlistNames) {
                statement.setString(1, playlist.getName());
                ResultSet resultSet = statement.executeQuery();
                while (resultSet.next()) {
                    length += resultSet.getInt("duration");
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return length;
    }

    @Override
    public void addTrackToPlaylist(int id, Track track) {
        int trackID = track.getId();

        try (
                Connection connection = connectionFactory.getConnection();
                PreparedStatement badButNecessaryStatement1 = connection.prepareStatement("SET FOREIGN_KEY_CHECKS = 0");
                PreparedStatement addSongStatement = connection.prepareStatement("INSERT INTO playlist_track_connector (playlist_id, track_id) VALUES (?,?)");
                PreparedStatement badButNecessaryStatement2 = connection.prepareStatement("SET FOREIGN_KEY_CHECKS = 1")
        ) {

            addSongStatement.setInt(1, id);
            addSongStatement.setInt(2, trackID);
            addSongStatement.execute();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void deleteTrackFromPlaylist(int playlistId, int trackId, String token) {
        try (
                Connection connection = connectionFactory.getConnection();
                PreparedStatement statement = connection.prepareStatement("DELETE FROM playlist_track_connector WHERE playlist_id = ? AND track_id = ?;");
        ) {
            statement.setInt(1, playlistId);
            statement.setInt(2, trackId);
            statement.execute();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
