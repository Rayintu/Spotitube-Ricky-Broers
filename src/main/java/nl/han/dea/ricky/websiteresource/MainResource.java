package nl.han.dea.ricky.websiteresource;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Path("/")
public class MainResource {
    @GET
    @Produces(MediaType.TEXT_PLAIN)
    public String connectionTest() {
        return "Server started successfully, hello.";
    }
}
