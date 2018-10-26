package nl.han.dea.ricky.controller;

import nl.han.dea.ricky.entity.Playlist;
import nl.han.dea.ricky.entity.Track;
import nl.han.dea.ricky.exception.LoginException;
import nl.han.dea.ricky.response.PlaylistResponse;
import nl.han.dea.ricky.service.IPlaylistService;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/playlists")
public class PlaylistsController {

    @Inject
    IPlaylistService playlistService;

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response viewPlaylists(@QueryParam("token") String token) {
        try {

            PlaylistResponse ownedPlaylists = playlistService.getOwnedPlaylists(token);
            return Response.status(Response.Status.OK).entity(ownedPlaylists).build();
        } catch (Exception e) {
            e.printStackTrace();
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(e.getMessage()).build();
        }

    }

    @Path("/{id}")
    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response editPlaylistName(Playlist playlist, @PathParam("id") int id, @QueryParam("token") String token) {
        if (token == null) {
            return Response.status(Response.Status.BAD_REQUEST).build();
        }
        try {
            String newPlaylistName = playlist.getName();
            return Response.status(Response.Status.OK).entity(playlistService.editPlaylistName(newPlaylistName, token, id)).build();
        } catch (RuntimeException e) {
            e.printStackTrace();
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(e.getMessage()).build();
        } catch (LoginException e) {
            return Response.status(Response.Status.FORBIDDEN).build();
        }
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response addNewPlaylist(Playlist playlist, @QueryParam("token") String token) {
        try {
            return Response.status(Response.Status.CREATED).entity(playlistService.addNewPlaylist(playlist, token)).build();
        } catch (RuntimeException e) {
            e.printStackTrace();
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(e.getMessage()).build();
        }
    }

    @Path("/{id}")
    @DELETE
    @Produces(MediaType.APPLICATION_JSON)
    public Response deletePlaylist(@PathParam("id") int id, @QueryParam("token") String token) {
        try {
            return Response.status(Response.Status.OK).entity(playlistService.deletePlaylist(id, token)).build();
        } catch (RuntimeException e) {
            e.printStackTrace();
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(e.getMessage()).build();
        }
    }

    @Path("/{id}/tracks")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAllTracksInPlaylist(@PathParam("id") int id, @QueryParam("token") String token) {
        try {
            return Response.status(Response.Status.OK).entity(playlistService.getAllTracksInPlaylist(id, token)).build();
        } catch (RuntimeException e) {
            e.printStackTrace();
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(e.getMessage()).build();
        }
    }

    @Path("/{id}/tracks")
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response addTrackToPlaylist(@PathParam("id") int id, @QueryParam("token") String token, Track track) {
        try {
            return Response.status(Response.Status.CREATED).entity(playlistService.addTrackToPlaylist(id, token, track)).build();
        } catch (Exception e) {
            e.printStackTrace();
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(e.getMessage()).build();
        }
    }

    @Path("/{playlistId}/tracks/{trackId}")
    @DELETE
    @Produces(MediaType.APPLICATION_JSON)
    public Response removeTrackFromPlaylist(
            @PathParam("playlistId") int playlistId, @PathParam("trackId") int trackId, @QueryParam("token") String token) {
        try {
            return Response.status(Response.Status.OK).entity(playlistService.deleteTrackFromPlaylist(playlistId, trackId, token)).build();
        } catch (Exception e) {
            e.printStackTrace();
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(e.getMessage()).build();
        }
    }

}
