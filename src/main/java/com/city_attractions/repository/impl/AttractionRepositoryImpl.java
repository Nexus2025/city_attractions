package com.city_attractions.repository.impl;

import com.city_attractions.entity.Attraction;
import com.city_attractions.repository.AttractionRepository;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class AttractionRepositoryImpl implements AttractionRepository {

    private SessionFactory sessionFactory;

    @Autowired
    public AttractionRepositoryImpl(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public List<Attraction> getAll() {
        Session session = sessionFactory.openSession();
        Transaction tx = session.beginTransaction();

        List<Attraction> attractions = session.createQuery("FROM Attraction", Attraction.class).getResultList();

        tx.commit();
        session.close();
        return attractions;
    }

    @Override
    public List<Attraction> getAllByCityId(Integer cityId) {
        Session session = sessionFactory.openSession();
        Transaction tx = session.beginTransaction();

        Query<Attraction> query = session.createQuery("FROM Attraction WHERE cityId =: cityId");
        query.setParameter("cityId", cityId);
        List<Attraction> attractions = query.getResultList();

        tx.commit();
        session.close();
        return  attractions;
    }

    @Override
    public Attraction save(Attraction attraction) {
        Session session = sessionFactory.openSession();
        Transaction tx = session.beginTransaction();

        session.saveOrUpdate(attraction);

        tx.commit();
        session.close();
        return attraction;
    }

    @Override
    public void delete(Integer id) {
        Session session = sessionFactory.openSession();
        Transaction tx = session.beginTransaction();

        Query<Attraction> query = session.createQuery("DELETE FROM Attraction WHERE id =:id");
        query.setParameter("id", id);
        query.executeUpdate();

        tx.commit();
        session.close();
    }

    @Override
    public Attraction getByNameAndCityId(String name, Integer cityId) {
        Session session = sessionFactory.openSession();
        Transaction tx = session.beginTransaction();

        System.out.println(cityId);

        Query<Attraction> query = session.createQuery("FROM Attraction a WHERE a.name = :name AND a.city.id = :cityId");
        query.setParameter("name", name);
        query.setParameter("cityId", cityId);
        Attraction attraction = query.uniqueResult();

        tx.commit();
        session.close();
        return attraction;
    }

    @Override
    public Attraction get(Integer id) {
        Session session = sessionFactory.openSession();
        Transaction tx = session.beginTransaction();

        Attraction attraction = session.get(Attraction.class, id);

        tx.commit();
        session.close();
        return attraction;
    }
}