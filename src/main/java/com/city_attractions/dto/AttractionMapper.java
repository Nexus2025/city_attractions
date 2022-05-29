package com.city_attractions.dto;

import com.city_attractions.entity.Attraction;

public class AttractionMapper {

    public static AttractionDto attractionToDto(Attraction attraction) {
        AttractionDto attractionDto = new AttractionDto();
        attractionDto.setId(attraction.getId());
        attractionDto.setName(attraction.getName());
        attractionDto.setConstructionDate(attraction.getConstructionDate());
        attractionDto.setDescription(attraction.getDescription());
        attractionDto.setType(attraction.getType());
        attractionDto.setCityId(attraction.getCity().getId());

        return attractionDto;
    }

    public static Attraction attractionFromDto(AttractionDto attractionDto) {
        Attraction attraction = new Attraction();
        attraction.setId(attractionDto.getId());
        attraction.setName(attractionDto.getName());
        attraction.setConstructionDate(attractionDto.getConstructionDate());
        attraction.setDescription(attractionDto.getDescription());
        attraction.setType(attractionDto.getType());

        return attraction;
    }
}
