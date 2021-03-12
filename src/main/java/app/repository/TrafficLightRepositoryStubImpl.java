package app.repository;

import app.domain.TrafficLight;
import java.util.ArrayList;
import java.util.List;

public class TrafficLightRepositoryStubImpl implements TrafficLightRepository{
    private static List<TrafficLight> trafficLights = new ArrayList<>();

    @Override
    public List<TrafficLight> getAll() {
        return null;
    }


}
