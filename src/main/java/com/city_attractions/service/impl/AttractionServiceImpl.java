package com.city_attractions.service.impl;

import com.city_attractions.entity.Attraction;
import com.city_attractions.entity.AttractionType;
import com.city_attractions.exception.DataNotFoundException;
import com.city_attractions.exception.DuplicateDataException;
import com.city_attractions.exception.IncorrectDataException;
import com.city_attractions.repository.AttractionRepository;
import com.city_attractions.repository.CityRepository;
import com.city_attractions.service.AttractionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class AttractionServiceImpl implements AttractionService {

    private AttractionRepository attractionRepository;
    private CityRepository cityRepository;

    @Autowired
    public AttractionServiceImpl(AttractionRepository attractionRepository, CityRepository cityRepository) {
        this.attractionRepository = attractionRepository;
        this.cityRepository = cityRepository;
    }

    @Override
    public List<Attraction> getAll(Boolean orderByName, AttractionType type) {
        List<Attraction> attractions = attractionRepository.getAll();

        if (orderByName != null && orderByName) {
            attractions = attractions.stream()
                    .sorted(Comparator.comparing(Attraction::getName))
                    .collect(Collectors.toList());
        }

        if (type != null) {
            attractions = attractions.stream()
                    .filter(a -> a.getType().equals(type))
                    .collect(Collectors.toList());
        }

        return attractions;
    }

    @Override
    public Attraction create(Attraction attraction) {
        if (attraction.getId() != null) {
            throw new IncorrectDataException("Field ID must not be specified");
        }

        if (cityRepository.get(attraction.getCity().getId()) == null) {
            throw new DataNotFoundException(String.format("City with id= %d does not exist", attraction.getCity().getId()));
        }

        if (checkDuplicate(attraction)) {
            throw new DuplicateDataException(String.format("Attraction with name= %s and cityId= %d already exists",
                            attraction.getName(), attraction.getCity().getId()));
        }

        attractionRepository.save(attraction);
        return attraction;
    }

    @Override
    public Attraction update(Attraction attraction, Integer id) {
        Attraction fromBd = attractionRepository.get(id);
        if (fromBd == null) {
            throw new DataNotFoundException(String.format("Attraction with id= %d does not exist", id));
        }

        if (attraction.getDescription() != null) {
            fromBd.setDescription(attraction.getDescription());
        }

        return attractionRepository.save(fromBd);
    }

    @Override
    public void delete (Integer id) {
        if (attractionRepository.get(id) == null) {
            throw new DataNotFoundException(String.format("Attraction with id= %s does not exist", id));
        }
        attractionRepository.delete(id);
    }

    private boolean checkDuplicate(Attraction attraction) {
        Attraction fromBd = attractionRepository.getByNameAndCityId(attraction.getName(), attraction.getCity().getId());
        if (fromBd != null && attraction.getName().equalsIgnoreCase(fromBd.getName())) {
            return attraction.getCity().getId().equals(fromBd.getCity().getId());
        }

        return false;
    }
}