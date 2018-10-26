package nl.han.dea.ricky.service;

import nl.han.dea.ricky.datatransferobjects.TracksDTO;
import nl.han.dea.ricky.persistence.ITrackDAO;

import javax.inject.Inject;

public class TrackService implements ITrackService {

    @Inject
    ITrackDAO trackDAO;

    @Override
    public TracksDTO getTracks(int playlistID) {
        return new TracksDTO(trackDAO.getTracks(playlistID));

    }
}
