package com.city_attractions.dto;

import javax.validation.constraints.NotNull;

public class CityDto {
    private Integer id;

    @NotNull
    private String name;

    @NotNull
    private Integer population;

    @NotNull
    private Boolean subwayAvailability;

    @NotNull
    private String country;

    public CityDto() {
    }

    public CityDto(Integer id, String name, Integer population, Boolean subwayAvailability, String country) {
        this.id = id;
        this.name = name;
        this.population = population;
        this.subwayAvailability = subwayAvailability;
        this.country = country;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getPopulation() {
        return population;
    }

    public void setPopulation(Integer population) {
        this.population = population;
    }

    public Boolean getSubwayAvailability() {
        return subwayAvailability;
    }

    public void setSubwayAvailability(Boolean subwayAvailability) {
        this.subwayAvailability = subwayAvailability;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }
}
