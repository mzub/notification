package ru.geekbrains.ui.service;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class UiServiceApplicationTests {

    @Autowired
    private MockMvc mvc;


    @Test
    void contextLoads() throws Exception {

        mvc.perform(get("http://localhost:8080/auth"))
                .andExpect(status().isOk());

    }

}
