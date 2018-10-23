package nl.han.dea.ricky.service;

import nl.han.dea.ricky.persistence.TrackDAO;
import nl.han.dea.ricky.response.TracksResponse;

public class TrackService implements ITrackService {


    TrackDAO trackDAO = new TrackDAO();

    @Override
    public TracksResponse getTracks(int playlistID) {
        return new TracksResponse(trackDAO.getTracks(playlistID));

    }
}
