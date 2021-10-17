package cz.tridenyodpad.cb.walkingdistance.dto.openrouteservice;

import lombok.Data;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Data
public class MatrixResponse {

    private List<List<BigDecimal>> durations = new ArrayList<>();
    private List<List<BigDecimal>> distances = new ArrayList<>();
    private List<Place> destinations = new ArrayList<>();
    private List<Place> sources = new ArrayList<>();
}
