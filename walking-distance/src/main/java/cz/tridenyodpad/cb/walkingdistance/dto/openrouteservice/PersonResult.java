package cz.tridenyodpad.cb.walkingdistance.dto.openrouteservice;

import com.fasterxml.jackson.annotation.JsonAnyGetter;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PersonResult {

    @JsonAnyGetter
    private WalkingResult walkingResult;
}
