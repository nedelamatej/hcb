package cz.tridenyodpad.cb.walkingdistance.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public enum Unit {

    @JsonProperty("m")
    METERS,

    @JsonProperty("km")
    KILOMETERS,

    @JsonProperty("mi")
    MILES;
}
