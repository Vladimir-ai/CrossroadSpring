package app.repository.impl;

import app.domain.entity.Automobile;
import app.repository.AutomobileRepository;
import java.util.List;
import java.util.Optional;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
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
        Session session = sessionFactory.openSession();
        var query = session.createQuery("from automobiles", Automobile.class);
        List<Automobile> result = query.getResultList();
        session.close();
        return result;
    }

    @Override
    public void save(Automobile entity) {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        session.save(entity);
        transaction.commit();
        session.close();
    }

    @Override
    public void update(Automobile entity) {
        var current = get(entity.getId());
        Session session = sessionFactory.openSession();
        var trans = session.beginTransaction();
        session.update(entity);
        trans.commit();
        session.close();
    }

    @Override
    public void delete(Long id) {
        var session = sessionFactory.openSession();
        var trans = session.beginTransaction();
        var curr = session.get(Automobile.class, id);
        session.delete(curr);
        trans.commit();
        session.close();
    }

    @Override
    public void delete(Automobile entity) {
        var session = sessionFactory.openSession();
        var transaction = session.beginTransaction();
        var curr = session.get(Automobile.class, entity.getId());
        session.delete(curr);
        transaction.commit();
        session.close();
    }

    @Override
    public void clear() {
        var session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        session.createQuery("delete from automobiles").executeUpdate();
        transaction.commit();
        session.close();
    }
}
