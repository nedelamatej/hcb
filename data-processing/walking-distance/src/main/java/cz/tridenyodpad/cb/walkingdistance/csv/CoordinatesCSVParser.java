package cz.tridenyodpad.cb.walkingdistance.csv;

import cz.tridenyodpad.cb.walkingdistance.Coordinates;
import lombok.SneakyThrows;
import org.springframework.stereotype.Component;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class CoordinatesCSVParser {

    @SneakyThrows
    public List<Coordinates> parse(final File file, final int firstCoordinatesLineNumber) {
        try (final BufferedReader reader = new BufferedReader(new FileReader(file))) {
            return reader.lines()
                    .skip(firstCoordinatesLineNumber - 1)
                    .map(line -> line.split(","))
                    .map(coordinates -> parseCoordinates(coordinates[0], coordinates[1]))
                    .collect(Collectors.toList());
        }
    }

    private Coordinates parseCoordinates(final String latitude, final String longitude) {
        return new Coordinates(new BigDecimal(latitude), new BigDecimal(longitude));
    }
}
