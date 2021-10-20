package cz.tridenyodpad.cb.walkingdistance;

import cz.tridenyodpad.cb.walkingdistance.configuration.OpenRouteServiceConfiguration;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

@SpringBootApplication
@EnableConfigurationProperties(OpenRouteServiceConfiguration.class)
public class WalkingDistanceApplication {

    public static void main(String[] args) {
        SpringApplication.run(WalkingDistanceApplication.class, args);
    }
}
