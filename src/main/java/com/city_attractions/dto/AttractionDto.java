package com.city_attractions.dto;

import com.city_attractions.entity.AttractionType;

import javax.validation.constraints.NotNull;
import java.time.LocalDate;

public class AttractionDto {

    private Integer id;

    @NotNull
    private String name;

    @NotNull
    private LocalDate constructionDate;

    @NotNull
    private String description;

    @NotNull
    private AttractionType type;

    @NotNull
    private Integer cityId;

    public AttractionDto() {
    }

    public AttractionDto(Integer id, String name, LocalDate constructionDate, String description, AttractionType type, Integer cityId) {
        this.id = id;
        this.name = name;
        this.constructionDate = constructionDate;
        this.description = description;
        this.type = type;
        this.cityId = cityId;
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

    public LocalDate getConstructionDate() {
        return constructionDate;
    }

    public void setConstructionDate(LocalDate constructionDate) {
        this.constructionDate = constructionDate;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public AttractionType getType() {
        return type;
    }

    public void setType(AttractionType type) {
        this.type = type;
    }

    public Integer getCityId() {
        return cityId;
    }

    public void setCityId(Integer cityId) {
        this.cityId = cityId;
    }
}
