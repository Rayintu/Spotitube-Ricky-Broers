package nl.han.dea.ricky.Resource;

import nl.han.dea.ricky.LoginCredentials;
import nl.han.dea.ricky.LoginException;
import nl.han.dea.ricky.Playlist;
import nl.han.dea.ricky.Response.AllPlaylistViewResponse;
import nl.han.dea.ricky.Service.LoginService;
import nl.han.dea.ricky.TempPlaylistCreator;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

@Path("/")
public class MainResource {
    private String token;


    @GET
    @Produces(MediaType.TEXT_PLAIN)
    public String helloWorld() {
        return "Hello World!";
    }

    @POST
    @Path("/login")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response login(LoginCredentials creds) {
        LoginService loginService = new LoginService();
        try {
            return Response.status(Response.Status.OK).entity(loginService.login(creds)).build();
        } catch (LoginException e) {
            String message = e.getMessage();
            return Response.status(Response.Status.UNAUTHORIZED).entity(message).build();
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
        AllPlaylistViewResponse allPlaylistViewResponse = new AllPlaylistViewResponse(playlist);
        if (allPlaylistViewResponse != null) {
            return Response.status(Response.Status.OK).entity(allPlaylistViewResponse).build();
        } else {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
        }
    }
}
