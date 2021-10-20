package cz.tridenyodpad.cb.walkingdistance.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public enum Metric {

    @JsonProperty("distance")
    DISTANCE,

    @JsonProperty("duration")
    DURATION
}
