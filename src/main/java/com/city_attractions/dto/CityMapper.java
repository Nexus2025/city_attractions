package com.city_attractions.dto;

import com.city_attractions.entity.City;

public class CityMapper {

    public static City cityFromDto(CityDto cityDto) {
        City city = new City();
        city.setId(cityDto.getId());
        city.setName(cityDto.getName());
        city.setPopulation(cityDto.getPopulation());
        city.setSubwayAvailability(cityDto.getSubwayAvailability());
        city.setCountry(cityDto.getCountry());

        return city;
    }

    public static CityDto cityToDto(City city) {
        CityDto cityDto = new CityDto();
        cityDto.setId(city.getId());
        cityDto.setName(city.getName());
        cityDto.setPopulation(city.getPopulation());
        cityDto.setSubwayAvailability(city.getSubwayAvailability());
        cityDto.setCountry(city.getCountry());

        return cityDto;
    }
}
