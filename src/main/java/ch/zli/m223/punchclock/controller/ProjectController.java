package ch.zli.m223.punchclock.controller;

import ch.zli.m223.punchclock.service.ProjectService;
import org.eclipse.microprofile.openapi.annotations.Operation;
import org.eclipse.microprofile.openapi.annotations.tags.Tag;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.List;

import ch.zli.m223.punchclock.domain.Project;

@Path("/projects")
@Tag(name = "Projects", description = "Handling of projects")
public class ProjectController {

    @Inject
    ProjectService projectService;

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Operation(summary = "List all Projects", description = "")
    public List<Project> list() {
        return projectService.findAll();
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Operation(summary = "Gets one Project", description = "")
    @Path("/{id}")
    public Project getSingleProject(@PathParam("id") Long id) {
        return projectService.getSingleProject(id);
    }

    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    @Operation(summary = "Add a new Project", description = "The newly created Project is returned. The id may not be passed.")
    public Project add(Project project) {
        return projectService.createProject(project);
    }

    @DELETE
    @Path("/{id}")
    @Operation(summary = "Delete a single Project", description = "")
    public void deleteEntry(@PathParam("id") Long id) {
        projectService.delete(id);
    }

    @PUT
    @Operation(summary = "Edit a single Project", description = "")
    public void update(Project project) {
        projectService.updateProject(project);
    }
}