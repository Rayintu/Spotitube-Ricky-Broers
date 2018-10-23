package nl.han.dea.ricky.persistence;

import nl.han.dea.ricky.entity.Track;

import java.util.List;

public interface ITrackDAO {
    List<Track> getTracks(int playlistID);
}
