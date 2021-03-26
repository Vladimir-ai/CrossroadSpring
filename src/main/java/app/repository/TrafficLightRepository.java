package app.repository;


import app.interfaces.DataRepository;
import app.model.TrafficLight;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import org.springframework.stereotype.Repository;

@Repository
public class TrafficLightRepository implements DataRepository<TrafficLight> {
    private List<TrafficLight> trafficLights = new ArrayList<>();

    @Override
    public Optional<TrafficLight> get(long id) {
        return Optional.empty();
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
        //TO-DO
    }

    @Override
    public void delete(long id) {
        trafficLights.remove((int) id);
    }

    @Override
    public void delete(TrafficLight entity) {
        trafficLights.remove(entity);
    }
}
