package app.repository;

import app.domain.DTO.TrafficLightDTO;
import app.domain.entity.TrafficLight;
import java.util.List;
import java.util.Optional;

public interface TrafficLightRepository {
    Optional<TrafficLight> get(Long id);

    List<TrafficLight> getAll();

    void save(TrafficLight entity);

    void update(TrafficLight entity);

    void delete(Long id);

    void delete(TrafficLight entity);

    void clear();


}
