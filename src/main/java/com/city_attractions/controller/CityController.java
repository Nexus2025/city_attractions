package com.city_attractions.controller;

import com.city_attractions.dto.AttractionDto;
import com.city_attractions.dto.AttractionMapper;
import com.city_attractions.dto.CityDto;
import com.city_attractions.dto.CityMapper;
import com.city_attractions.service.CityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping(value = "/rest/cities", produces = MediaType.APPLICATION_JSON_VALUE)
public class CityController {

    private CityService cityService;

    @Autowired
    public CityController(CityService cityService) {
        this.cityService = cityService;
    }

    @GetMapping("/{cityId}/attractions")
    public List<AttractionDto> getAttractionsByCityId(@PathVariable Integer cityId) {
        return cityService.get(cityId).getAttractions()
                .stream()
                .map(AttractionMapper::attractionToDto)
                .collect(Collectors.toList());
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public CityDto create(@Valid @RequestBody CityDto cityDto) {
        return CityMapper.cityToDto(cityService.create(CityMapper.cityFromDto(cityDto)));
    }

    @PutMapping(value = "/{id}", consumes = MediaType.APPLICATION_JSON_VALUE)
    public CityDto update(@RequestBody CityDto cityDto, @PathVariable Integer id) {
        return CityMapper.cityToDto(cityService.update(CityMapper.cityFromDto(cityDto), id));
    }
}
