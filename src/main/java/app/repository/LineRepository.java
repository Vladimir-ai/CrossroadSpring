package app.repository;

import app.domain.DTO.LineDTO;
import app.domain.entity.Line;
import java.util.List;
import java.util.Optional;
import org.springframework.stereotype.Repository;

@Repository
public interface LineRepository {
    Optional<Line> get(Long id);

    Optional<Line> get(int id);

    List<Line> getAll();

    void save(Line line);

    void update(Line line);

    void delete(Long id);

    void delete(Line line);

    void clear();
}
