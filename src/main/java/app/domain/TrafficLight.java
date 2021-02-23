package app.domain;

import lombok.Data;
import lombok.ToString;

@Data
public class TrafficLight {
    public enum TrafficLightState{
        RED,
        YELLOW,
        GREEN,
        GREEN_RIGHT,
        BOTH_GREEN;
    }

    private Integer pos;
    private TrafficLightState currentState;

    public TrafficLight(Integer pos){
        this.pos = pos;
    }

}
