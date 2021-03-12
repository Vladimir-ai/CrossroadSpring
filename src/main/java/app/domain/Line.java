package app.domain;

import java.util.List;
import lombok.Data;

@Data
public class Line {

    private int lineLength = 100;
    //Длина всегда 100
    RoadBlock startBlock; //соединять линии???

    public Line(int lineLength){
        this.lineLength = lineLength;
        BuildLine();
    }

    public RoadBlock getBlockByIndex(int index){
        if (index > 100 || index < 0)
            return null;

        RoadBlock block = startBlock;
        for(int i = 0; i < index && block != null; i++){
            block = block.getAutomobileLinksList()[1];
        }

        return block;
    }

    public static void linkCoDirectionalLines(Line left, Line right, int targetLineCount){
        if (left.lineLength != right.lineLength)
            return;
        RoadBlock leftBlock = left.getStartBlock();

        RoadBlock rightBlock = right.getStartBlock();

        for(int i = 0; i < left.lineLength - 1; i++){
            RoadBlock leftNextBlock = leftBlock.getAutomobileLinksList()[1];
            RoadBlock rightNextBlock = rightBlock.getAutomobileLinksList()[1];

            if (!(i <= left.lineLength / 2 - (targetLineCount - 1) && i >= left.lineLength / 2 - 3)) {
                leftBlock.getAutomobileLinksList()[2] = rightNextBlock;
                rightBlock.getAutomobileLinksList()[0] = leftNextBlock;
            }

            leftBlock = leftNextBlock;
            rightBlock = rightNextBlock;
        }

    }

    public boolean[] getBlockLinksByIndex(int index){
        RoadBlock block = getBlockByIndex(index);
        boolean[] res = new boolean[3];

        for (int i = 0; i < 3; i++)
            res[i] = block.getAutomobileLinksList()[i] != null;

        return res;
    }

    private void BuildLine(){
        startBlock = new RoadBlock();
        RoadBlock curr = startBlock;

        for (int i = 0; i < lineLength - 1; i++) {
            RoadBlock next = new RoadBlock();
            curr.getAutomobileLinksList()[1] = next;
            curr = next;
        }
    }

}
