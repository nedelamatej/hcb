package cz.tridenyodpad.cb.walkingdistance.configuration;

import cz.tridenyodpad.cb.walkingdistance.model.Unit;
import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties("ors")
@Data
public class OpenRouteServiceConfiguration {

    private String apiKey = "5b3ce3597851110001cf62483ed629f3119245bd92b2a3b4a339e631";

    private String apiPath = "https://api.openrouteservice.org/v2";
    private String matrixPath = "/matrix";

    private int maxMatrixCallsPerMinute = 40;
    private int maxMatrixLocationsPerRequest = 3500;

    private Unit unit = Unit.METERS;
}
