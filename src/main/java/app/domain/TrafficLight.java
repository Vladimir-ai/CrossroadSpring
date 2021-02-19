package app.domain;

import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class TrafficLight {
    public enum TrafficLightState{
        RED,
        YELLOW,
        GREEN,
        GREEN_RIGHT,
        BOTH_GREEN;
    }

    private TrafficLightState currentState;


}
