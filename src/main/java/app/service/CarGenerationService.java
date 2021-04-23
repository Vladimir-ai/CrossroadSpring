package app.service;

import app.component.RoadComponent;
import app.domain.DTO.AutomobileDTO;
import app.domain.DTO.DriveModel;
import app.domain.DTO.LineDTO;
import app.domain.DTO.RoadBlockDTO;
import app.repository.AutomobileRepository;
import app.repository.LineRepository;
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
            Optional<LineDTO> ln;

            do{
                int lineNum = random.nextInt(roadComponent.getLinesPerSide() * 4);
                ln = lineRepository.get(lineNum);
            }
            while (!ln.isPresent() || (ln.isPresent() && ln.get().getStartBlock().getAutomobileDTO() != null));

            RoadBlockDTO startBlock = ln.get().getStartBlock();
            int autoSpeed  = random.nextInt(roadComponent.getMaxAutoSpeed() - roadComponent.getMinAutoSpeed());

            AutomobileDTO automobileDTO = new AutomobileDTO(autoSpeed + roadComponent.getMinAutoSpeed(),
                    DriveModel.values()[random.nextInt(DriveModel.values().length)],
                    startBlock);
            startBlock.setAutomobileDTO(automobileDTO);

            automobileRepository.save(automobileDTO);
        }
    }
}
