package cz.tridenyodpad.cb.walkingdistance.csv;

import cz.tridenyodpad.cb.walkingdistance.Coordinates;
import cz.tridenyodpad.cb.walkingdistance.dto.openrouteservice.MatrixResponse;
import lombok.SneakyThrows;
import org.springframework.stereotype.Component;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.math.BigDecimal;
import java.util.List;

@Component
public class MatrixResultCSVParser {

    @SneakyThrows
    public void saveFormatted(final File file, final List<MatrixResponse> matrixResponses) {
        try (final BufferedWriter writer = new BufferedWriter(new FileWriter(file))) {
            writer.write("x_p,y_p,x_c,y_c,distance,duration");
            writer.newLine();
            for (final MatrixResponse matrixResponse : matrixResponses) {
                final Coordinates personCoordinates = matrixResponse.getSources().get(0).getLocation();
                final Coordinates containerCoordinates = matrixResponse.getDestinations().get(0).getLocation();
                final BigDecimal distance = matrixResponse.getDistances().get(0).get(0);
                final BigDecimal duration = matrixResponse.getDurations().get(0).get(0);
                writer.write(String.format(
                        "%s,%s,%s,%s,%s,%s",
                        personCoordinates.getLatitude(), personCoordinates.getLongitude(),
                        containerCoordinates.getLatitude(), containerCoordinates.getLongitude(),
                        distance, duration
                ));
                writer.newLine();
            }
        }
    }
}
