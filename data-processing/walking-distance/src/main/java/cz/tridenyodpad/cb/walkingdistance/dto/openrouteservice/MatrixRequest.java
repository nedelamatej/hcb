package cz.tridenyodpad.cb.walkingdistance.dto.openrouteservice;

import com.fasterxml.jackson.annotation.JsonProperty;
import cz.tridenyodpad.cb.walkingdistance.Coordinates;
import cz.tridenyodpad.cb.walkingdistance.model.Metric;
import cz.tridenyodpad.cb.walkingdistance.model.Unit;
import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class MatrixRequest {

    private List<Coordinates> locations;
    private List<Integer> sources;
    private List<Integer> destinations;
    private List<Metric> metrics;

    @JsonProperty("units")
    private Unit unit;
}
