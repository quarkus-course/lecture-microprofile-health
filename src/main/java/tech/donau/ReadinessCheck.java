package tech.donau;

import io.quarkus.runtime.StartupEvent;
import org.eclipse.microprofile.health.HealthCheck;
import org.eclipse.microprofile.health.HealthCheckResponse;
import org.eclipse.microprofile.health.Readiness;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.event.Observes;

@Readiness
@ApplicationScoped
public class ReadinessCheck implements HealthCheck {

    public static boolean READY = false;

    public void onStart(@Observes StartupEvent event) {
        new Thread(()-> {
            try {
                Thread.sleep(15000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            READY = true;
        }).start();

    }

    @Override
    public HealthCheckResponse call() {
        if (READY) {
            return HealthCheckResponse.up("Readyness");
        } else {
            return HealthCheckResponse.down("Readyness");
        }
    }
}
