package app.service;

import app.component.RoadComponent;
import app.model.TrafficLight;
import app.model.TrafficLightState;
import app.repository.RoadBlockRepository;
import app.repository.TrafficLightRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TrafficLightService {
    final RoadComponent roadComponent;
    final TrafficLightRepository trafficLightRepository;
    final RoadBlockRepository roadBlockRepository;


    @Autowired
    public TrafficLightService(TrafficLightRepository trafficLightRepository,
                               RoadComponent roadComponent,
                               RoadBlockRepository roadBlockRepository) {
        this.trafficLightRepository = trafficLightRepository;
        this.roadComponent = roadComponent;
        this.roadBlockRepository = roadBlockRepository;
    }


    public List<TrafficLight> getTrafficLightList() {
        return trafficLightRepository.getAll();
    }


    public void startAll() {
        for (TrafficLight trafficLight : trafficLightRepository.getAll()) {
            trafficLight.setLastSwitchTime(roadComponent.getCurrentTimeInSeconds());

            trafficLightRepository.update(trafficLight);
        }
    }


    public void changeCycleTimeByColor(TrafficLight trafficLight, TrafficLightState color, long time) {
        switch (color) {
            case RED: {
                trafficLight.setCycleTimeRed(time);
                break;
            }
            case YELLOW: {
                trafficLight.setCycleTimeYellow(time);
                break;
            }
            case GREEN: {
                trafficLight.setCycleTimeGreen(time);
                break;
            }
        }

        trafficLightRepository.update(trafficLight);
    }


    public void changeCycleTimeByColor(TrafficLightState color, long time) {
        trafficLightRepository.getAll().forEach(trafficLight -> changeCycleTimeByColor(trafficLight, color, time));
    }


    public void changeStateByTime() {
        trafficLightRepository.getAll().forEach(trafficLight -> {
            long time = roadComponent.getCurrentTimeInSeconds();

            switch (trafficLight.getCurrentState()) {
                case RED: {
                    if (trafficLight.getCycleTimeRed() + trafficLight.getLastSwitchTime() - time <= 0)
                        changeStateIfNeeded(trafficLight);
                    break;
                }
                case YELLOW: {
                    if (trafficLight.getCycleTimeYellow() + trafficLight.getLastSwitchTime() - time <= 0)
                        changeStateIfNeeded(trafficLight);
                    break;
                }
                case GREEN: {
                    if (trafficLight.getCycleTimeGreen() + trafficLight.getLastSwitchTime() - time <= 0)
                        changeStateIfNeeded(trafficLight);
                    break;
                }
            }
        });
    }


    private void changeStateIfNeeded(TrafficLight trafficLight) {
        trafficLight.setCurrentState(
                TrafficLightState.values()[(trafficLight.getCurrentState().ordinal() + 1) % TrafficLightState.values().length]
        );

        trafficLight.setLastSwitchTime(roadComponent.getCurrentTimeInSeconds());

        trafficLight.getControlledBlocks()
                .forEach(roadBlock -> {
                    roadBlock.setTrafficLightState(
                            TrafficLightState.values()[(trafficLight.getCurrentState().ordinal() + 1) % TrafficLightState.values().length]);
                    roadBlockRepository.update(roadBlock);
                });

        trafficLightRepository.update(trafficLight);
    }
}
