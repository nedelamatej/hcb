package cz.tridenyodpad.cb.walkingdistance;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import cz.tridenyodpad.cb.walkingdistance.serialization.CoordinatesDeserializer;
import cz.tridenyodpad.cb.walkingdistance.serialization.CoordinatesSerializer;
import lombok.Value;

import java.math.BigDecimal;

@Value
@JsonSerialize(using = CoordinatesSerializer.class)
@JsonDeserialize(using = CoordinatesDeserializer.class)
public class Coordinates {

    /**
     * Latitude, also referred as x
     */
    BigDecimal latitude;

    /**
     * Latitude, also referred as y
     */
    BigDecimal longitude;
}
