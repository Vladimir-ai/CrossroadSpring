package app.repository.impl;

import app.domain.DTO.RoadBlockDTO;
import app.repository.RoadBlockRepository;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import org.springframework.stereotype.Repository;

@Repository
public class RoadBlockRepositoryImpl implements RoadBlockRepository {
    private final List<RoadBlockDTO> roadBlockDTOS = new ArrayList<>();

    @Override
    public Optional<RoadBlockDTO> get(UUID id) {
        return roadBlockDTOS.stream().filter(block -> block.getId().equals(id)).findFirst();
    }

    @Override
    public List<RoadBlockDTO> getAll() {
        return roadBlockDTOS;
    }

    @Override
    public void save(RoadBlockDTO entity) {
        roadBlockDTOS.add(entity);
    }

    @Override
    public void update(RoadBlockDTO entity) {
        roadBlockDTOS.stream().filter(block -> block.getId().equals(entity.getId())).findFirst().ifPresent(block -> block = entity);
    }

    @Override
    public void delete(UUID id) {
        roadBlockDTOS.removeIf(block -> block.getId().equals(id));
    }

    @Override
    public void delete(RoadBlockDTO entity) {
        roadBlockDTOS.removeIf(block -> block.getId().equals(entity.getId()));
    }

    @Override
    public void clear() {
        roadBlockDTOS.clear();
    }

    public RoadBlockDTO getRoadBlockShiftByIndex(RoadBlockDTO roadBlockDTO, int index){
        for (int i = 0; i < index; i++){
            roadBlockDTO = roadBlockDTO.getAutomobileLinksList()[1];
        }
        return roadBlockDTO;
        //return roadBlocks.get(roadBlocks.indexOf(roadBlock) + index);
    }

    @Override
    public RoadBlockDTO getRoadBlockLinkByIndex(RoadBlockDTO roadBlockDTO, int index){
        return roadBlockDTO.getAutomobileLinksList()[index];
        //return roadBlocks.get(roadBlocks.indexOf(roadBlock)).getAutomobileLinksList()[index];
    }

    @Override
    public void setRoadBlockLinkByIndex(RoadBlockDTO roadBlockDTOFrom, RoadBlockDTO roadBlockDTOTo, int index){
        roadBlockDTOFrom.getAutomobileLinksList()[index] = roadBlockDTOTo;
    }
}
