package org.ibm.filters;

import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerResponseContext;
import javax.ws.rs.container.ContainerResponseFilter;
import javax.ws.rs.ext.Provider;
import java.io.IOException;

@Provider
public class ApplicationFilter implements ContainerResponseFilter {
    @Override
    public void filter(ContainerRequestContext containerRequestContext, ContainerResponseContext containerResponseContext) throws IOException {
        System.out.println("Response Filters");
        //Send a header whenever new response is committed.
        System.out.println(containerRequestContext.getRequest().getMethod());
        containerResponseContext.getHeaders().add("company", "IBM");
    }
}
