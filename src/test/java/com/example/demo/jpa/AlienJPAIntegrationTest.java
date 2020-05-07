package com.example.demo.jpa;

import com.example.demo.DemoApplication;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.MOCK, classes = DemoApplication.class)
@AutoConfigureMockMvc
@TestPropertySource(locations = "classpath:application-integration-test.properties")
public class AlienJPAIntegrationTest {

    @Autowired
    private MockMvc mvc;

    @Test
    public void get() throws Exception {
        mvc.perform(
                MockMvcRequestBuilders.get("/api/get/101")
                .contentType(MediaType.APPLICATION_JSON)
        ).andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    public void post() throws Exception {
        mvc.perform(
                MockMvcRequestBuilders.post("/api/create")
                .contentType(MediaType.APPLICATION_JSON)
                .content(toJsonString(new Alien(102, "Hello")))
        ).andExpect(MockMvcResultMatchers.status().isCreated());

        mvc.perform(
                MockMvcRequestBuilders.get("/api/get/102")
                        .contentType(MediaType.APPLICATION_JSON)
        ).andExpect(MockMvcResultMatchers.status().isOk());
    }

    private String toJsonString(Alien alien) throws JsonProcessingException {
        return new ObjectMapper().writeValueAsString(alien);
    }
}
