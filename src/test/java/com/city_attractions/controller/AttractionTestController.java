package com.city_attractions.controller;

import com.city_attractions.entity.AttractionType;
import com.city_attractions.exception.DataNotFoundException;
import com.city_attractions.exception.DuplicateDataException;
import com.city_attractions.service.AttractionService;
import com.city_attractions.service.CityService;
import com.city_attractions.util.AttractionUtil;
import com.city_attractions.util.CityUtil;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.BDDMockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(AttractionController.class)
public class AttractionTestController {

    @Autowired
    private MockMvc mvc;

    @MockBean
    private AttractionService attractionService;

    @MockBean
    private CityService cityService;

    @Test
    public void getAll_Should_Return_OK() throws Exception {
        ObjectMapper mapper = new ObjectMapper();
        mapper.findAndRegisterModules();
        mapper.configure(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS, false);

        BDDMockito.given(this.attractionService.getAll(true, AttractionType.BUILDING))
                .willReturn(AttractionUtil.getList());

        this.mvc.perform(get("/rest/attractions?orderByName=true&type=BUILDING")
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().string(mapper.writeValueAsString(AttractionUtil.getListDto())));
    }

    @Test
    public void create_Should_Return_OK() throws Exception {
        ObjectMapper mapper = new ObjectMapper();
        mapper.findAndRegisterModules();
        mapper.configure(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS, false);

        BDDMockito.given(this.attractionService.create(any())).willReturn(AttractionUtil.getAttraction());
        BDDMockito.given(this.cityService.get(anyInt())).willReturn(CityUtil.getCity());

        this.mvc.perform(post("/rest/attractions")
                .content(mapper.writeValueAsString(AttractionUtil.getNewAttractionDto()))
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().string(mapper.writeValueAsString(AttractionUtil.getAttractionDto())));
    }

    @Test
    public void create_Should_Return_UNPROCESSABLE_ENTITY() throws Exception {
        ObjectMapper mapper = new ObjectMapper();
        mapper.findAndRegisterModules();
        mapper.configure(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS, false);

        BDDMockito.given(this.attractionService.create(any()))
                .willThrow(new DuplicateDataException("Attraction with name= Attraction and cityId= City already exists"));

        this.mvc.perform(post("/rest/attractions")
                .content(mapper.writeValueAsString(AttractionUtil.getNewAttractionDto()))
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isUnprocessableEntity());
    }

    @Test
    public void create_Should_Return_NOT_FOUND() throws Exception {
        ObjectMapper mapper = new ObjectMapper();
        mapper.findAndRegisterModules();
        mapper.configure(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS, false);

        BDDMockito.given(this.attractionService.create(any()))
                .willThrow(new DataNotFoundException("City with id= 0 does not exist"));

        this.mvc.perform(post("/rest/attractions")
                .content(mapper.writeValueAsString(AttractionUtil.getNewAttractionDto()))
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound());
    }

    @Test
    public void update_Should_Return_OK() throws Exception {
        ObjectMapper mapper = new ObjectMapper();
        mapper.findAndRegisterModules();
        mapper.configure(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS, false);

        BDDMockito.given(this.attractionService.update(any(), anyInt()))
                .willReturn(AttractionUtil.getAttraction());

        this.mvc.perform(put("/rest/attractions/1")
                .content(mapper.writeValueAsString(AttractionUtil.getNewAttractionDto()))
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().string(mapper.writeValueAsString(AttractionUtil.getAttractionDto())));
    }

    @Test
    public void update_Should_Return_NOT_FOUND() throws Exception {

        ObjectMapper mapper = new ObjectMapper();
        mapper.findAndRegisterModules();
        mapper.configure(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS, false);

        BDDMockito.given(this.attractionService.update(any(), anyInt()))
                .willThrow(new DataNotFoundException("Attraction with id= 0 does not exist"));

        this.mvc.perform(put("/rest/attractions/0")
                .content(mapper.writeValueAsString(AttractionUtil.getNewAttractionDto()))
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound());
    }

    @Test
    public void delete_Should_Return_True() throws Exception {
        this.mvc.perform(delete("/rest/attractions/1")
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isNoContent());
    }

    @Test
    public void delete_Should_Return_NOT_FOUND() throws Exception {
        BDDMockito.willThrow(new DataNotFoundException("Attraction with id= 0 does not exist"))
                .given(attractionService).delete(anyInt());

        this.mvc.perform(delete("/rest/attractions/0")
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound());
    }
}
