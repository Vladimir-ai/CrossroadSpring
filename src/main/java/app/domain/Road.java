package app.domain;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.ToString;

@Data
@AllArgsConstructor
public class Road {
    private boolean isVertical;
    private Integer pos;
    private List<Line> lineList;
    private List<TrafficLight> trafficLightList;
    private List<Pedestrian> pedestrianList;
}
