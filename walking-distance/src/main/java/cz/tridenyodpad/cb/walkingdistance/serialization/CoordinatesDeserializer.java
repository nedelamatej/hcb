package cz.tridenyodpad.cb.walkingdistance.serialization;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;
import cz.tridenyodpad.cb.walkingdistance.Coordinates;

import java.io.IOException;
import java.math.BigDecimal;

public class CoordinatesDeserializer extends StdDeserializer<Coordinates> {

    public CoordinatesDeserializer() {
        super(Coordinates.class);
    }

    @Override
    public Coordinates deserialize(final JsonParser p, final DeserializationContext ctxt) throws IOException {
        final JsonNode node = p.getCodec().readTree(p);
        final BigDecimal longitude = new BigDecimal(node.get(0).asText());
        final BigDecimal latitude = new BigDecimal(node.get(1).asText());
        return new Coordinates(latitude, longitude);
    }
}
