package app.repository;

import app.domain.DTO.AutomobileDTO;
import app.domain.entity.Automobile;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import org.springframework.stereotype.Repository;

@Repository
public interface AutomobileRepository {
    Optional<Automobile> get(Long id);

    List<Automobile> getAll();

    void save(Automobile entity);

    void update(Automobile entity);

    void delete(Long id);

    void delete(Automobile entity);

    public void clear();
}
