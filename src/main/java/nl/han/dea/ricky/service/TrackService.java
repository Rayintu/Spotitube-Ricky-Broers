package nl.han.dea.ricky.service;

import nl.han.dea.ricky.persistence.ITrackDAO;
import nl.han.dea.ricky.response.TracksResponse;

import javax.inject.Inject;

public class TrackService implements ITrackService {

    @Inject
    ITrackDAO ITrackDAO;

    @Override
    public TracksResponse getTracks(int playlistID) {
        return new TracksResponse(ITrackDAO.getTracks(playlistID));

    }
}
