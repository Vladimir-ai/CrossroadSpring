package app.repository.impl;

import app.domain.DTO.LineDTO;
import app.repository.LineRepository;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import org.springframework.stereotype.Repository;

@Repository
public class LineRepositoryImpl implements LineRepository {
    private final List<LineDTO> lineDTOS = new ArrayList<>();

    @Override
    public Optional<LineDTO> get(UUID id) {
        return lineDTOS.stream().filter(lineDTO -> lineDTO.getId().equals(id)).findFirst();
    }

    public Optional<LineDTO> get(int id) {
        return Optional.of(lineDTOS.get(id));
    }

    @Override
    public List<LineDTO> getAll() {
        return lineDTOS;
    }

    @Override
    public void save(LineDTO lineDTO) {
        lineDTOS.add(lineDTO);
    }

    @Override
    public void update(LineDTO lineDTO) {
//        if(lines.stream().filter(ln -> ln.equals(line)).findFirst().isEmpty())
//            return;

        //find by id
        lineDTOS.stream().filter(ln -> ln.getId().equals(lineDTO.getId())).findFirst().ifPresent(ln -> ln = lineDTO);
    }

    @Override
    public void delete(UUID id) {
        lineDTOS.removeIf(lineDTO -> lineDTO.getId().equals(id));
    }

    @Override
    public void delete(LineDTO lineDTO) {
        lineDTOS.removeIf(ln -> lineDTO.getId().equals(ln.getId()));
    }

    @Override
    public void clear() {
        lineDTOS.clear();
    }
}
