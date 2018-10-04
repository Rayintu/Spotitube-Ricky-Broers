package nl.han.dea.ricky;

import java.util.ArrayList;
import java.util.List;

public class TempPlaylistCreator {
    private List<Playlist> returnList = new ArrayList<Playlist>();

    public TempPlaylistCreator() {

    }

    public List<Playlist> createPlaylists() {
        List<Track> songs1 = new ArrayList<Track>();
        List<Track> songs2 = new ArrayList<Track>();
        List<Track> songs3 = new ArrayList<Track>();


        songs1.add(new Track("Everytime", 300));
        songs1.add(new Track("Sunflower", 385));
        songs1.add(new Track("losing you", 229));
        Playlist l1 = new Playlist("1", "indie", "ricky", songs1);
        returnList.add(l1);

        songs2.add(new Track("thunderstruck", 3245));
        songs2.add(new Track("back in black", 22));
        songs2.add(new Track("CQCQ", 600));
        Playlist l2 = new Playlist("2", "rock", "ricky", songs2);
        returnList.add(l2);

        songs3.add(new Track("Through the fire and the flames", 432));
        songs3.add(new Track("Ring of fire", 123));
        songs3.add(new Track("Cry thunder", 782));
        Playlist l3 = new Playlist("3", "power-metal", "ricky", songs3);
        returnList.add(l3);

        return returnList;
    }
}
