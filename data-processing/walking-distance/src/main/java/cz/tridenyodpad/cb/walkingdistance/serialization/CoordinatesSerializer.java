package cz.tridenyodpad.cb.walkingdistance.serialization;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;
import cz.tridenyodpad.cb.walkingdistance.Coordinates;

import java.io.IOException;

public class CoordinatesSerializer extends StdSerializer<Coordinates> {

    public CoordinatesSerializer() {
        super(Coordinates.class);
    }

    @Override
    public void serialize(final Coordinates value, final JsonGenerator gen, final SerializerProvider provider) throws IOException {
        gen.writeStartArray();
        gen.writeNumber(value.getLongitude());
        gen.writeNumber(value.getLatitude());
        gen.writeEndArray();
    }
}
