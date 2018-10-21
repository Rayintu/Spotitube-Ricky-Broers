package nl.han.dea.ricky.service;

import nl.han.dea.ricky.persistence.TrackDAO;
import nl.han.dea.ricky.response.TracksResponse;

public class TrackService {


    TrackDAO trackDAO = new TrackDAO();

    public TracksResponse getTracks() {
        return new TracksResponse(trackDAO.getTracks());

    }
}
