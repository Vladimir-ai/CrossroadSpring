package app.repository;

import app.domain.Line;
import java.util.ArrayList;
import java.util.List;

public class LineRepositoryStubImpl implements LineRepository{
    private static List<Line> lineList = new ArrayList<>();

    @Override
    public List<Line> getAll() {
        return lineList;
    }
}
