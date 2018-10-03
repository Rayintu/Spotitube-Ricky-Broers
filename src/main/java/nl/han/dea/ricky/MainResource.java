package nl.han.dea.ricky;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

@Path("/")
public class MainResource {


    @GET
    @Produces(MediaType.TEXT_PLAIN)
    public String helloWorld() {
        return "Hello World!";
    }


    @GET
    @Path("/login")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response yeet() {
        return Response.status(Response.Status.NOT_ACCEPTABLE).build();
    }

    @POST
    @Path("/login")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response login(LoginCredentials creds) {
        if (creds.getUser().equals("ricky") && creds.getPassword().equals("yeet")) {
            LoginReturnObject returnObject = new LoginReturnObject("1234-1234-1234", "Ricky Broers");
            return Response.status(Response.Status.OK).entity(returnObject).build();
        } else {
            return Response.status(Response.Status.UNAUTHORIZED).build();
        }
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
////            return Response.status(Response.Status.OK).entity(playlists).build();
////        } else {
////            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
////        }
//    }

    @GET
    @Path("/playlists")
    @Produces(MediaType.APPLICATION_JSON)
    public Response test() {
        TempPlaylistCreator creator = new TempPlaylistCreator();
        List<Playlist> playlist = creator.createPlaylists();
        Playlists playlists = new Playlists(playlist, 123445);
        if (playlists != null) {
            return Response.status(Response.Status.OK).entity(playlists).build();
        } else {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
        }
    }
}
