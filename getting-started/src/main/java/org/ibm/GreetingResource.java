package org.ibm;

import org.ibm.services.GreeterService;

import javax.annotation.PostConstruct;
import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Path("/hello")
public class GreetingResource {

    @Inject
    GreeterService greeterService;
    @GET
    @Produces(MediaType.TEXT_PLAIN)
    public String hello() {
        return greeterService.sayHello();
    }
}