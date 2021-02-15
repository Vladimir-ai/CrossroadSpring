package app.domain;

import lombok.Getter;
import lombok.ToString;

@ToString
public class TrafficLight {
    public enum TrafficLightState{
        RED,
        YELLOW,
        GREEN,
        GREEN_RIGHT,
        BOTH_GREEN;
    }

    @Getter
    private TrafficLightState currentState;


}
