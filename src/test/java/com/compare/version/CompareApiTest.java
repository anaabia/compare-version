package com.compare.version;

import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

@SpringBootTest
@AutoConfigureMockMvc
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class CompareApiTest {

    @Autowired
    MockMvc mockMvc;

    @Test
    public void sameVersionValueTest() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/compare?version1=1.2.4&version2=1.2.4"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().string("0"))
                .andReturn();
    }

    @Test
    public void version1SmallerValueTest() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/compare?version1=1.2&version2=1.2.4"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().string("-1"))
                .andReturn();
    }

    @Test
    public void version1SmallerSameSizeValueTest() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/compare?version1=1.2.3&version2=1.2.4"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().string("-1"))
                .andReturn();
    }


    @Test
    public void version1BiggerValueTest() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/compare?version1=1.2.4&version2=1.2"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().string("1"))
                .andReturn();
    }


    @Test
    public void version1BiggerSameSizeValueTest() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/compare?version1=1.2.4&version2=1.2.1"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().string("1"))
                .andReturn();
    }
}
