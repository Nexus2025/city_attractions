package com.city_attractions.service;

import com.city_attractions.entity.Attraction;
import com.city_attractions.entity.AttractionType;

import java.util.List;

public interface AttractionService {
    List<Attraction> getAll(Boolean orderByName, AttractionType type);
    Attraction create(Attraction attraction);
    Attraction update(Attraction attraction, Integer id);
    void delete (Integer id);
}
