package com.city_attractions.util;

import com.city_attractions.dto.AttractionDto;
import com.city_attractions.entity.Attraction;
import com.city_attractions.entity.AttractionType;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class AttractionUtil {

    public static List<Attraction> getList() {
        List<Attraction> attractions = new ArrayList<>();
        attractions.add(new Attraction(1, "One", LocalDate.parse("1988-12-12"), "someOne", AttractionType.BUILDING));
        attractions.add(new Attraction(2, "Two", LocalDate.parse("1918-10-31"), "someTwo", AttractionType.BUILDING));
        attractions.add(new Attraction(3, "Three", LocalDate.parse("1900-11-11"), "someThree", AttractionType.BUILDING));

        attractions.get(0).setCity(CityUtil.getCity());
        attractions.get(1).setCity(CityUtil.getCity());
        attractions.get(2).setCity(CityUtil.getCity());
        return attractions;
    }

    public static Attraction getAttraction() {
        Attraction attraction = new Attraction(1, "One", LocalDate.parse("1988-12-12"), "someOne", AttractionType.BUILDING);
        attraction.setCity(CityUtil.getCity());
        return attraction;
    }

    public static AttractionDto getAttractionDto() {
        return new AttractionDto(1, "One", LocalDate.parse("1988-12-12"), "someOne", AttractionType.BUILDING, 2);
    }

    public static AttractionDto getNewAttractionDto() {
        return new AttractionDto(0, "One", LocalDate.parse("1988-12-12"), "someOne", AttractionType.BUILDING, 2);
    }

    public static List<AttractionDto> getListDto() {
        List<AttractionDto> attractions = new ArrayList<>();
        attractions.add(new AttractionDto(1, "One", LocalDate.parse("1988-12-12"), "someOne", AttractionType.BUILDING, 2));
        attractions.add(new AttractionDto(2, "Two", LocalDate.parse("1918-10-31"), "someTwo", AttractionType.BUILDING, 2));
        attractions.add(new AttractionDto(3, "Three", LocalDate.parse("1900-11-11"), "someThree", AttractionType.BUILDING, 2));
        return attractions;
    }
}
