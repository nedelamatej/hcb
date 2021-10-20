package cz.tridenyodpad.cb.walkingdistance.dto.openrouteservice;

import lombok.Data;

@Data
public class Metadata {

    private Engine engine;
    private String service;
    private Query query;
    private String attribution;
    private long timestamp;
}
