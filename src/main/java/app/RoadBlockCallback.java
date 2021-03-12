package app;

import app.domain.Automobile;
import app.domain.DriveModel;

public interface RoadBlockCallback {

    RoadBlockCallback subscribe(Automobile automobile, DriveModel autoDriveModel);

    void unsubscribe(Automobile automobile);

}