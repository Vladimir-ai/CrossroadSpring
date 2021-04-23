package app.domain.DTO;

import java.util.List;
import java.util.UUID;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TrafficLightDTO {

    private UUID id = UUID.randomUUID();
    private List<RoadBlockDTO> controlledBlocks;
    private TrafficLightState currentState;
    private long lastSwitchTime;
    private long cycleTimeRed;
    private long cycleTimeYellow;
    private long cycleTimeGreen;

    public TrafficLightDTO(List<RoadBlockDTO> controlledBlocks, TrafficLightState currentState) {
        this.controlledBlocks = controlledBlocks;
        this.currentState = currentState;
    }
}
