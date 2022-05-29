package com.city_attractions.repository;

import com.city_attractions.entity.Attraction;

import java.util.List;

public interface AttractionRepository {
    List<Attraction> getAll();
    List<Attraction> getAllByCityId(Integer cityId);
    Attraction get(Integer id);
    Attraction getByNameAndCityId(String name, Integer cityId);
    Attraction save(Attraction attraction);
    void delete(Integer id);
}
