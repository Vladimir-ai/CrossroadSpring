package app.temp;

import app.model.Line;
import app.model.RoadBlock;
import app.model.TrafficLight;
import java.util.ArrayList;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class World {

    private static final int LINES_PER_SIDE = 2;
    private static final int LINE_LENGTH = 100;

    private int trafficLightDist;
    private List<Line> linesList = new ArrayList<>();
    private List<TrafficLight> trafficLights = new ArrayList<>();

    public World(int trafficLightDist) {
        initAutomobileLines();
        initTrafficLights();
        this.trafficLightDist = trafficLightDist;
    }

    public World(){
        this(10);
    }


    private void initAutomobileLines() {
        //инициирует дороги и связывает однонаправленные
        for (int index = 0; index < 4; index++) {
            for (int j = 0; j < LINES_PER_SIDE; j++) {
                linesList.add(new Line(LINE_LENGTH));

                if (j > 0)
                    linkCoDirectionalLines(linesList.get(index * 2), linesList.get(index * 2 + 1), LINE_LENGTH);
            }
        }

        final int LINE_COUNT = LINES_PER_SIDE * 4;
        //Связывает дороги на перекрестке
        for (int index = 0; index < 4; index++) {
//            RoadBlock leftTurn = linesList.get(LINES_PER_SIDE * index).getBlockByIndex(LINE_LENGTH / 2);
//            RoadBlock rightTurn = linesList.get(LINES_PER_SIDE * (index + 1) - 1).getBlockByIndex(LINE_LENGTH / 2 - (LINES_PER_SIDE - 1));
//
//            leftTurn.getAutomobileLinksList()[0] = linesList
//                    .get(LINE_COUNT / 2 + (index < 2 ? index * LINES_PER_SIDE : -(index / 2 + index % 2) * LINES_PER_SIDE))
//                    .getBlockByIndex(LINE_LENGTH / 2 - 1);
//            rightTurn.getAutomobileLinksList()[2] = linesList
//                    .get(LINE_COUNT - (index < 2 ? index : index - 2 * (index % 2) + 1) * LINES_PER_SIDE - 1)
//                    .getBlockByIndex(LINE_LENGTH / 2 + (LINES_PER_SIDE - 1));
        }
    }

    private void initTrafficLights(){
        for (int index = 0; index < 4; index++){
            List<RoadBlock> roadBlocks = new ArrayList<>();

            for (int j = 0; j < LINES_PER_SIDE; j++){
//                RoadBlock block = linesList.get(index * LINES_PER_SIDE + j)
//                        .getBlockByIndex(LINE_LENGTH / 2 - LINES_PER_SIDE - 2 - trafficLightDist);
//
//                roadBlocks.add(block);
//
//                for (int blockInd = 0; blockInd < trafficLightDist; blockInd++){
//                    block = block.getAutomobileLinksList()[2];
//                    roadBlocks.add(block);
//                }
            }

          //  trafficLights.add(new TrafficLight(roadBlocks));
        }
    }

    private static void linkCoDirectionalLines(Line left, Line right, int targetLineCount){
        if (left.getLineLength() != right.getLineLength())
            return;
        RoadBlock leftBlock = left.getStartBlock();

        RoadBlock rightBlock = right.getStartBlock();

        for(int i = 0; i < left.getLineLength() - 1; i++){
            RoadBlock leftNextBlock = leftBlock.getAutomobileLinksList()[1];
            RoadBlock rightNextBlock = rightBlock.getAutomobileLinksList()[1];

            if (!(i <= left.getLineLength() / 2 - (targetLineCount - 1) && i >= left.getLineLength() / 2 - 3)) {
                leftBlock.getAutomobileLinksList()[2] = rightNextBlock;
                rightBlock.getAutomobileLinksList()[0] = leftNextBlock;
            }

            leftBlock = leftNextBlock;
            rightBlock = rightNextBlock;
        }
    }

}
