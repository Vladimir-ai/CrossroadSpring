package app.model;

import java.util.UUID;
import lombok.AllArgsConstructor;
import lombok.Data;

import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Automobile{

    private UUID id = UUID.randomUUID();
    private Integer speed;
    private DriveModel driveModel;
    private RoadBlock roadBlock;
    private Boolean hasTurned = false;

    public Automobile(Integer speed, DriveModel driveModel, RoadBlock roadBlock) {
        this.speed = speed;
        this.driveModel = driveModel;
        this.roadBlock = roadBlock;
    }

    //    private RoadBlockCallback roadBlockCallback;

//    public Automobile(Integer targetSpeed, DriveModel driveModel, RoadBlockCallback roadBlockCallback) {
//        this.targetSpeed = targetSpeed;
//        this.driveModel = driveModel;
//        this.currentSpeed = 0;
//        this.roadBlockCallback = roadBlockCallback;
//    }

//    public Automobile(Integer targetSpeed, RoadBlockCallback roadBlockCallback) {
//        this(targetSpeed, DriveModel.values()[new Random().nextInt(3)], roadBlockCallback);
//    }

//    public Automobile(RoadBlockCallback roadBlockCallback){
//        this(new Random().nextInt(90) + 30, roadBlockCallback);
//    }
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
