package app.interfaces;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import org.springframework.stereotype.Component;

@Component
public interface DataRepository<T> {
    Optional<T> get(UUID id);

    List<T> getAll();

    void save(T entity);

    void update(T entity);

    void delete(UUID id);

    void delete(T entity);

    void clear();
}
