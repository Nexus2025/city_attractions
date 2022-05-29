package com.city_attractions.repository.impl;

import com.city_attractions.repository.CityRepository;
import com.city_attractions.entity.City;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class CityRepositoryImpl implements CityRepository {

    private SessionFactory sessionFactory;

    @Autowired
    public CityRepositoryImpl(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public City save(City city) {
        Session session = sessionFactory.openSession();
        Transaction tx = session.beginTransaction();

        session.saveOrUpdate(city);

        tx.commit();
        session.close();
        return city;
    }

    @Override
    public City getByNameAndCountry(String name, String country) {
        Session session = sessionFactory.openSession();
        Transaction tx = session.beginTransaction();

        Query<City> query = session.createQuery("FROM City c WHERE c.name = :name AND c.country = :country");
        query.setParameter("name", name);
        query.setParameter("country", country);
        City city = query.uniqueResult();

        tx.commit();
        session.close();
        return city;
    }

    @Override
    public City get(Integer id) {
        Session session = sessionFactory.openSession();
        Transaction tx = session.beginTransaction();

        City city = session.get(City.class, id);

        tx.commit();
        session.close();
        return city;
    }
}
