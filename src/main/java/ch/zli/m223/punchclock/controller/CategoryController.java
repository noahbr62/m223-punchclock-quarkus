package ch.zli.m223.punchclock.controller;

import javax.ws.rs.Path;
import java.util.List;

import java.util.List;

import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.DELETE;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.eclipse.microprofile.openapi.annotations.Operation;
import org.eclipse.microprofile.openapi.annotations.tags.Tag;

import ch.zli.m223.punchclock.domain.Category;
import ch.zli.m223.punchclock.service.CategoryService;

@Path("/categories")
@Tag(name = "Entries", description = "Handling of categories")
public class CategoryController {
    @Inject
    CategoryService categoryservice;

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Operation(summary = "List all Categories", description = "")
    public List<Category> list() {
        return categoryservice.findAll();
    }

   
    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    @Operation(summary = "Add a new Category", description = "The newly created Category is returned. The id may not be passed.")
    public Category add(Category category) {
        return categoryservice.createCategory(category);
    }

}
