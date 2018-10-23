package nl.han.dea.ricky.service;

import nl.han.dea.ricky.response.TracksResponse;

public interface ITrackService {
    TracksResponse getTracks(int playlistID);
}
