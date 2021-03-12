package app.domain;

import java.util.ArrayList;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RoadBlock implements RoadBlockCallback {

    private RoadBlock[] automobileLinksList = new RoadBlock[3]; // как хранить для 1-3 полос??? пока заполняем нулями, делаем висячие на пустоту, всегда 3 полосы
    private List<Pedestrian> pedestrianLinksList;
    private Automobile automobile;
    private List<Pedestrian> pedestrianList;
    private TrafficLightState trafficLightState;

    public synchronized boolean Subscribe(Automobile auto){
        if (automobile != null)
            return false;
        this.automobile = auto;
        return true;
    }

    public synchronized void Block(TrafficLightState state){
        trafficLightState = state;
    }

    //обслуживает свой авто
    @Override
    public synchronized RoadBlockCallback subscribe(Automobile automobile, DriveModel autoDriveModel) {
        RoadBlock block;
        if (automobileLinksList[autoDriveModel.ordinal()] == null){
            block = (autoDriveModel.ordinal() == 0) ? automobileLinksList[1] : automobileLinksList[3];
        }else
            block = automobileLinksList[autoDriveModel.ordinal()];

        if (!block.Subscribe(automobile))
            return null;

        return this;
    }

    @Override
    public void unsubscribe(Automobile automobile) {
        this.automobile = null;
    }
}
