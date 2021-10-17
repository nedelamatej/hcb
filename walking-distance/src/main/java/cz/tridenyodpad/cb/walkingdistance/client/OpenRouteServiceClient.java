package cz.tridenyodpad.cb.walkingdistance.client;

import cz.tridenyodpad.cb.walkingdistance.configuration.OpenRouteServiceConfiguration;
import cz.tridenyodpad.cb.walkingdistance.dto.openrouteservice.MatrixRequest;
import cz.tridenyodpad.cb.walkingdistance.dto.openrouteservice.MatrixResponse;
import cz.tridenyodpad.cb.walkingdistance.model.Type;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
@RequiredArgsConstructor
public class OpenRouteServiceClient {

    private final OpenRouteServiceConfiguration configuration;
    private final RestTemplate restTemplate;

    public MatrixResponse callMatrix(final Type type, final MatrixRequest request) {
        final HttpHeaders headers = new HttpHeaders();
        headers.add(HttpHeaders.AUTHORIZATION, configuration.getApiKey());
        final HttpEntity<MatrixRequest> requestEntity = new HttpEntity<>(request, headers);

        return restTemplate.postForObject(
                configuration.getApiPath() + configuration.getMatrixPath() + type.getPathValue(),
                requestEntity,
                MatrixResponse.class
        );
    }
}
