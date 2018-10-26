package nl.han.dea.ricky.datatransferobjects;

import nl.han.dea.ricky.entity.Track;

import java.util.ArrayList;
import java.util.List;

public class TracksDTO {

    List<Track> tracks = new ArrayList<Track>();

    public TracksDTO() {
    }

    public TracksDTO(List<Track> tracks) {
        this.tracks = tracks;
    }

    public List<Track> getTracks() {
        return tracks;
    }
}
