package app.model;

import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TrafficLight {

    private List<RoadBlock> controlledBlocks;
    private TrafficLightState currentState;

}
