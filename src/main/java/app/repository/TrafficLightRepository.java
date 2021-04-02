package app.repository;

import app.model.TrafficLight;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface TrafficLightRepository {
    Optional<TrafficLight> get(UUID id);

    List<TrafficLight> getAll();

    void save(TrafficLight entity);

    void update(TrafficLight entity);

    void delete(UUID id);

    void delete(TrafficLight entity);

    void clear();


}
