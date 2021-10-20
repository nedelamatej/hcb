package cz.tridenyodpad.cb.walkingdistance.dto.openrouteservice;

import com.fasterxml.jackson.annotation.JsonAnyGetter;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ContainerToPersonsResult {

//    @JsonAnyGetter
//    private List<PersonResult> personResults;

    @JsonAnyGetter
    private List<WalkingResult> walkingResults;
}
