package org.ibm;

import org.ibm.services.UserService;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;

@Path("/users")
public class UserResource {

    @Inject
    UserService userService;

    @GET
    public String getUser(){
        return userService.getUser();
    }

}
