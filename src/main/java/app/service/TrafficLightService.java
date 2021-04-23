//package app.service;
//
//import app.component.RoadComponent;
//import app.domain.DTO.TrafficLightDTO;
//import app.domain.DTO.TrafficLightState;
//import app.repository.RoadBlockRepository;
//import app.repository.TrafficLightRepository;
//import java.util.List;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Service;
//
//@Service
//public class TrafficLightService {
//    final RoadComponent roadComponent;
//    final TrafficLightRepository trafficLightRepository;
//    final RoadBlockRepository roadBlockRepository;
//
//
//    @Autowired
//    public TrafficLightService(TrafficLightRepository trafficLightRepository,
//                               RoadComponent roadComponent,
//                               RoadBlockRepository roadBlockRepository) {
//        this.trafficLightRepository = trafficLightRepository;
//        this.roadComponent = roadComponent;
//        this.roadBlockRepository = roadBlockRepository;
//    }
//
//
//    public List<TrafficLightDTO> getTrafficLightList() {
//        return trafficLightRepository.getAll();
//    }
//
//
//    public void startAll() {
//        for (TrafficLightDTO trafficLightDTO : trafficLightRepository.getAll()) {
//            trafficLightDTO.setLastSwitchTime(roadComponent.getCurrentTimeInSeconds());
//
//            trafficLightRepository.update(trafficLightDTO);
//        }
//    }
//
//
//    public void changeCycleTimeByColor(TrafficLightDTO trafficLightDTO, TrafficLightState color, long time) {
//        switch (color) {
//            case RED: {
//                trafficLightDTO.setCycleTimeRed(time);
//                break;
//            }
//            case YELLOW: {
//                trafficLightDTO.setCycleTimeYellow(time);
//                break;
//            }
//            case GREEN: {
//                trafficLightDTO.setCycleTimeGreen(time);
//                break;
//            }
//        }
//
//        trafficLightRepository.update(trafficLightDTO);
//    }
//
//
//    public void changeCycleTimeByColor(TrafficLightState color, long time) {
//        trafficLightRepository.getAll().forEach(trafficLight -> changeCycleTimeByColor(trafficLight, color, time));
//    }
//
//
//    public void changeStateByTime() {
//        trafficLightRepository.getAll().forEach(trafficLight -> {
//            long time = roadComponent.getCurrentTimeInSeconds();
//
//            switch (trafficLight.getCurrentState()) {
//                case RED: {
//                    if (trafficLight.getCycleTimeRed() + trafficLight.getLastSwitchTime() - time <= 0)
//                        changeStateIfNeeded(trafficLight);
//                    break;
//                }
//                case YELLOW: {
//                    if (trafficLight.getCycleTimeYellow() + trafficLight.getLastSwitchTime() - time <= 0)
//                        changeStateIfNeeded(trafficLight);
//                    break;
//                }
//                case GREEN: {
//                    if (trafficLight.getCycleTimeGreen() + trafficLight.getLastSwitchTime() - time <= 0)
//                        changeStateIfNeeded(trafficLight);
//                    break;
//                }
//            }
//        });
//    }
//
//
//    private void changeStateIfNeeded(TrafficLightDTO trafficLightDTO) {
//        trafficLightDTO.setCurrentState(
//                TrafficLightState.values()[(trafficLightDTO.getCurrentState().ordinal() + 1) % TrafficLightState.values().length]
//        );
//
//        trafficLightDTO.setLastSwitchTime(roadComponent.getCurrentTimeInSeconds());
//
//        trafficLightDTO.getControlledBlocks()
//                .forEach(roadBlock -> {
//                    roadBlock.setTrafficLightState(trafficLightDTO.getCurrentState());
//                    roadBlockRepository.update(roadBlock);
//                });
//
//        trafficLightRepository.update(trafficLightDTO);
//    }
//}
