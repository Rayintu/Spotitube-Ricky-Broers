package nl.han.dea.ricky.controller;

import nl.han.dea.ricky.entity.Playlist;
import nl.han.dea.ricky.service.PlaylistService;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/playlists")
public class PlaylistsController {


    PlaylistService playlistService = new PlaylistService();


    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response viewPlaylists(@QueryParam("token") String token) {
        try {
            return Response.status(Response.Status.OK).entity(playlistService.getOwnedPlaylists(token)).build();
        } catch (Exception e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
        }

    }

    @Path("/{id}")
    @PUT
    @Produces(MediaType.APPLICATION_JSON)
    public Response editPlaylistName(Playlist playlist, @PathParam("id") int id, @QueryParam("token") String token) {
        try {
            String newPlaylistName = playlist.getName();
            return Response.status(Response.Status.OK).entity(playlistService.editPlaylistName(newPlaylistName, token, id)).build();
        } catch (Exception e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
        }
    }

    @POST
    @Produces(MediaType.APPLICATION_JSON)
    public Response addNewPlaylist(Playlist playlist, @QueryParam("token") String token) {
        try {
            return Response.status(Response.Status.OK).entity(playlistService.addNewPlaylist(playlist, token));
        } catch (Exception e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
        }
    }
}
