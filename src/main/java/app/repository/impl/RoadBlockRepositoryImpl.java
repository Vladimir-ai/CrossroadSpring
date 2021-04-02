package app.repository.impl;

import app.model.RoadBlock;
import app.model.TrafficLight;
import app.repository.RoadBlockRepository;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import org.springframework.stereotype.Repository;

@Repository
public class RoadBlockRepositoryImpl implements RoadBlockRepository {
    private final List<RoadBlock> roadBlocks = new ArrayList<>();

    @Override
    public Optional<RoadBlock> get(UUID id) {
        return roadBlocks.stream().filter(block -> block.getId().equals(id)).findFirst();
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
        roadBlocks.stream().filter(block -> block.getId().equals(entity.getId())).findFirst().ifPresent(block -> block = entity);
    }

    @Override
    public void delete(UUID id) {
        roadBlocks.removeIf(block -> block.getId().equals(id));
    }

    @Override
    public void delete(RoadBlock entity) {
        roadBlocks.removeIf(block -> block.getId().equals(entity.getId()));
    }

    @Override
    public void clear() {
        roadBlocks.clear();
    }

    public RoadBlock getRoadBlockShiftByIndex(RoadBlock roadBlock, int index){
        for (int i = 0; i < index; i++){
            roadBlock = roadBlock.getAutomobileLinksList()[1];
        }
        return roadBlock;
        //return roadBlocks.get(roadBlocks.indexOf(roadBlock) + index);
    }

    @Override
    public RoadBlock getRoadBlockLinkByIndex(RoadBlock roadBlock, int index){
        return roadBlock.getAutomobileLinksList()[index];
        //return roadBlocks.get(roadBlocks.indexOf(roadBlock)).getAutomobileLinksList()[index];
    }

    @Override
    public void setRoadBlockLinkByIndex(RoadBlock roadBlockFrom, RoadBlock roadBlockTo, int index){
        roadBlockFrom.getAutomobileLinksList()[index] = roadBlockTo;
    }
}
