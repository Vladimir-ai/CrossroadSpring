package app.service;

import app.domain.DTO.AutomobileDTO;
import app.domain.DTO.RoadBlockDTO;
import app.domain.DTO.TrafficLightState;
import app.repository.AutomobileRepository;
import app.repository.RoadBlockRepository;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CarMovingService {

    final RoadBlockRepository roadBlockRepository;
    final AutomobileRepository automobileRepository;

    @Autowired
    public CarMovingService(RoadBlockRepository roadBlockRepository, AutomobileRepository automobileRepository) {
        this.roadBlockRepository = roadBlockRepository;
        this.automobileRepository = automobileRepository;
    }

    public void moveAllCars() {
        List<UUID> carsToRemove = new ArrayList<>();

        automobileRepository.getAll().forEach(auto -> {

            for (int i = 0; i < auto.getSpeed(); i++) {
                RoadBlockDTO currRoadBlockDTO = auto.getRoadBlockDTO();

                if (currRoadBlockDTO != null)
                    if (currRoadBlockDTO.getAutomobileLinksList()[0] == null &&
                            currRoadBlockDTO.getAutomobileLinksList()[1] == null &&
                            currRoadBlockDTO.getAutomobileLinksList()[2] == null) {

                        moveCar(currRoadBlockDTO, null, auto);
                        carsToRemove.add(auto.getId());

                    } else if (currRoadBlockDTO.getAutomobileLinksList()[auto.getDriveModelDTO().ordinal()] != null &&
                            currRoadBlockDTO.getAutomobileLinksList()[auto.getDriveModelDTO().ordinal()].getAutomobileDTO() == null && !auto.getHasTurned()) {

                        RoadBlockDTO nextRoadBlockDTO = currRoadBlockDTO.getAutomobileLinksList()[auto.getDriveModelDTO().ordinal()];

                        if(currRoadBlockDTO.getIsCrossRoad() && !auto.getHasTurned())
                            auto.setHasTurned(true);

                        moveCar(currRoadBlockDTO, nextRoadBlockDTO, auto);

                    } else if (currRoadBlockDTO.getAutomobileLinksList()[1] != null &&
                            (currRoadBlockDTO.getAutomobileLinksList()[auto.getDriveModelDTO().ordinal()] == null || auto.getHasTurned())) {

                        moveCar(currRoadBlockDTO, currRoadBlockDTO.getAutomobileLinksList()[1], auto);
                    }
            }
        });

        carsToRemove.forEach(automobileRepository::delete);
    }

    public List<AutomobileDTO> getAllAutomobiles() {
        return automobileRepository.getAll();
    }

    private void moveCar(RoadBlockDTO currBlock, RoadBlockDTO nextBlock, AutomobileDTO auto) {

        if (nextBlock == null) {
            currBlock.setAutomobileDTO(null);
            auto.setRoadBlockDTO(null);
            return;
        }

        //aaaaa
        if (nextBlock.getAutomobileDTO() == null &&
                nextBlock.getTrafficLightState().ordinal() == TrafficLightState.GREEN.ordinal()) {

            nextBlock.setAutomobileDTO(auto);
            currBlock.setAutomobileDTO(null);
            auto.setRoadBlockDTO(nextBlock);
        } else
            return;

        roadBlockRepository.update(currBlock);
        roadBlockRepository.update(nextBlock);
        automobileRepository.update(auto);
    }
}
