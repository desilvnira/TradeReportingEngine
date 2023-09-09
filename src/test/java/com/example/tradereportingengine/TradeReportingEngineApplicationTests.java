package com.example.tradereportingengine;

import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.util.ResourceUtils;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@SpringBootTest
@AutoConfigureMockMvc
class TradeReportingEngineApplicationTests {

    @Autowired
    private MockMvc mockMvc;

    @Order(1)
    @Test
    void postTest() {
        try {
            mockMvc.perform(post("/setTrades"))
                    .andExpect(status().isCreated());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
    @Order(2)
    @Test
    void getTest() throws IOException {
        File file = ResourceUtils.getFile("src/test/resources/mockTrades.json");
        String content = new String(Files.readAllBytes(file.toPath()));
        try {
            mockMvc.perform(get("/getTrades")).andExpect(content().json(content));
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

}
