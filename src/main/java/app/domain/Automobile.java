package app.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.Random;

@Data
public class Automobile {
    public enum DriveModel{
        LEFT,
        STRAIGHT,
        RIGHT;
    }

    private Integer targetSpeed;
    private Integer currentSpeed;
    private DriveModel driveModel;

    public Automobile(Integer targetSpeed, DriveModel driveModel) {
        this.targetSpeed = targetSpeed;
        this.driveModel = driveModel;
        this.currentSpeed = 0;
    }

    public Automobile(Integer targetSpeed) {
        this(targetSpeed, DriveModel.values()[new Random().nextInt(3)]);
    }

    public Automobile(){
        this(new Random().nextInt(90) + 30);
    }

}
