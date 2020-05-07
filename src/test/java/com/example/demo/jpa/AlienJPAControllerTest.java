package com.example.demo.jpa;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.hamcrest.core.Is;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(SpringRunner.class)
@WebMvcTest(AlienJPAController.class)
public class AlienJPAControllerTest {

    @Autowired
    private MockMvc mvc;

    @MockBean
    private AlienRepo alienRepo;

    @Test
    public void getAlien_givenId() throws Exception {
        when(alienRepo.findById(12)).thenReturn(Optional.ofNullable(new Alien(12, "test")));
        mvc.perform(
                MockMvcRequestBuilders.get("/api/get/12")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.aid", Is.is(12)))
                .andExpect(MockMvcResultMatchers.jsonPath("$.aname", Is.is("test")));
    }

    @Test
    public void createAlien_GivenAlien() throws Exception {

        Alien alien = new Alien(12, "test");
        mvc.perform(
                MockMvcRequestBuilders.post("/api/create")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(toJson(alien))
        ).andExpect(MockMvcResultMatchers.status().isCreated());

        verify(alienRepo).save(any());
    }

    private String toJson(final Alien alien) throws JsonProcessingException {
        return new ObjectMapper().writeValueAsString(alien);
    }
}