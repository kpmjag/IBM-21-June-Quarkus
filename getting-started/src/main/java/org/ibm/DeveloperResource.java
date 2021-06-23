package org.ibm;

import org.ibm.entity.Developer;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriBuilder;

@Path("/developer")
public class DeveloperResource {

    @POST
    @Path("/create")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response createDeveloper(Developer developer) {
        developer.persist();
        System.out.println(developer);
        ///developer/1 = UriBuilder.fromResource().
        return Response.created(UriBuilder.fromResource(DeveloperResource.class).path("/create/" + Long.toString(developer.getId())).build())
                .entity(developer)
                .header("dev", developer.getName())
                .build();
    }

    @Path("/list")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getDeveloper() {
        Developer developer = new Developer();
        developer.setName("Subramanian");
        developer.persist();
        return Response.ok().entity(developer).build();
    }

}
