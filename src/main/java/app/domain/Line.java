package app.domain;

import lombok.Data;

@Data
public class Line {

    private int lineLength;
    private RoadBlock startBlock; //соединять линии???

    public Line(int lineLength){
        this.lineLength = lineLength;

        startBlock = new RoadBlock();
        RoadBlock curr = startBlock;

        for (int i = 0; i < lineLength - 1; i++) {
            RoadBlock next = new RoadBlock();
            curr.getAutomobileLinksList()[1] = next;
            curr = next;
        }
    }

//    public RoadBlock getBlockByIndex(int index){
//        if (index > 100 || index < 0)
//            return null;
//
//        RoadBlock block = startBlock;
//        for(int i = 0; i < index && block != null; i++){
//            block = block.getAutomobileLinksList()[1];
//        }
//
//        return block;
//    }
//
//    public boolean[] getBlockLinksByIndex(int index){
//        RoadBlock block = getBlockByIndex(index);
//        boolean[] res = new boolean[3];
//
//        for (int i = 0; i < 3; i++)
//            res[i] = block.getAutomobileLinksList()[i] != null;
//
//        return res;
//    }

    private void BuildLine(){
    }

}
