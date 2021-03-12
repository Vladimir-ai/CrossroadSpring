package app.repository;

import app.domain.Automobile;
import java.util.ArrayList;
import java.util.List;

public class AutomobileRepositoryStubImpl implements AutomobileRepository{
    private static List<Automobile> automobileList = new ArrayList<>();

    @Override
    public List<Automobile> getAll() {
        return automobileList;
    }
}
