package nl.han.dea.ricky.websiteresource;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Path("/")
public class MainResource {
    private String token;


    @GET
    @Produces(MediaType.TEXT_PLAIN)
    public String helloWorld() {
        return "Hello World!";
    }


//    @GET
//    @Path("/playlists")
//    @Produces(MediaType.APPLICATION_JSON)
//    public List<Playlist> viewPlaylists() {
//        TempPlaylistCreator creator = new TempPlaylistCreator();
//        List<Playlist> playlists = creator.createPlaylists();
//
//        return playlists;
////        if(playlists != null){
////            return response.status(response.Status.OK).entity(playlists).build();
////        } else {
////            return response.status(response.Status.INTERNAL_SERVER_ERROR).build();
////        }
//    }


}
