package com.example.demo;


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

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(HelloController.class)
public class HelloControllerTest {

    @Autowired
    private MockMvc mvc;

    @MockBean
    private EmployeeService employeeService;

    @Test
    public void test() throws Exception {

        mvc.perform(
                MockMvcRequestBuilders.get("/api/hello")
//                        .contentType(MediaType.APPLICATION_JSON)
                        .queryParam("name", "chedditej"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", Is.is("Hello chedditej!")));

    }

    @Test
    public void test1() throws Exception {
        mvc.perform(
                MockMvcRequestBuilders.get("/api/hello")
                        .contentType(MediaType.APPLICATION_JSON)
                        .queryParam("name", "donga puppy"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", Is.is("Hello donga puppy!")));
    }

    @Test
    public void testPathParam() throws Exception {

        mvc.perform(MockMvcRequestBuilders.get("/api/emp/2")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", Is.is("Hello 2")));
    }

    @Test
    public void getEmployeeDetails() throws Exception {
        when(employeeService.getEmployee(12)).thenReturn(new Employee(12, "test", null));
        mvc.perform(
                MockMvcRequestBuilders.get("/api/emp/12")
                .contentType(MediaType.APPLICATION_JSON)
        ).andExpect(status().isOk())
                .andExpect(jsonPath("$.name", Is.is("test")));
    }
}