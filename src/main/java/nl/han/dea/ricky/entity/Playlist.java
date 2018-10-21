package nl.han.dea.ricky.entity;

public class Playlist {
    private int id;
    private String name;
    private boolean owner;

    private Track[] tracks;

    public Playlist(int id, String name, boolean owner, Track[] tracks) {
        this.id = id;
        this.name = name;
        this.owner = owner;
        this.tracks = tracks;
    }

//    public int getPlaylistLength() {
//        int length = 0;
//        for (Track track : tracks) {
//            length += track.getLength();
//        }
//        return length;
//    }

    public Playlist() {

    }

//    public List<Track> addSongToPlaylist(Track track) {
//        tracks.add(track);
//        return tracks;
//    }

    public Track[] getTracks() {
        return tracks;
    }

    public void setTracks(Track[] tracks) {
        this.tracks = tracks;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean getOwner() {
        return owner;
    }

    public void setOwner(boolean owner) {
        this.owner = owner;
    }
}
