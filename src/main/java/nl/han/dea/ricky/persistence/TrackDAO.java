package nl.han.dea.ricky.persistence;

import nl.han.dea.ricky.entity.Track;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class TrackDAO implements ITrackDAO {
    private ConnectionFactory connectionFactory;

    public TrackDAO() {
        connectionFactory = new ConnectionFactory();
    }

    @Override
    public List<Track> getTracks(int playlistID) {
        List<Track> tracks = new ArrayList<Track>();

        try (
                Connection connection = connectionFactory.getConnection();
                PreparedStatement statement = connection.prepareStatement("SELECT tr.*, ptc.playlist_id FROM tracks tr JOIN playlist_track_connector ptc on tr.track_id = ptc.track_id WHERE playlist_id <> ?;")
        ) {
            statement.setInt(1, playlistID);
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
}
