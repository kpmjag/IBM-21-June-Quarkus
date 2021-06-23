package org.ibm;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

@Path("/customer")
public class CustomerResource {

    @GET
    @Path("/{customerId}")
    @Produces(MediaType.TEXT_PLAIN)
    public Response getCustomerId(@PathParam("customerId") String id) {

        if (id.equals("abc")) {
            throw new WebApplicationException("Element with position " + id + " does not exist.", 404);
        }
        //biz if this failed throw new YourBizException("Something went wrong in my biz")
        return Response.ok(Integer.parseInt(id)).build();
    }

    @Provider
    public static class WebApplicationErrors implements ExceptionMapper<WebApplicationException> {
        @Override
        public Response toResponse(WebApplicationException e) {
            //return Response.status(500).entity(e.getMessage()).build();
            return Response.status(Response.Status.BAD_GATEWAY).entity(e.getMessage()).header("err", "myerr").build();
        }
    }

    @Provider
    public static class CustomerExceptionMapper implements ExceptionMapper<Exception> {
        @Override
        public Response toResponse(Exception e) {
            //return Response.status(500).entity(e.getMessage()).build();
            return Response.status(Response.Status.BAD_GATEWAY).entity(e.getMessage()).header("err", "myerr").build();
        }
    }

}
