package app.repository;

import app.model.Line;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import org.springframework.stereotype.Repository;

@Repository
public interface LineRepository {
    Optional<Line> get(UUID id);

    Optional<Line> get(int id);

    List<Line> getAll();

    void save(Line line);

    void update(Line line);

    void delete(UUID id);

    void delete(Line line);

    void clear();
}
