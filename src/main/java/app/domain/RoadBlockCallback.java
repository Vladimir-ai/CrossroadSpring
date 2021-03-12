package app.domain;

public interface RoadBlockCallback {

    RoadBlockCallback subscribe(Automobile automobile, DriveModel autoDriveModel);

    void unsubscribe(Automobile automobile);

}