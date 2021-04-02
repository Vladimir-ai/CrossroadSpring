package app.model;

import java.util.List;
import java.util.UUID;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TrafficLight {

    private UUID id = UUID.randomUUID();
    private List<RoadBlock> controlledBlocks;
    private TrafficLightState currentState;
    private long lastSwitchTime;
    private long cycleTimeRed;
    private long cycleTimeYellow;
    private long cycleTimeGreen;

    public TrafficLight(List<RoadBlock> controlledBlocks, TrafficLightState currentState) {
        this.controlledBlocks = controlledBlocks;
        this.currentState = currentState;
    }
}
