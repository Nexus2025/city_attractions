package com.city_attractions.repository;

import com.city_attractions.entity.City;

public interface CityRepository {
    City get(Integer id);
    City getByNameAndCountry(String name, String country);
    City save(City city);
}
