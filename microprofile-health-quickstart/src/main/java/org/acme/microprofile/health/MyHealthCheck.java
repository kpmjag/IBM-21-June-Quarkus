package org.acme.microprofile.health;

import org.eclipse.microprofile.health.HealthCheck;
import org.eclipse.microprofile.health.HealthCheckResponse;
import org.eclipse.microprofile.health.Liveness;

import javax.enterprise.context.ApplicationScoped;
import javax.ws.rs.Path;

@Path("/myhealthapi")
@Liveness
@ApplicationScoped
public class MyHealthCheck implements HealthCheck {

    @Override
    public HealthCheckResponse call() {
        return HealthCheckResponse.up("my service is up");
    }
}
