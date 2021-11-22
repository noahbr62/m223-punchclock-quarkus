package ch.zli.m223.punchclock.controller;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;

import ch.zli.m223.punchclock.domain.Entry;
import ch.zli.m223.punchclock.domain.User;
import ch.zli.m223.punchclock.service.AuthenticationService;
import io.quarkus.security.Authenticated;
import org.eclipse.microprofile.openapi.annotations.Operation;

import java.util.List;

@Path("/auth")
public class AuthentificationController {
    @Inject
    AuthenticationService authenticationService;
    @POST
    @Path("/login")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    @Operation(summary = "Check the existence of a user", description = "A Token will be returned")
    public String login(User user ){
        if(authenticationService.checkIfUserExists(user)) {
            return authenticationService.generateValidJwtToken(user.getUsername());
        } else {
            throw new NotAuthorizedException("User "+ user.getUsername()+" was not found");
        }
    }

    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    @Operation(summary = "Add a new User", description = "The newly created User is returned. The id may not be passed.")
    public User add(User user) {
        return authenticationService.createUser(user);
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Authenticated
    @Operation(summary = "List all Users", description = "")
    public List<User> list() {
        return authenticationService.findAll();
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Authenticated
    @Operation(summary = "Gets one User", description = "")
    @Path("/{id}")
    public User getSingleUser(@PathParam("id") Long id) {
        return authenticationService.getSingleUser(id);
    }

    @DELETE
    @Authenticated
    @Path("/{id}")
    @Operation(summary = "Delete a single User", description = "")
    public void deleteEntry(@PathParam("id") Long id) {
        authenticationService.delete(id);
    }
}
