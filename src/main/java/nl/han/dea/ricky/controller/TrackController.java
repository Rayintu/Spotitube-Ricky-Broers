package nl.han.dea.ricky.controller;

import nl.han.dea.ricky.service.TrackService;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/tracks")
public class TrackController {

    TrackService trackService = new TrackService();

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAllTracks(
            @QueryParam("forPlaylist") int playlistID,
            @QueryParam("token") String token) {

        try {
            return Response.status(Response.Status.OK).entity(trackService.getTracks()).build();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

    }
}
