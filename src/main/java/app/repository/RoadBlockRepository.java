package app.repository;

import app.interfaces.DataRepository;
import app.model.RoadBlock;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import org.springframework.stereotype.Repository;

@Repository
public class RoadBlockRepository implements DataRepository<RoadBlock> {
    private List<RoadBlock> roadBlocks = new ArrayList<>();

    @Override
    public Optional<RoadBlock> get(long id) {
        return Optional.empty();
    }

    @Override
    public List<RoadBlock> getAll() {
        return roadBlocks;
    }

    @Override
    public void save(RoadBlock entity) {
        roadBlocks.add(entity);
    }

    @Override
    public void update(RoadBlock entity) {
        //TO-DO
    }

    @Override
    public void delete(long id) {
        roadBlocks.remove((int) id);
    }

    @Override
    public void delete(RoadBlock entity) {
        roadBlocks.removeIf(entity::equals);
    }

    public RoadBlock getRoadBlockShiftByIndex(RoadBlock roadBlock, int index){
        return roadBlocks.get(roadBlocks.indexOf(roadBlock) + index);
    }
}
