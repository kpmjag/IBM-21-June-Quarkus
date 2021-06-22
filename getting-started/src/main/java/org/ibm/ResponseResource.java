package org.ibm;

import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriBuilder;

@Path("/responses")
public class ResponseResource {

    
    @Path("/ok")
    @GET
    public Response sayOk() {
        return Response.ok("Hello").build();
    }

    @Path("/create")
    @POST
    @Produces(MediaType.TEXT_PLAIN)
    public Response create() {
        return Response.created(UriBuilder.fromPath("/create").build()).entity("Created").build();
    }

    @Path("/sendstatus")
    @GET
    public Response sendStatus() {
        //return Response.status(200).entity("status").build();
        return Response.status(Response.Status.OK).entity("status").build();
    }
}
