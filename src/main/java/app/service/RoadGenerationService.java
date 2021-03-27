package app.service;

import app.component.RoadComponent;
import app.model.Automobile;
import app.model.Line;
import app.model.RoadBlock;
import app.model.TrafficLight;
import app.model.TrafficLightState;
import app.repository.LineRepository;
import app.repository.RoadBlockRepository;
import app.repository.TrafficLightRepository;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RoadGenerationService {
    private final LineRepository lineRepository;
    private final RoadComponent roadComponent;
    private final RoadBlockRepository roadBlockRepository;
    private final TrafficLightRepository trafficLightRepository;

    @Autowired
    public RoadGenerationService(LineRepository lineRepository,
                                 RoadComponent roadComponent,
                                 RoadBlockRepository roadBlockRepository,
                                 TrafficLightRepository trafficLightRepository){
        this.lineRepository = lineRepository;
        this.roadComponent = roadComponent;
        this.roadBlockRepository = roadBlockRepository;
        this.trafficLightRepository = trafficLightRepository;
        generateRoad();
        initTrafficLights();
    }

    public boolean changePos(Automobile automobile, RoadBlock from, RoadBlock to){
        //TO-DO
        return true;
    }

    public void generateRoad(){
        //инициирует дороги и связывает однонаправленные
        for (int index = 0; index < 4; index++) {
            for (int j = 0; j < roadComponent.getLinesPerSide(); j++) {
                //lineRepository.save(new Line(roadComponent.getLineLength()));
                initLine(roadComponent.getLineLength());

                if (j > 0)
                    linkCoDirectionalLines(lineRepository.get(index * 2).get(),
                            lineRepository.get(index * 2 + 1).get(),
                            roadComponent.getLineLength());
            }
        }

      //  List<Line> linesList = lineRepository.getAll();
        final int LINES_PER_SIDE = roadComponent.getLinesPerSide();
        final int LINE_LENGTH = roadComponent.getLineLength();
        final int LINE_COUNT = roadComponent.getLinesPerSide() * 4;

        //Связывает дороги на перекрестке
        for (int index = 0; index < 4; index++) {
            RoadBlock leftTurn = roadBlockRepository.getRoadBlockShiftByIndex(
                    lineRepository.get(LINES_PER_SIDE * index).get().getStartBlock(),
                    LINE_LENGTH / 2);
            RoadBlock rightTurn = roadBlockRepository.getRoadBlockShiftByIndex(
                    lineRepository.get(LINES_PER_SIDE * (index + 1) - 1).get().getStartBlock(),
                    LINE_LENGTH / 2 - (LINES_PER_SIDE - 1));

            leftTurn.getAutomobileLinksList()[0] = roadBlockRepository.getRoadBlockShiftByIndex(lineRepository
                    .get(LINE_COUNT / 2 + (index < 2 ? index * LINES_PER_SIDE : -(index / 2 + index % 2) * LINES_PER_SIDE)).get().getStartBlock(),
                    (LINE_LENGTH / 2 - 1));
            rightTurn.getAutomobileLinksList()[2] = roadBlockRepository.getRoadBlockShiftByIndex(lineRepository
                    .get(LINE_COUNT - (index < 2 ? index : index - 2 * (index % 2) + 1) * LINES_PER_SIDE - 1).get().getStartBlock(),
                    LINE_LENGTH / 2 + (LINES_PER_SIDE - 1));
        }
    }

    private void initLine(int lineLength){
        RoadBlock startBlock = new RoadBlock();
        RoadBlock curr = startBlock;
        roadBlockRepository.save(curr);

        for (int i = 0; i < lineLength - 1; i++) {
            RoadBlock next = new RoadBlock();
            roadBlockRepository.setRoadBlockLinkByIndex(curr, next, 1);
            roadBlockRepository.save(next);
            curr = next;
        }

        lineRepository.save(new Line(lineLength, startBlock));
    }

    private void initTrafficLights() {
        final int LINE_LENGTH = roadComponent.getLineLength();
        final int LINES_PER_SIDE = roadComponent.getLinesPerSide();
        final int trafficLightDist = 20;
        for (int index = 0; index < 4; index++) {
            List<RoadBlock> roadBlocks = new ArrayList<>();

            for (int j = 0; j < roadComponent.getLinesPerSide(); j++) {
                RoadBlock block =  roadBlockRepository.getRoadBlockShiftByIndex(
                        lineRepository.getAll().get(index * roadComponent.getLinesPerSide() + j).getStartBlock(),
                        (LINE_LENGTH / 2 - LINES_PER_SIDE - 2 - trafficLightDist));

                roadBlocks.add(block);

                for (int blockInd = 0; blockInd < trafficLightDist; blockInd++){
                    block = roadBlockRepository.getRoadBlockLinkByIndex(block, 1);
                    roadBlocks.add(block);
                }
            }

            trafficLightRepository.save(new TrafficLight(roadBlocks, TrafficLightState.RED));
        }
    }

    private void linkCoDirectionalLines(Line left, Line right, int targetLineCount){
        if (left.getLineLength() != right.getLineLength())
            return;
        RoadBlock leftBlock = left.getStartBlock();

        RoadBlock rightBlock = right.getStartBlock();

        for(int i = 0; i < left.getLineLength() - 1; i++){
            RoadBlock leftNextBlock =  roadBlockRepository.getRoadBlockLinkByIndex(leftBlock, 1);
            RoadBlock rightNextBlock = roadBlockRepository.getRoadBlockLinkByIndex(rightBlock, 1);

            if (!(i <= left.getLineLength() / 2 - (targetLineCount - 1) && i >= left.getLineLength() / 2 - 3)) {
                roadBlockRepository.setRoadBlockLinkByIndex(leftBlock, rightNextBlock, 2);
                roadBlockRepository.setRoadBlockLinkByIndex(rightBlock, leftNextBlock, 0);
            }

            leftBlock = leftNextBlock;
            rightBlock = rightNextBlock;
        }
    }

}
