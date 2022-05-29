package com.city_attractions.controller;

import com.city_attractions.dto.AttractionDto;
import com.city_attractions.dto.AttractionMapper;
import com.city_attractions.entity.Attraction;
import com.city_attractions.entity.AttractionType;
import com.city_attractions.entity.City;
import com.city_attractions.service.AttractionService;
import com.city_attractions.service.CityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping(value = "/rest/attractions", produces = MediaType.APPLICATION_JSON_VALUE)
public class AttractionController {

    private AttractionService attractionService;
    private CityService cityService;

    @Autowired
    public AttractionController(AttractionService attractionService, CityService cityService) {
        this.attractionService = attractionService;
        this.cityService = cityService;
    }

    @GetMapping
    public List<AttractionDto> getAll(@RequestParam(required = false) Boolean orderByName,
                                      @RequestParam(required = false) AttractionType type) {

        return attractionService.getAll(orderByName, type)
                .stream().map(AttractionMapper::attractionToDto)
                .collect(Collectors.toList());
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public AttractionDto create(@Valid @RequestBody AttractionDto attractionDto) {
        City city = cityService.get(attractionDto.getCityId());
        Attraction attraction = AttractionMapper.attractionFromDto(attractionDto);
        attraction.setCity(city);
        return AttractionMapper.attractionToDto(attractionService.create(attraction));
    }

    @PutMapping(value = "/{id}", consumes = MediaType.APPLICATION_JSON_VALUE)
    public AttractionDto update(@RequestBody AttractionDto attractionDto, @PathVariable Integer id) {
        return AttractionMapper.attractionToDto(attractionService.update(AttractionMapper.attractionFromDto(attractionDto), id));
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Integer id) {
        attractionService.delete(id);
    }
}