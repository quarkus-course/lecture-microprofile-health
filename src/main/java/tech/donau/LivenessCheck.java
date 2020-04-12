package tech.donau;

import org.eclipse.microprofile.health.HealthCheck;
import org.eclipse.microprofile.health.HealthCheckResponse;
import org.eclipse.microprofile.health.Liveness;

import javax.enterprise.context.ApplicationScoped;
import java.util.Date;

@Liveness
@ApplicationScoped
public class LivenessCheck implements HealthCheck {

    @Override
    public HealthCheckResponse call() {
        return HealthCheckResponse.builder()
                .name("Quarkus server")
                .up()
                .withData("mavenPlugin", "1.3.0")
                .withData("systemTime", new Date().getTime())
                .build();
    }
}
