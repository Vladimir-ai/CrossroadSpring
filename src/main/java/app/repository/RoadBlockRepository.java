package app.repository;

import app.model.RoadBlock;
import app.model.TrafficLight;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface RoadBlockRepository {
    Optional<RoadBlock> get(UUID id);

    List<RoadBlock> getAll();

    void save(RoadBlock entity);

    void update(RoadBlock entity);

    void delete(UUID id);

    void delete(RoadBlock entity);

    void clear();

    RoadBlock getRoadBlockShiftByIndex(RoadBlock roadBlock, int index);

    RoadBlock getRoadBlockLinkByIndex(RoadBlock roadBlock, int index);

    void setRoadBlockLinkByIndex(RoadBlock roadBlockFrom, RoadBlock roadBlockTo, int index);
}
