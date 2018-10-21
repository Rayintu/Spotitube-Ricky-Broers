package nl.han.dea.ricky.persistence;

import nl.han.dea.ricky.entity.Playlist;
import nl.han.dea.ricky.entity.Track;
import nl.han.dea.ricky.exception.LoginException;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class PlaylistDAO {
    private ConnectionFactory connectionFactory;

    public PlaylistDAO() {
        connectionFactory = new ConnectionFactory();
    }

    public List<Playlist> getAllPlaylistsOnToken(String token) {
        List<Playlist> playlists = new ArrayList<Playlist>();

        try (
                Connection connection = connectionFactory.getConnection();
                PreparedStatement statement = connection.prepareStatement("SELECT playlists.playlist_id, playlists.name, playlists.tracks, t.user AS ownerusername, t.token AS usertoken " +
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
            e.printStackTrace();
        }
        return playlists;
    }

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
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (LoginException e) {
            throw new RuntimeException(e);
        }

    }
}
