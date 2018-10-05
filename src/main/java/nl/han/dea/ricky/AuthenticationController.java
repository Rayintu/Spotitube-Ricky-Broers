package nl.han.dea.ricky;

import nl.han.dea.ricky.Service.LoginService;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/authenticate")
public class AuthenticationController {

    private LoginService loginService = new LoginService();

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response authenticate(LoginCredentials creds) {
        try {
            return Response.status(Response.Status.OK).entity(loginService.login(creds)).build();
        } catch (LoginException e) {
            String message = e.getMessage();
            return Response.status(Response.Status.UNAUTHORIZED).entity(message).build();
        }
    }
}
