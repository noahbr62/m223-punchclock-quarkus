package ch.zli.m223.punchclock.controller;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;

import ch.zli.m223.punchclock.domain.User;
import ch.zli.m223.punchclock.service.AuthenticationService;

@Path("/auth")
public class AuthentificationController {
    @Inject
    AuthenticationService authenticationService;
    @POST
    @Path("/login")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.TEXT_PLAIN)
    public String login(User user ){
        if(authenticationService.checkIfUserExists(user)) {
            return authenticationService.generateValidJwtToken(user.getUsername());
        } else {
            throw new NotAuthorizedException("User "+ user.getUsername()+" was not found");
        }
    }
}
