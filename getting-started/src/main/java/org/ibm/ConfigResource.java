package org.ibm;

import org.eclipse.microprofile.config.inject.ConfigProperty;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.core.Response;

@Path("/config")
public class ConfigResource {

    //inject configuration values
    @ConfigProperty(name = "greeting.message")
    String message;
    @ConfigProperty(name = "greeting.name")
    String name;
    @ConfigProperty(name = "greeting.from")
    String from;
    @ConfigProperty(name = "greeting.suffix", defaultValue = "!")
    String suffix;

    @Path("/greet")
    @GET
    public Response getGreeting() {
        return Response.ok().entity(message + "  " +  name + " from " + from + " " + suffix ).build();
    }
}
