package ru.kifor4ik.repository;

import jakarta.persistence.NamedNativeQuery;
import jakarta.persistence.Query;
import jakarta.persistence.TypedQuery;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import jakarta.validation.ConstraintViolationException;
import org.hibernate.LockMode;
import org.hibernate.Session;
import org.hibernate.annotations.NamedQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import ru.kifor4ik.entity.EventEntity;

import javax.annotation.PreDestroy;
import java.sql.Date;
import java.sql.Time;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;
import java.util.Objects;

@Repository
public class EventRepository implements CRUDrepository<EventEntity> {

    private Session session;

    @Autowired
    public EventRepository(Session session) {
        this.session = session;
    }

    @Override
    public boolean createOrUpdate(EventEntity event) {
        try {
            session.beginTransaction();
            session.merge(event);
            session.getTransaction().commit();
            return true;
        } catch (ConstraintViolationException e){
            throw e;
        } catch (Exception e) {
            session.getTransaction().rollback();
        }
        return false;
    }

    @Override
    public EventEntity getById(Long id) {
        try {
            session.beginTransaction();
            EventEntity eventEntity = session.get(EventEntity.class, id);
            session.getTransaction().commit();
            return eventEntity;
        } catch (Exception e) {
            session.getTransaction().rollback();
        }
        return null;
    }



    @Override
    public boolean delete(EventEntity event) {
        try {
            session.beginTransaction();
            session.remove(event);
            session.getTransaction().commit();
            return true;
        } catch (Exception e) {
            session.getTransaction().rollback();
        }
        return false;
    }

    public List<EventEntity> getByTest(int pageSize, int page, String theme, String manager, LocalDate date, LocalTime time){
        try {

            session.beginTransaction();

            CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();
            CriteriaQuery<EventEntity> criteria = criteriaBuilder.createQuery(EventEntity.class);
            Root<EventEntity> root = criteria.from(EventEntity.class);

            Predicate totalPredicate = criteriaBuilder.and(criteriaBuilder.equal(root.get("isDeleted"), false));
            if(theme != null)
                totalPredicate = criteriaBuilder.and(totalPredicate,criteriaBuilder.like(root.get("theme"), "%"+ theme +"%"));
            if(manager != null)
                totalPredicate = criteriaBuilder.and(totalPredicate,criteriaBuilder.like(root.get("manager"), "%"+ manager +"%"));
            if(date != null)
                totalPredicate = criteriaBuilder.and(totalPredicate,criteriaBuilder.equal(root.get("startDate"), Date.valueOf(date)));
            if(time != null)
                totalPredicate = criteriaBuilder.and(totalPredicate,criteriaBuilder.equal(root.get("startTime"), Time.valueOf(time)));

            TypedQuery<EventEntity> typedQuery = session.createQuery(criteria.select(root).where(totalPredicate));

            typedQuery.setFirstResult(page);
            if(pageSize == 0) pageSize = 10;
            typedQuery.setMaxResults(pageSize);

            List<EventEntity> temp = typedQuery.getResultList();

            session.getTransaction().commit();
            return temp;
        } catch (Exception e){
            session.getTransaction().rollback();
        }
        return null;
    }

    @PreDestroy
    public void close() {
        System.out.println("Event close session");
        session.close();
    }
}
