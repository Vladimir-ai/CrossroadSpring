package app.domain;

import app.RoadBlockCallback;
import lombok.Data;

import java.util.Random;

@Data
public class Automobile{

    private Integer targetSpeed;
    private Integer currentSpeed;
    private DriveModel driveModel;
    private RoadBlockCallback roadBlockCallback;

    public Automobile(Integer targetSpeed, DriveModel driveModel, RoadBlockCallback roadBlockCallback) {
        this.targetSpeed = targetSpeed;
        this.driveModel = driveModel;
        this.currentSpeed = 0;
        this.roadBlockCallback = roadBlockCallback;
    }

    public Automobile(Integer targetSpeed, RoadBlockCallback roadBlockCallback) {
        this(targetSpeed, DriveModel.values()[new Random().nextInt(3)], roadBlockCallback);
    }

    public Automobile(RoadBlockCallback roadBlockCallback){
        this(new Random().nextInt(90) + 30, roadBlockCallback);
    }
//
//    public void go(){
//        RoadBlockCallback blockCallback = roadBlockCallback.subscribe(this, driveModel);
//
//        if (blockCallback != null){
//            roadBlockCallback.unsubscribe(this);
//            roadBlockCallback = blockCallback;
//        }
//    }
}
