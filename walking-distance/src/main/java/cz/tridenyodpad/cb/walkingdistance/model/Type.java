package cz.tridenyodpad.cb.walkingdistance.model;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
public enum Type {

    WALKING("/foot-walking");

    private final String pathValue;
}
