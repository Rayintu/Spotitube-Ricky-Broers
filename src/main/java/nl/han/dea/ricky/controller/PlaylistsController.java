package nl.han.dea.ricky.controller;

import nl.han.dea.ricky.Playlist;
import nl.han.dea.ricky.StorageFactory;
import nl.han.dea.ricky.TempPlaylistCreator;
import nl.han.dea.ricky.response.AllPlaylistViewResponse;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

@Path("/playlists")
public class PlaylistsController {


    StorageFactory storage = new StorageFactory();

    @GET
    public void setToken() {
        storage.getInstanceOf();
    }


    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response viewPlaylists(@QueryParam("token") String token) {
        TempPlaylistCreator creator = new TempPlaylistCreator();
        List<Playlist> playlists = creator.createPlaylists();
        AllPlaylistViewResponse allPlaylistViewResponse = new AllPlaylistViewResponse(playlists);
        if (allPlaylistViewResponse != null) {
            return Response.status(Response.Status.OK).entity(allPlaylistViewResponse).build();
        } else {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
        }
    }
}
