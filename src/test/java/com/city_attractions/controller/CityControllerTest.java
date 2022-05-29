package com.city_attractions.controller;

import com.city_attractions.exception.DataNotFoundException;
import com.city_attractions.exception.DuplicateDataException;
import com.city_attractions.service.CityService;
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
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@RunWith(SpringRunner.class)
@WebMvcTest(CityController.class)
public class CityControllerTest {

    @Autowired
    private MockMvc mvc;

    @MockBean
    private CityService cityService;

    @Test
    public void getAttractionsByCityId_Should_Return_NOT_FOUND() throws Exception {
        ObjectMapper mapper = new ObjectMapper();
        mapper.findAndRegisterModules();
        mapper.configure(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS, false);

        BDDMockito.given(this.cityService.get(anyInt()))
                .willThrow(new DataNotFoundException("City with id= 0 does not exist"));

        this.mvc.perform(
                get("/rest/cities/0/attractions").accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound());
    }

    @Test
    public void create_Should_Return_OK() throws Exception {
        ObjectMapper mapper = new ObjectMapper();

        BDDMockito.given(this.cityService.create(any())).willReturn(CityUtil.getCity());

        this.mvc.perform(post("/rest/cities")
                        .content(mapper.writeValueAsString(CityUtil.getNewCity()))
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().string(mapper.writeValueAsString(CityUtil.getCityDto())));
    }

    @Test
    public void create_Should_Return_UNPROCESSABLE_ENTITY() throws Exception {
        ObjectMapper mapper = new ObjectMapper();

        BDDMockito.given(this.cityService.create(any()))
                .willThrow(new DuplicateDataException(
                        String.format("City with name= City and country= Country already exists")));

        this.mvc.perform(post("/rest/cities")
                .content(mapper.writeValueAsString(CityUtil.getNewCity()))
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isUnprocessableEntity());
    }

    @Test
    public void update_Should_Return_OK() throws Exception {

        ObjectMapper mapper = new ObjectMapper();
        mapper.findAndRegisterModules();

        BDDMockito.given(this.cityService.update(any(), anyInt())).willReturn(CityUtil.getCity());

        this.mvc.perform(put("/rest/cities/2")
                .content(mapper.writeValueAsString(CityUtil.getNewCity()))
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().string(mapper.writeValueAsString(CityUtil.getCityDto())));
    }

    @Test
    public void update_Should_Return_NOT_FOUND() throws Exception {

        ObjectMapper mapper = new ObjectMapper();
        mapper.findAndRegisterModules();

        BDDMockito.given(this.cityService.update(any(), anyInt()))
                .willThrow(new DataNotFoundException("City with id= 0 does not exist"));

        this.mvc.perform(put("/rest/cities/0")
                .content(mapper.writeValueAsString(CityUtil.getNewCity()))
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound());
    }
}
