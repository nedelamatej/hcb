package cz.tridenyodpad.cb.walkingdistance.dto.openrouteservice;

import lombok.Data;

import java.util.List;

@Data
public class Query {

    private String responseType;
    private List<String> sources;
    private String profile;
    private List<String> destinations;
    private List<Double> locations;
    private List<String> metrics;
    private String units;
    private List<String> metricsStrings;
}
