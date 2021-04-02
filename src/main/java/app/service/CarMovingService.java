package app.service;

import app.model.Automobile;
import app.model.RoadBlock;
import app.model.TrafficLightState;
import app.repository.AutomobileRepository;
import app.repository.RoadBlockRepository;
import app.repository.impl.AutomobileRepositoryImpl;
import app.repository.impl.RoadBlockRepositoryImpl;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import javax.naming.NamingEnumeration;
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
                RoadBlock currRoadBlock = auto.getRoadBlock();

                if (currRoadBlock != null)
                    if (currRoadBlock.getAutomobileLinksList()[0] == null &&
                            currRoadBlock.getAutomobileLinksList()[1] == null &&
                            currRoadBlock.getAutomobileLinksList()[2] == null) {

                        moveCar(currRoadBlock, null, auto);
                        carsToRemove.add(auto.getId());

                    } else if (currRoadBlock.getAutomobileLinksList()[auto.getDriveModel().ordinal()] != null &&
                            currRoadBlock.getAutomobileLinksList()[auto.getDriveModel().ordinal()].getAutomobile() == null && !auto.getHasTurned()) {

                        RoadBlock nextRoadBlock = currRoadBlock.getAutomobileLinksList()[auto.getDriveModel().ordinal()];

                        if(currRoadBlock.getIsCrossRoad() && !auto.getHasTurned())
                            auto.setHasTurned(true);

                        moveCar(currRoadBlock, nextRoadBlock, auto);

                    } else if (currRoadBlock.getAutomobileLinksList()[1] != null &&
                            (currRoadBlock.getAutomobileLinksList()[auto.getDriveModel().ordinal()] == null || auto.getHasTurned())) {

                        moveCar(currRoadBlock, currRoadBlock.getAutomobileLinksList()[1], auto);
                    }
            }
        });

        carsToRemove.forEach(automobileRepository::delete);
    }

    public List<Automobile> getAllAutomobiles() {
        return automobileRepository.getAll();
    }

    private void moveCar(RoadBlock currBlock, RoadBlock nextBlock, Automobile auto) {

        if (nextBlock == null) {
            currBlock.setAutomobile(null);
            auto.setRoadBlock(null);
            return;
        }

        //aaaaa
        if (nextBlock.getAutomobile() == null &&
                nextBlock.getTrafficLightState().ordinal() == TrafficLightState.GREEN.ordinal()) {

            nextBlock.setAutomobile(auto);
            currBlock.setAutomobile(null);
            auto.setRoadBlock(nextBlock);
        } else
            return;

        roadBlockRepository.update(currBlock);
        roadBlockRepository.update(nextBlock);
        automobileRepository.update(auto);
    }
}
