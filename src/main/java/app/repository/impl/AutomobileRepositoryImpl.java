package app.repository.impl;

import app.domain.DTO.AutomobileDTO;
import app.domain.entity.Automobile;
import app.repository.AutomobileRepository;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class AutomobileRepositoryImpl implements AutomobileRepository {

    private final SessionFactory sessionFactory;

    @Autowired
    public AutomobileRepositoryImpl(SessionFactory sessionFactory){
        this.sessionFactory = sessionFactory;
    }

    @Override
    public Optional<Automobile> get(Long id) {
        Session session = sessionFactory.openSession();
        var result = session.get(Automobile.class, id);
        session.close();
        return Optional.of(result);
    }

    @Override
    public List<Automobile> getAll() {
        return automobileDTOS;
    }

    @Override
    public void save(Automobile entity) {
        automobileDTOS.add(entity);
    }

    @Override
    public void update(Automobile entity) {
        automobileDTOS.stream().filter(ent -> ent.getId().equals(entity.getId())).findFirst().ifPresent(ln -> ln = entity);
    }

    @Override
    public void delete(Long id) {
        automobileDTOS.removeIf(auto -> auto.getId().equals(id));
    }

    @Override
    public void delete(Automobile entity) {
        automobileDTOS.removeIf(auto -> entity.getId().equals(auto.getId()));
    }

    @Override
    public void clear() {
        automobileDTOS.clear();
    }
}
