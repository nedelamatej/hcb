package cz.tridenyodpad.cb.walkingdistance.mapping;

import cz.tridenyodpad.cb.walkingdistance.dto.openrouteservice.ContainerToPersonsResult;
import cz.tridenyodpad.cb.walkingdistance.dto.openrouteservice.MatrixResponse;
import cz.tridenyodpad.cb.walkingdistance.dto.openrouteservice.WalkingResult;
import org.mapstruct.Mapper;

import java.util.ArrayList;
import java.util.List;

@Mapper(componentModel = "spring")
public abstract class MatrixResultMapper {

    public MatrixResponse merge(final List<MatrixResponse> matrices) {
        final MatrixResponse mergedResponse = new MatrixResponse();
        matrices.forEach(response -> {
            for (int i = 0; i < response.getDistances().size(); i++) {
                mergedResponse.getDurations().get(i).addAll(response.getDurations().get(i));
                mergedResponse.getDistances().get(i).addAll(response.getDistances().get(i));
            }
            mergedResponse.getDestinations().addAll(response.getDestinations());
        });
        return mergedResponse;
    }

    public List<ContainerToPersonsResult> map(final MatrixResponse matrix) {
        final List<ContainerToPersonsResult> containerToPersonsResults = new ArrayList<>();
        for (int containerIndex = 0; containerIndex < matrix.getDestinations().size(); containerIndex++) {
            final List<WalkingResult> walkingResults = new ArrayList<>();
            for (int personIndex = 0; personIndex < matrix.getSources().size(); personIndex++) {
                final WalkingResult walkingResult = new WalkingResult(
                        matrix.getDistances().get(personIndex).get(containerIndex),
                        matrix.getDurations().get(personIndex).get(containerIndex)
                );
                walkingResults.add(walkingResult);
            }
            containerToPersonsResults.add(new ContainerToPersonsResult(walkingResults));
        }
        return containerToPersonsResults;
    }
}
