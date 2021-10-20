package cz.tridenyodpad.cb.walkingdistance;

import com.fasterxml.jackson.databind.ObjectMapper;
import cz.tridenyodpad.cb.walkingdistance.client.OpenRouteServiceClient;
import cz.tridenyodpad.cb.walkingdistance.dto.openrouteservice.MatrixRequest;
import cz.tridenyodpad.cb.walkingdistance.dto.openrouteservice.MatrixResponse;
import cz.tridenyodpad.cb.walkingdistance.model.Metric;
import cz.tridenyodpad.cb.walkingdistance.model.Type;
import cz.tridenyodpad.cb.walkingdistance.model.Unit;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class WalkingDistanceCalculator {

    private final OpenRouteServiceClient orsClient;

    @SneakyThrows
    public List<MatrixResponse> calculateDistances(final List<Coordinates> sources, final List<Coordinates> destinations) {
        final List<MatrixResponse> responses = new ArrayList<>();
        int callsCount = 0;
        int failedRequestsCount = 0;
        for (final Coordinates source : sources) {
            for (final Coordinates destination : destinations) {
                final MatrixRequest request = createRequest(source, destination);
                try {
                    responses.add(orsClient.callMatrix(Type.WALKING, request));
                } catch (final Exception exception) {
                    log.error("Error with request " + request.toString(), exception);
                    failedRequestsCount++;
                }
                callsCount++;
                if (callsCount % 100 == 0) {
                    log.info("{} calls done.", callsCount);
                }
            }
        }
        log.info("{} matrix requests processed.", callsCount);
        log.warn("{} matrix requests failed.", failedRequestsCount);

        try (final BufferedWriter writer = new BufferedWriter(new FileWriter("savior.json"))) {
            writer.write(new ObjectMapper().writeValueAsString(responses));
        }

        return responses;
    }

    private MatrixRequest createRequest(final Coordinates source, final Coordinates destination) {
        final List<Coordinates> locations = List.of(source, destination);
        final List<Integer> sourcesIndexes = List.of(locations.indexOf(source));
        final List<Integer> destinationsIndexes = List.of(locations.indexOf(destination));
        return MatrixRequest.builder()
                .locations(locations)
                .sources(sourcesIndexes)
                .destinations(destinationsIndexes)
                .metrics(List.of(Metric.DISTANCE, Metric.DURATION))
                .unit(Unit.METERS)
                .build();
    }
}
