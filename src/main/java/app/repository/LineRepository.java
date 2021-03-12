package app.repository;

import app.domain.Line;
import java.util.List;

public interface LineRepository {
    List<Line> getAll();
}
