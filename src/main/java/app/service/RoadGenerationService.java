package app.service;

import app.component.RoadComponent;
import app.domain.DTO.LineDTO;
import app.domain.DTO.RoadBlockDTO;
import app.domain.DTO.TrafficLightDTO;
import app.domain.DTO.TrafficLightState;
import app.domain.entity.Line;
import app.domain.entity.RoadBlock;
import app.mapper.MainMapper;
import app.repository.LineRepository;
import app.repository.RoadBlockRepository;
import app.repository.TrafficLightRepository;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RoadGenerationService {
    private final RoadComponent roadComponent;

    private final LineRepository lineRepository;
    private final RoadBlockRepository roadBlockRepository;
    private final TrafficLightRepository trafficLightRepository;

    private final MainMapper mapper;

    @Autowired
    public RoadGenerationService(LineRepository lineRepository,
                                 RoadComponent roadComponent,
                                 RoadBlockRepository roadBlockRepository,
                                 TrafficLightRepository trafficLightRepository, MainMapper mapper) {
        this.lineRepository = lineRepository;
        this.roadComponent = roadComponent;
        this.roadBlockRepository = roadBlockRepository;
        this.trafficLightRepository = trafficLightRepository;
        this.mapper = mapper;
    }

    public void initRoad() {

        generateRoad();
        //initTrafficLights();
    }

    public boolean isRoadInitiated() {
        return roadBlockRepository.getAll().size() == roadComponent.getLineLength() * roadComponent.getLinesPerSide() * 4;
    }

    private void generateRoad() {
        //инициирует дороги и связывает однонаправленные
        lineRepository.clear();
        roadBlockRepository.clear();

        for (int index = 0; index < 4; index++) {
            for (int j = 0; j < roadComponent.getLinesPerSide(); j++) {
                //lineRepository.save(new Line(roadComponent.getLineLength()));
                initLine(roadComponent.getLineLength());

                if (j > 0) {
                    var a = lineRepository.get((long) (index * roadComponent.getLinesPerSide() + j));
                    var firstLine = mapper.lineToLineDTO(lineRepository.get((long) (index * roadComponent.getLinesPerSide() + j)).get());
                    var secLine = mapper.lineToLineDTO(lineRepository.get((long) (index * roadComponent.getLinesPerSide() + j + 1)).get()); // FIXED COUNTER
                    linkCoDirectionalLines(firstLine, secLine,
                            roadComponent.getLineLength());
                }
            }
        }

        //  List<Line> linesList = lineRepository.getAll();
        final int LINES_PER_SIDE = roadComponent.getLinesPerSide();
        final int LINE_LENGTH = roadComponent.getLineLength();
        final int LINE_COUNT = roadComponent.getLinesPerSide() * 4;

        //Связывает дороги на перекрестке
        for (int index = 0; index < 4; index++) {
            RoadBlockDTO leftTurn = getRoadBlockShiftByIndex(
                    mapper.blockToBlockDTO(lineRepository.get((long)(LINES_PER_SIDE * index + 1)).get().getStartBlock()), //fixed
                    LINE_LENGTH / 2);

            RoadBlockDTO rightTurn = getRoadBlockShiftByIndex(
                    mapper.blockToBlockDTO(lineRepository.get((long)(LINES_PER_SIDE * (index + 1))).get().getStartBlock()), //fixed
                    LINE_LENGTH / 2 - LINES_PER_SIDE);

            leftTurn.getAutomobileLinksList()[0] = getRoadBlockShiftByIndex(mapper.blockToBlockDTO(lineRepository
                            .get((long)(LINE_COUNT / 2 + (index < 2 ? index * LINES_PER_SIDE + 1 : -(index / 2 + index % 2) * LINES_PER_SIDE + 1))).get().getStartBlock()), //fixed
                    (LINE_LENGTH / 2 - 1));

            rightTurn.getAutomobileLinksList()[2] = getRoadBlockShiftByIndex(mapper.blockToBlockDTO(lineRepository
                            .get((long)(LINE_COUNT - (index < 2 ? index + 1 : index - 2 * (index % 2) + 1) * LINES_PER_SIDE)).get().getStartBlock()), //fixed
                    LINE_LENGTH / 2 + (LINES_PER_SIDE - 1));

            leftTurn.setIsCrossRoad(true);
            rightTurn.setIsCrossRoad(true);

            roadBlockRepository.update(mapper.blockDtoToBlockNoReccurency(leftTurn));
            roadBlockRepository.update(mapper.blockDtoToBlockNoReccurency(rightTurn));
        }
    }

    private void initLine(int lineLength) {

        RoadBlockDTO startBlock = new RoadBlockDTO();
        startBlock.setTrafficLightState(TrafficLightState.GREEN);
        RoadBlockDTO curr = startBlock;

        //roadBlockRepository.save(startBlock);

        for (int i = 0; i < lineLength - 1; i++) {
            RoadBlockDTO next = new RoadBlockDTO();
            next.setTrafficLightState(TrafficLightState.GREEN);
            //linkRoadBlockLinkByIndex(curr, next, 1);
            curr.getAutomobileLinksList()[1] = next;
            //roadBlockRepository.save(next);
            //roadBlockRepository.update(curr);

            curr = next;
        }

        lineRepository.save(mapper.lineDtoToLine(new LineDTO(startBlock, lineLength)));
    }

    private void linkRoadBlocksByIndex(RoadBlockDTO from, RoadBlockDTO to, int index){
        if (index < 0 || index > 2)
            return;

        from.getAutomobileLinksList()[index] = to;
        var aa = mapper.blockDtoToBlock(from);
        roadBlockRepository.update(mapper.blockDtoToBlock(from));
    }

    private RoadBlockDTO getRoadBlockShiftByIndex(RoadBlockDTO stratBlock, int index){
        RoadBlockDTO block = stratBlock;
        for(int i = 0; i < index; i++)
            block = block.getAutomobileLinksList()[1];

        return block;
    }
//
//    private void initTrafficLights() {
//        final int LINE_LENGTH = roadComponent.getLineLength();
//        final int LINES_PER_SIDE = roadComponent.getLinesPerSide();
//        final int TRAFFIC_LIGHT_DIST = roadComponent.getTrafficLightDist();
//
//        for (int index = 0; index < 4; index++) {
//            List<RoadBlockDTO> roadBlockDTOS = new ArrayList<>();
//
//            for (int j = 0; j < roadComponent.getLinesPerSide(); j++) {
//                RoadBlockDTO block = roadBlockRepository.getRoadBlockShiftByIndex(
//                        lineRepository.getAll().get(index * roadComponent.getLinesPerSide() + j).getStartBlock(),
//                        (LINE_LENGTH / 2 - LINES_PER_SIDE - 2 - TRAFFIC_LIGHT_DIST));
//
//                roadBlockDTOS.add(block);
//
//                for (int blockInd = 0; blockInd < TRAFFIC_LIGHT_DIST; blockInd++) {
//                    block = roadBlockRepository.getRoadBlockLinkByIndex(block, 1);
//                    roadBlockDTOS.add(block);
//                }
//            }
//
//            trafficLightRepository.save(new TrafficLightDTO(roadBlockDTOS, TrafficLightState.RED));
//            roadBlockDTOS.forEach(roadBlock -> {
//                roadBlock.setTrafficLightState(TrafficLightState.RED);
//                roadBlockRepository.update(roadBlock);
//            });
//        }
//    }
//
    private void linkCoDirectionalLines(LineDTO left, LineDTO right, int targetLineCount) {
        if (left.getLineLength() != right.getLineLength())
            return;

        RoadBlockDTO leftBlock = left.getStartBlock();
        RoadBlockDTO rightBlock = right.getStartBlock();

        for (int i = 0; i < left.getLineLength() - 1; i++) {
            RoadBlockDTO leftNextBlock = leftBlock.getAutomobileLinksList()[1];
            RoadBlockDTO rightNextBlock = rightBlock.getAutomobileLinksList()[1];

            if (!(i <= left.getLineLength() / 2 - (targetLineCount - 1) && i >= left.getLineLength() / 2 - 3)) {
                linkRoadBlocksByIndex(leftBlock, rightNextBlock, 2);
                linkRoadBlocksByIndex(rightBlock, leftNextBlock, 0);
            }

            leftBlock = leftNextBlock;
            rightBlock = rightNextBlock;
        }


    }

}
