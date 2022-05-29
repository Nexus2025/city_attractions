package com.city_attractions.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.List;

@Entity
@Table(name = "cities")
public class City {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @Column(name = "name")
    @NotNull
    private String name;

    @Column(name = "population")
    @NotNull
    private Integer population;

    @Column(name = "subway_availability")
    @NotNull
    private Boolean subwayAvailability;

    @Column(name = "country")
    @NotNull
    private String country;

    @OneToMany(fetch = FetchType.EAGER, mappedBy = "city")
    @JsonIgnore
    private List<Attraction> attractions;

    public City() {
    }

    public City(Integer id, String name, Integer population, Boolean subwayAvailability, String country) {
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

    public List<Attraction> getAttractions() {
        return attractions;
    }

    public void setAttractions(List<Attraction> attractions) {
        this.attractions = attractions;
    }

    @Override
    public String toString() {
        return "City{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", population=" + population +
                ", subwayAvailability=" + subwayAvailability +
                ", country='" + country + '\'' +
                '}';
    }
}
