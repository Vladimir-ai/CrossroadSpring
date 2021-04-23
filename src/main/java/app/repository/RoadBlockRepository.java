package app.repository;

import app.domain.DTO.RoadBlockDTO;
import app.domain.entity.RoadBlock;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface RoadBlockRepository {
    Optional<RoadBlock> get(Long id);

    List<RoadBlock> getAll();

    void save(RoadBlock entity);

    void update(RoadBlock entity);

    void delete(Long id);

    void delete(RoadBlock entity);

    void clear();

    RoadBlockDTO getRoadBlockShiftByIndex(RoadBlockDTO roadBlockDTO, int index);

    RoadBlockDTO getRoadBlockLinkByIndex(RoadBlockDTO roadBlockDTO, int index);

    void setRoadBlockLinkByIndex(RoadBlockDTO roadBlockDTOFrom, RoadBlockDTO roadBlockDTOTo, int index);
}
