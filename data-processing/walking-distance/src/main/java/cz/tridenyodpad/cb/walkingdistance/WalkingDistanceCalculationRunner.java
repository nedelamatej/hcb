package cz.tridenyodpad.cb.walkingdistance;

import cz.tridenyodpad.cb.walkingdistance.csv.CoordinatesCSVParser;
import cz.tridenyodpad.cb.walkingdistance.csv.MatrixResultCSVParser;
import cz.tridenyodpad.cb.walkingdistance.dto.openrouteservice.MatrixResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.io.File;
import java.util.List;

@Component
@RequiredArgsConstructor
@Slf4j
public class WalkingDistanceCalculationRunner implements CommandLineRunner {

    private final CoordinatesCSVParser coordinatesCSVParser;
    private final WalkingDistanceCalculator calculator;
    private final MatrixResultCSVParser matrixResultCSVParser;

    @Override
    public void run(final String... csvFilesPaths) {
        if (csvFilesPaths.length < 3) {
            throw new IllegalStateException("2 paths to CSV files and 1 path for JSON output need to be present");
        }
        final File personsCoordinatesFile = new File(csvFilesPaths[0]);
        final File containersCoordinatesFile = new File(csvFilesPaths[1]);
        final File outputFile = new File(csvFilesPaths[2]);

        final List<Coordinates> personCoordinates = coordinatesCSVParser.parse(personsCoordinatesFile, 2);
        log.info("Parsed {} person coordinates from {}.", personCoordinates.size(), personsCoordinatesFile.getAbsolutePath());
        final List<Coordinates> containersCoordinates = coordinatesCSVParser.parse(containersCoordinatesFile, 2);
        log.info("Parsed {} containers coordinates from {}.", containersCoordinates.size(), containersCoordinatesFile.getAbsolutePath());

        final List<MatrixResponse> matrixResponses = calculator.calculateDistances(personCoordinates, containersCoordinates);
        log.info("Obtained {} matrix responses.", matrixResponses.size());

        matrixResultCSVParser.saveFormatted(outputFile, matrixResponses);
        log.info("Wrote {} matrix responses as CSV to {}.", matrixResponses.size(), outputFile.getAbsolutePath());
    }
}
