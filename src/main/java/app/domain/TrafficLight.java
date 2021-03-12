package app.domain;

import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TrafficLight {

    private List<RoadBlock> controlledBlocks;
    private TrafficLightState currentState;

}
