package app.repository;

import app.component.RoadComponent;
import app.interfaces.DataRepository;
import app.model.Line;
import app.model.RoadBlock;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class LineRepository implements DataRepository<Line> {
    private List<Line> lines = new ArrayList<>();

    @Override
    public Optional<Line> get(long id) {
        return Optional.of(lines.get((int) id));
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
        lines.stream().filter(ln -> ln.hashCode() == line.hashCode()).findFirst().ifPresent(ln -> ln = line);
    }

    @Override
    public void delete(long id) {
        lines.remove((int) id);
    }

    @Override
    public void delete(Line line) {
        lines.removeIf(line::equals);
    }

}
