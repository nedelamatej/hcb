package cz.tridenyodpad.cb.walkingdistance.dto.openrouteservice;

import cz.tridenyodpad.cb.walkingdistance.Coordinates;
import lombok.Data;

import java.math.BigDecimal;

@Data
public class Place {

    private Coordinates location;
    private BigDecimal snappedDistance;
}
