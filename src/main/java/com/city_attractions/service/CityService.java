package com.city_attractions.service;

import com.city_attractions.entity.City;
import com.city_attractions.exception.DataNotFoundException;
import com.city_attractions.exception.DuplicateDataException;
import com.city_attractions.exception.IncorrectDataException;
import com.city_attractions.repository.CityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CityService {

    private final CityRepository cityRepository;

    @Autowired
    public CityService(CityRepository cityRepository) {
        this.cityRepository = cityRepository;
    }

    public City get(Integer id) {
        City city = cityRepository.get(id);
        if (city == null) {
            throw new DataNotFoundException(String.format("City with id= %d does not exist", id));
        }

        return city;
    }

    public City create(City city) {
        if (city.getId() != null) {
            throw new IncorrectDataException("Field ID must not be specified");
        }

        if (checkDuplicate(city)) {
            throw new DuplicateDataException(String.format("City with name= %s and country= %s already exists",
                    city.getName(), city.getCountry()));
        }

        return cityRepository.save(city);
    }

    public City update(City city, Integer id) {
        City fromBd = cityRepository.get(id);
        if (fromBd == null) {
            throw new DataNotFoundException(String.format("City with id= %d does not exist", id));
        }

        if (city.getPopulation() != null) {
            fromBd.setPopulation(city.getPopulation());
        }

        if (city.getSubwayAvailability() != null) {
            fromBd.setSubwayAvailability(city.getSubwayAvailability());
        }

        return cityRepository.save(fromBd);
    }

    private boolean checkDuplicate(City city) {
        City fromBd = cityRepository.getByNameAndCountry(city.getName(), city.getCountry());
        if (fromBd != null && city.getName().equalsIgnoreCase(fromBd.getName())) {
            return city.getCountry().equalsIgnoreCase(fromBd.getCountry());
        }
        return false;
    }
}
