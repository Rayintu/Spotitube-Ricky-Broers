package nl.han.dea.ricky;

import java.util.ArrayList;
import java.util.List;

public class TempPlaylistCreator {
    private List<Playlist> returnList = new ArrayList<Playlist>();

    public TempPlaylistCreator() {

    }

    public List<Playlist> createPlaylists() {
        List<Song> songs1 = new ArrayList<Song>();
        List<Song> songs2 = new ArrayList<Song>();
        List<Song> songs3 = new ArrayList<Song>();


        songs1.add(new Song("Everytime"));
        songs1.add(new Song("Sunflower"));
        songs1.add(new Song("losing you"));
        Playlist l1 = new Playlist("1", "indie", "ricky", songs1);
        returnList.add(l1);

        songs2.add(new Song("thunderstruck"));
        songs2.add(new Song("back in black"));
        songs2.add(new Song("CQCQ"));
        Playlist l2 = new Playlist("2", "rock", "ricky", songs2);
        returnList.add(l2);

        songs3.add(new Song("Through the fire and the flames"));
        songs3.add(new Song("Ring of fire"));
        songs3.add(new Song("Cry thunder"));
        Playlist l3 = new Playlist("3", "power-metal", "ricky", songs3);
        returnList.add(l3);

        return returnList;
    }
}
