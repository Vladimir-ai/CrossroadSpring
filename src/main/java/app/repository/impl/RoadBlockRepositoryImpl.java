package app.repository.impl;

import app.domain.DTO.RoadBlockDTO;
import app.domain.entity.Automobile;
import app.domain.entity.RoadBlock;
import app.repository.RoadBlockRepository;
import java.util.List;
import java.util.Optional;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class RoadBlockRepositoryImpl implements RoadBlockRepository {
    private final SessionFactory sessionFactory;

    @Autowired
    public RoadBlockRepositoryImpl(SessionFactory sessionFactory){
        this.sessionFactory = sessionFactory;
    }

    @Override
    public Optional<RoadBlock> get(Long id) {
        Session session = sessionFactory.openSession();
        var result = session.get(RoadBlock.class, id);
        session.close();
        return Optional.of(result);
    }

    @Override
    public List<RoadBlock> getAll() {
        Session session = sessionFactory.openSession();
        var query = session.createQuery("from roadblocks ", RoadBlock.class);
        var result = query.getResultList();
        session.close();
        return result;
    }

    @Override
    public void save(RoadBlock entity) {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        session.persist(entity);
        transaction.commit();
        session.close();
    }

    @Override
    public void update(RoadBlock entity) {
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
    public void delete(RoadBlock entity) {
        var session = sessionFactory.openSession();
        var transaction = session.beginTransaction();
        var curr = session.get(RoadBlock.class, entity.getId());
        session.delete(curr);
        transaction.commit();
        session.close();
    }

    @Override
    public void clear() {
        var session = sessionFactory.openSession();
        var transaction = session.beginTransaction();
        session.delete("from roadBlocks", RoadBlock.class);
        transaction.commit();
        session.close();
    }

    @Override
    public RoadBlockDTO getRoadBlockShiftByIndex(RoadBlockDTO roadBlockDTO, int index) {
        return null;
    }

    @Override
    public RoadBlockDTO getRoadBlockLinkByIndex(RoadBlockDTO roadBlockDTO, int index) {
        return null;
    }

    @Override
    public void setRoadBlockLinkByIndex(RoadBlockDTO roadBlockDTOFrom, RoadBlockDTO roadBlockDTOTo, int index) {

    }

//    public RoadBlockDTO getRoadBlockShiftByIndex(RoadBlockDTO roadBlockDTO, int index){
//        for (int i = 0; i < index; i++){
//            roadBlockDTO = roadBlockDTO.getAutomobileLinksList()[1];
//        }
//        return roadBlockDTO;
//        //return roadBlocks.get(roadBlocks.indexOf(roadBlock) + index);
//    }
//
//    @Override
//    public RoadBlockDTO getRoadBlockLinkByIndex(RoadBlockDTO roadBlockDTO, int index){
//        return roadBlockDTO.getAutomobileLinksList()[index];
//        //return roadBlocks.get(roadBlocks.indexOf(roadBlock)).getAutomobileLinksList()[index];
//    }
//
//    @Override
//    public void setRoadBlockLinkByIndex(RoadBlockDTO roadBlockDTOFrom, RoadBlockDTO roadBlockDTOTo, int index){
//        roadBlockDTOFrom.getAutomobileLinksList()[index] = roadBlockDTOTo;
//    }
}
