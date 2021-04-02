package app.repository;

import app.model.Automobile;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import org.springframework.stereotype.Repository;

@Repository
public interface AutomobileRepository {
    Optional<Automobile> get(UUID id);

    List<Automobile> getAll();

    void save(Automobile entity);

    void update(Automobile entity);

    void delete(UUID id);

    void delete(Automobile entity);

    public void clear();
}
