package app.repository.impl;


import app.domain.DTO.TrafficLightDTO;
import app.repository.TrafficLightRepository;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import org.springframework.stereotype.Repository;

@Repository
public class TrafficLightRepositoryImpl implements TrafficLightRepository {
    private final List<TrafficLightDTO> trafficLightDTOS = new ArrayList<>();

    @Override
    public Optional<TrafficLightDTO> get(UUID id) {
        return trafficLightDTOS.stream().filter(trafficLight -> trafficLight.getId().equals(id)).findFirst();
    }

    @Override
    public List<TrafficLightDTO> getAll() {
        return trafficLightDTOS;
    }

    @Override
    public void save(TrafficLightDTO entity) {
        trafficLightDTOS.add(entity);
    }

    @Override
    public void update(TrafficLightDTO entity) {
        trafficLightDTOS.stream().filter(trafficLight -> trafficLight.getId().equals(entity.getId()))
                .findFirst().ifPresent(trafficLight -> trafficLight = entity);
    }

    @Override
    public void delete(UUID id) {
        trafficLightDTOS.removeIf(trafficLight -> trafficLight.getId().equals(id));
    }

    @Override
    public void delete(TrafficLightDTO entity) {
        trafficLightDTOS.removeIf(trafficLight -> trafficLight.getId().equals(entity.getId()));
    }

    @Override
    public void clear() {
        trafficLightDTOS.clear();
    }
}
