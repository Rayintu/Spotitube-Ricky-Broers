package nl.han.dea.ricky.service;

import nl.han.dea.ricky.datatransferobjects.TracksDTO;

public interface ITrackService {
    TracksDTO getTracks(int playlistID);
}
