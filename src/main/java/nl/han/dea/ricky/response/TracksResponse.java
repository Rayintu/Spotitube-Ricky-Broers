package nl.han.dea.ricky.response;

import nl.han.dea.ricky.entity.Track;

import java.util.ArrayList;
import java.util.List;

public class TracksResponse {

    List<Track> tracks = new ArrayList<Track>();

    public TracksResponse() {
    }

    public TracksResponse(List<Track> tracks) {
        this.tracks = tracks;
    }

    public List<Track> getTracks() {
        return tracks;
    }
}
