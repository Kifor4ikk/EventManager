package ru.kifor4ik.repository;

import org.hibernate.LockMode;
import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import ru.kifor4ik.entity.EventEntity;

import javax.annotation.PreDestroy;

@Repository
public class EventRepository implements CRUDrepository<EventEntity> {

    private Session session;

    @Autowired
    public EventRepository(Session session) {
        this.session = session;
        System.out.println("Session -> " + session.isOpen());
    }

    @Override
    public void create(EventEntity event) {
        try {
            session.beginTransaction();
            session.merge(event);
            session.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
            session.getTransaction().rollback();
        }
    }

    @Override
    public EventEntity getById(Long id) {
        try {

            session.beginTransaction();
            EventEntity eventEntity = session.get(EventEntity.class, id, LockMode.READ);
            session.getTransaction().commit();
            return eventEntity;
        } catch (Exception e) {
            session.getTransaction().rollback();
        }
        return null;
    }

    @Override
    public void update(EventEntity item) {

    }

    @Override
    public void delete(Long id) {

    }

    @Override
    public void softDelete(Long id) {

    }

    @PreDestroy
    public void close() {
        System.out.println("Event close session");
        session.close();
    }
}
