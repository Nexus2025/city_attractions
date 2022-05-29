package com.city_attractions.service;

import com.city_attractions.entity.City;

public interface CityService {
    City get(Integer id);
    City create(City city);
    City update(City city, Integer id);

}
