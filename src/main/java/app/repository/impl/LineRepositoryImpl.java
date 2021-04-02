package app.repository.impl;

import app.model.Line;
import app.repository.LineRepository;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import org.springframework.stereotype.Repository;

@Repository
public class LineRepositoryImpl implements LineRepository {
    private final List<Line> lines = new ArrayList<>();

    @Override
    public Optional<Line> get(UUID id) {
        return lines.stream().filter(line -> line.getId().equals(id)).findFirst();
    }

    public Optional<Line> get(int id) {
        return Optional.of(lines.get(id));
    }

    @Override
    public List<Line> getAll() {
        return lines;
    }

    @Override
    public void save(Line line) {
        lines.add(line);
    }

    @Override
    public void update(Line line) {
//        if(lines.stream().filter(ln -> ln.equals(line)).findFirst().isEmpty())
//            return;

        //find by id
        lines.stream().filter(ln -> ln.getId().equals(line.getId())).findFirst().ifPresent(ln -> ln = line);
    }

    @Override
    public void delete(UUID id) {
        lines.removeIf(line -> line.getId().equals(id));
    }

    @Override
    public void delete(Line line) {
        lines.removeIf(ln -> line.getId().equals(ln.getId()));
    }

    @Override
    public void clear() {
        lines.clear();
    }
}
