package app.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

public class Automobile {
    public enum DriveModel{
        LEFT,
        STRAIGHT,
        RIGHT;
    }

    private Integer targetSpeed;

    @Getter
    private Integer currentSpeed;

    @Getter
    private DriveModel driveModel;

    public Automobile(Integer targetSpeed){
        //TO-DO
    }


}
