package com.city_attractions.util;

import com.city_attractions.dto.CityDto;
import com.city_attractions.entity.City;

public class CityUtil {

    public static City getCity() {
        City city = new City(2, "City", 1000, true, "Country");
        return city;
    }

    public static CityDto getCityDto() {
        CityDto city = new CityDto(2, "City", 1000, true, "Country");
        return city;
    }

    public static City getNewCity() {
        City city = new City(null, "City", 1000, true, "Country");
        return city;
    }
}
