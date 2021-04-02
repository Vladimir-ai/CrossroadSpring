package app.repository.impl;

import app.model.Automobile;
import app.repository.AutomobileRepository;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import org.springframework.stereotype.Repository;

@Repository
public class AutomobileRepositoryImpl implements AutomobileRepository {
    final private List<Automobile> automobiles = new ArrayList<>();

    @Override
    public Optional<Automobile> get(UUID id) {
        return automobiles.stream().filter(automobile -> automobile.getId().equals(id)).findFirst();
    }

    @Override
    public List<Automobile> getAll() {
        return automobiles;
    }

    @Override
    public void save(Automobile entity) {
        automobiles.add(entity);
    }

    @Override
    public void update(Automobile entity) {
        automobiles.stream().filter(ent -> ent.getId().equals(entity.getId())).findFirst().ifPresent(ln -> ln = entity);
    }

    @Override
    public void delete(UUID id) {
        automobiles.removeIf(auto -> auto.getId().equals(id));
    }

    @Override
    public void delete(Automobile entity) {
        automobiles.removeIf(auto -> entity.getId().equals(auto.getId()));
    }

    @Override
    public void clear() {
        automobiles.clear();
    }
}
