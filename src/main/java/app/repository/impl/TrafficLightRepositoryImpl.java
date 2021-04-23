package app.repository.impl;


import app.domain.entity.Automobile;
import app.domain.entity.TrafficLight;
import app.repository.TrafficLightRepository;
import java.util.List;
import java.util.Optional;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class TrafficLightRepositoryImpl implements TrafficLightRepository {
    private final SessionFactory sessionFactory;

    @Autowired
    public TrafficLightRepositoryImpl(SessionFactory sessionFactory){
        this.sessionFactory = sessionFactory;
    }



    @Override
    public Optional<TrafficLight> get(Long id) {
        Session session = sessionFactory.openSession();
        var result = session.get(TrafficLight.class, id);
        session.close();
        return Optional.of(result);
    }

    @Override
    public List<TrafficLight> getAll() {
        Session session = sessionFactory.openSession();
        var query = session.createQuery("from trafficLight ", TrafficLight.class);
        var result = query.getResultList();
        session.close();
        return result;
    }

    @Override
    public void save(TrafficLight entity) {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        session.persist(entity);
        transaction.commit();
        session.close();
    }

    @Override
    public void update(TrafficLight entity) {
        var current = get(entity.getId());
        Session session = sessionFactory.openSession();
        session.evict(current);
        session.update(entity);
        session.close();
    }

    @Override
    public void delete(Long id) {
        var session = sessionFactory.openSession();
        var curr = session.get(Automobile.class, id);
        session.delete(curr);
        session.close();
    }

    @Override
    public void delete(TrafficLight entity) {
        var session = sessionFactory.openSession();
        var curr = session.get(TrafficLight.class, entity.getId());
        session.delete(curr);
        session.close();
    }

    @Override
    public void clear() {
        var session = sessionFactory.openSession();
        session.delete("from trafficLight", TrafficLight.class);
        session.close();
    }
}
