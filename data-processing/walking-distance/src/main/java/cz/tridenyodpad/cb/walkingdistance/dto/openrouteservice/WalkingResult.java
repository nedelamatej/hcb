package cz.tridenyodpad.cb.walkingdistance.dto.openrouteservice;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class WalkingResult {

    private BigDecimal distance;
    private BigDecimal duration;
}
