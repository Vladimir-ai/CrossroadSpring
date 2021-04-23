//package app.service;
//
//import app.component.RoadComponent;
//import app.domain.DTO.LineDTO;
//import app.domain.DTO.RoadBlockDTO;
//import app.domain.DTO.TrafficLightDTO;
//import app.domain.DTO.TrafficLightState;
//import app.repository.LineRepository;
//import app.repository.RoadBlockRepository;
//import app.repository.TrafficLightRepository;
//import java.util.ArrayList;
//import java.util.List;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Service;
//
//@Service
//public class RoadGenerationService {
//    private final RoadComponent roadComponent;
//
//    private final LineRepository lineRepository;
//    private final RoadBlockRepository roadBlockRepository;
//    private final TrafficLightRepository trafficLightRepository;
//
//    @Autowired
//    public RoadGenerationService(LineRepository lineRepository,
//                                 RoadComponent roadComponent,
//                                 RoadBlockRepository roadBlockRepository,
//                                 TrafficLightRepository trafficLightRepository) {
//        this.lineRepository = lineRepository;
//        this.roadComponent = roadComponent;
//        this.roadBlockRepository = roadBlockRepository;
//        this.trafficLightRepository = trafficLightRepository;
//    }
//
//    public void initRoad() {
//        roadBlockRepository.clear();
//        trafficLightRepository.clear();
//
//        generateRoad();
//        initTrafficLights();
//    }
//
//    public boolean isRoadInitiated() {
//        return roadBlockRepository.getAll().size() == roadComponent.getLineLength() * roadComponent.getLinesPerSide() * 4;
//    }
//
//    private void generateRoad() {
//        //инициирует дороги и связывает однонаправленные
//        for (int index = 0; index < 4; index++) {
//            for (int j = 0; j < roadComponent.getLinesPerSide(); j++) {
//                //lineRepository.save(new Line(roadComponent.getLineLength()));
//                initLine(roadComponent.getLineLength());
//
//                if (j > 0)
//                    linkCoDirectionalLines(lineRepository.get(index * roadComponent.getLinesPerSide() + j - 1).get(),
//                            lineRepository.get(index * roadComponent.getLinesPerSide() + j).get(),
//                            roadComponent.getLineLength());
//            }
//        }
//
//        //  List<Line> linesList = lineRepository.getAll();
//        final int LINES_PER_SIDE = roadComponent.getLinesPerSide();
//        final int LINE_LENGTH = roadComponent.getLineLength();
//        final int LINE_COUNT = roadComponent.getLinesPerSide() * 4;
//
//        //Связывает дороги на перекрестке
//        for (int index = 0; index < 4; index++) {
//            RoadBlockDTO leftTurn = roadBlockRepository.getRoadBlockShiftByIndex(
//                    lineRepository.get(LINES_PER_SIDE * index).get().getStartBlock(),
//                    LINE_LENGTH / 2);
//
//            RoadBlockDTO rightTurn = roadBlockRepository.getRoadBlockShiftByIndex(
//                    lineRepository.get(LINES_PER_SIDE * (index + 1) - 1).get().getStartBlock(),
//                    LINE_LENGTH / 2 - LINES_PER_SIDE);
//
//            leftTurn.getAutomobileLinksList()[0] = roadBlockRepository.getRoadBlockShiftByIndex(lineRepository
//                            .get(LINE_COUNT / 2 + (index < 2 ? index * LINES_PER_SIDE : -(index / 2 + index % 2) * LINES_PER_SIDE)).get().getStartBlock(),
//                    (LINE_LENGTH / 2 - 1));
//
//            rightTurn.getAutomobileLinksList()[2] = roadBlockRepository.getRoadBlockShiftByIndex(lineRepository
//                            .get(LINE_COUNT - (index < 2 ? index : index - 2 * (index % 2) + 1) * LINES_PER_SIDE - 1).get().getStartBlock(),
//                    LINE_LENGTH / 2 + (LINES_PER_SIDE - 1));
//
//            leftTurn.setIsCrossRoad(true);
//            rightTurn.setIsCrossRoad(true);
//            roadBlockRepository.update(leftTurn);
//            roadBlockRepository.update(rightTurn);
//        }
//    }
//
//    private void initLine(int lineLength) {
//        RoadBlockDTO startBlock = new RoadBlockDTO();
//        startBlock.setTrafficLightState(TrafficLightState.GREEN);
//        RoadBlockDTO curr = startBlock;
//
//        roadBlockRepository.save(curr);
//
//        for (int i = 0; i < lineLength - 1; i++) {
//            RoadBlockDTO next = new RoadBlockDTO();
//            next.setTrafficLightState(TrafficLightState.GREEN);
//            roadBlockRepository.setRoadBlockLinkByIndex(curr, next, 1);
//
//            roadBlockRepository.save(next);
//            roadBlockRepository.update(curr);
//
//            curr = next;
//        }
//
//
//        lineRepository.save(new LineDTO(lineLength, startBlock));
//    }
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
//    private void linkCoDirectionalLines(LineDTO left, LineDTO right, int targetLineCount) {
//        if (left.getLineLength() != right.getLineLength())
//            return;
//        RoadBlockDTO leftBlock = left.getStartBlock();
//
//        RoadBlockDTO rightBlock = right.getStartBlock();
//
//        for (int i = 0; i < left.getLineLength() - 1; i++) {
//            RoadBlockDTO leftNextBlock = roadBlockRepository.getRoadBlockLinkByIndex(leftBlock, 1);
//            RoadBlockDTO rightNextBlock = roadBlockRepository.getRoadBlockLinkByIndex(rightBlock, 1);
//
//            if (!(i <= left.getLineLength() / 2 - (targetLineCount - 1) && i >= left.getLineLength() / 2 - 3)) {
//                roadBlockRepository.setRoadBlockLinkByIndex(leftBlock, rightNextBlock, 2);
//                roadBlockRepository.setRoadBlockLinkByIndex(rightBlock, leftNextBlock, 0);
//            }
//
//            leftBlock = leftNextBlock;
//            rightBlock = rightNextBlock;
//        }
//    }
//
//}
