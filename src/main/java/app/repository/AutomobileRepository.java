package app.repository;

import app.interfaces.DataRepository;
import app.model.Automobile;
import app.model.Line;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import org.springframework.stereotype.Repository;

@Repository
public class AutomobileRepository implements DataRepository<Automobile> {
    private List<Automobile> automobiles = new ArrayList<>();

    @Override
    public Optional get(int id) {
        return Optional.empty();
    }

    @Override
    public List getAll() {
        return automobiles;
    }

    @Override
    public void save(Automobile entity) {
        automobiles.add(entity);
    }

    @Override
    public void update(Automobile entity) {
        //id search
        automobiles.stream().filter(ln -> ln.hashCode() == entity.hashCode()).findFirst().ifPresent(ln -> ln = entity);
    }

    @Override
    public void delete(long id) {
        automobiles.remove((int) id);
    }

    @Override
    public void delete(Automobile entity) {
        automobiles.removeIf(entity::equals);
    }
}
