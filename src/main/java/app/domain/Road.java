package app.domain;

import java.util.List;
import lombok.ToString;

@ToString
public class Road {
    private boolean isVertical;

    private List<Line> lineList;

    private List<TrafficLight> trafficLightList;

}
