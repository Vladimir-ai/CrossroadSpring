package app.interfaces;

import java.util.List;
import java.util.Optional;
import org.springframework.stereotype.Component;

@Component
public interface DataRepository<T> {
    Optional<T> get(int id);

    List<T> getAll();

    void save(T entity);

    void update(T entity);

    void delete(long id);

    void delete(T entity);
}
