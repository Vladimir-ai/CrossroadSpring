package app.repository;

import app.domain.Automobile;
import java.util.List;

public interface AutomobileRepository {
    public List<Automobile> getAll();
}
