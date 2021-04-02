package app.service;

import app.component.RoadComponent;
import app.model.Automobile;
import app.model.DriveModel;
import app.model.Line;
import app.model.RoadBlock;
import app.repository.AutomobileRepository;
import app.repository.LineRepository;
import app.repository.impl.AutomobileRepositoryImpl;
import app.repository.impl.LineRepositoryImpl;
import java.util.Optional;
import java.util.Random;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CarGenerationService {
    private final LineRepository lineRepository;
    private final AutomobileRepository automobileRepository;
    private final RoadComponent roadComponent;

    @Autowired
    public CarGenerationService(LineRepository lineRepository,
                                AutomobileRepository automobileRepository,
                                RoadComponent roadComponent) {

        this.lineRepository = lineRepository;
        this.automobileRepository = automobileRepository;
        this.roadComponent = roadComponent;
    }

    public void generateCars(int count){
        Random random = new Random();

        for (int i = 0; i < count && i < roadComponent.getLinesPerSide() * 4; i++){
            Optional<Line> ln;

            do{
                int lineNum = random.nextInt(roadComponent.getLinesPerSide() * 4);
                ln = lineRepository.get(lineNum);
            }
            while (!ln.isPresent() || (ln.isPresent() && ln.get().getStartBlock().getAutomobile() != null));

            RoadBlock startBlock = ln.get().getStartBlock();
            int autoSpeed  = random.nextInt(roadComponent.getMaxAutoSpeed() - roadComponent.getMinAutoSpeed());

            Automobile automobile = new Automobile(autoSpeed + roadComponent.getMinAutoSpeed(),
                    DriveModel.values()[random.nextInt(DriveModel.values().length)],
                    startBlock);
            startBlock.setAutomobile(automobile);

            automobileRepository.save(automobile);
        }
    }
}
