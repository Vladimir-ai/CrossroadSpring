package app.repository.impl;


import app.model.TrafficLight;
import app.repository.TrafficLightRepository;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import org.springframework.stereotype.Repository;

@Repository
public class TrafficLightRepositoryImpl implements TrafficLightRepository {
    private final List<TrafficLight> trafficLights = new ArrayList<>();

    @Override
    public Optional<TrafficLight> get(UUID id) {
        return trafficLights.stream().filter(trafficLight -> trafficLight.getId().equals(id)).findFirst();
    }

    @Override
    public List<TrafficLight> getAll() {
        return trafficLights;
    }

    @Override
    public void save(TrafficLight entity) {
        trafficLights.add(entity);
    }

    @Override
    public void update(TrafficLight entity) {
        trafficLights.stream().filter(trafficLight -> trafficLight.getId().equals(entity.getId()))
                .findFirst().ifPresent(trafficLight -> trafficLight = entity);
    }

    @Override
    public void delete(UUID id) {
        trafficLights.removeIf(trafficLight -> trafficLight.getId().equals(id));
    }

    @Override
    public void delete(TrafficLight entity) {
        trafficLights.removeIf(trafficLight -> trafficLight.getId().equals(entity.getId()));
    }

    @Override
    public void clear() {
        trafficLights.clear();
    }
}
