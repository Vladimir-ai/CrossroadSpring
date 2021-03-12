package app.repository;

import app.domain.TrafficLight;
import java.util.List;

public interface TrafficLightRepository {
    List<TrafficLight> getAll();

}
