package app.repository.impl;

import app.domain.DTO.LineDTO;
import app.domain.entity.Automobile;
import app.domain.entity.Line;
import app.repository.LineRepository;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class LineRepositoryImpl implements LineRepository {

    private final SessionFactory sessionFactory;

    @Autowired
    public LineRepositoryImpl(SessionFactory sessionFactory){
        this.sessionFactory = sessionFactory;
    }


    @Override
    public Optional<Line> get(Long id) {
        Session session = sessionFactory.openSession();
        var result = session.get(Line.class, id);
        session.close();
        return Optional.of(result);
    }


    @Override
    public List<Line> getAll() {
        Session session = sessionFactory.openSession();
        var query = session.createQuery("from lines", Line.class);
        var result = query.getResultList();
        session.close();
        return result;
    }

    @Override
    public void save(Line line) {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        session.persist(line);
        transaction.commit();
        session.close();
    }

    @Override
    public void update(Line line) {
        var current = get(line.getId());
        Session session = sessionFactory.openSession();
        session.evict(current);
        session.update(line);
        session.close();
    }

    @Override
    public void delete(Long id) {
        var session = sessionFactory.openSession();
        var curr = session.get(Line.class, id);
        session.delete(curr);
        session.close();
    }

    @Override
    public void delete(Line line) {
        var session = sessionFactory.openSession();
        var curr = session.get(Line.class, line.getId());
        session.delete(curr);
        session.close();
    }

    @Override
    public void clear() {
        var session = sessionFactory.openSession();
        session.delete("from lines", Line.class);
        session.close();
    }
}
