package com.example.schepen;

import com.example.schepen.model.Schip;
import com.example.schepen.repository.SchipRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
public class SchepenIntegrationTests {


    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private SchipRepository schipRepository;

    private Schip schip1 = new Schip("USS Enterprise", 15, "Turnhout", "Geel",2);
    private Schip schip2 = new Schip("Bismarck", 25, "Dessel", "Geel", 1);
    private Schip schip3 = new Schip("yamato", 18, "Turnhout", "Rotterdam", 3);
    private Schip schip4 = new Schip("USS Hornet", 16, "Geel", "Londen", 2);

    @BeforeEach
    public void beforeAllTests() {
        schipRepository.deleteAll();
        schipRepository.save(schip1);
        schipRepository.save(schip2);
        schipRepository.save(schip3);
        schipRepository.save(schip4);
    }

    @AfterEach
    public void afterAllTests() {
        schipRepository.deleteAll();
    }

    private ObjectMapper mapper = new ObjectMapper();

    @Test
    public void testGetSchipByNaam() throws Exception {

        mockMvc.perform(get("/schepen/naam/{naam}", "USS Enterprise"))
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name", is("USS Enterprise")))
                .andExpect(jsonPath("$.capaciteit", is(15)))
                .andExpect(jsonPath("$.startLocatie", is("Turnhout")))
                .andExpect(jsonPath("$.eindLocatie", is("Geel")))
                .andExpect(jsonPath("$.rederijId", is(2)));
    }

    @Test
    public void testGetSchepenByStartLocatie() throws Exception {
        List<Schip> schepen = new ArrayList<>();
        schepen.add(schip1);
        schepen.add(schip2);
        schepen.add(schip3);
        schepen.add(schip4);

        mockMvc.perform(get("/schepen/startlocatie/{locatie}", "Turnhout"))
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(2)))
                .andExpect(jsonPath("$[0].name", is("USS Enterprise")))
                .andExpect(jsonPath("$[0].capaciteit", is(15)))
                .andExpect(jsonPath("$[0].startLocatie", is("Turnhout")))
                .andExpect(jsonPath("$[0].eindLocatie", is("Geel")))
                .andExpect(jsonPath("$[0].rederijId", is(2)))
                .andExpect(jsonPath("$[1].name", is("yamato")))
                .andExpect(jsonPath("$[1].capaciteit", is(18)))
                .andExpect(jsonPath("$[1].startLocatie", is("Turnhout")))
                .andExpect(jsonPath("$[1].eindLocatie", is("Rotterdam")))
                .andExpect(jsonPath("$[1].rederijId", is(3)));
    }

    @Test
    public void testGetSchepenByEindLocatie() throws Exception {
        List<Schip> schepen = new ArrayList<>();
        schepen.add(schip1);
        schepen.add(schip2);
        schepen.add(schip3);
        schepen.add(schip4);

        mockMvc.perform(get("/schepen/eindlocatie/{locatie}", "Geel"))
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(2)))
                .andExpect(jsonPath("$[0].name", is("USS Enterprise")))
                .andExpect(jsonPath("$[0].capaciteit", is(15)))
                .andExpect(jsonPath("$[0].startLocatie", is("Turnhout")))
                .andExpect(jsonPath("$[0].eindLocatie", is("Geel")))
                .andExpect(jsonPath("$[0].rederijId", is(2)))
                .andExpect(jsonPath("$[1].name", is("Bismarck")))
                .andExpect(jsonPath("$[1].capaciteit", is(25)))
                .andExpect(jsonPath("$[1].startLocatie", is("Dessel")))
                .andExpect(jsonPath("$[1].eindLocatie", is("Geel")))
                .andExpect(jsonPath("$[1].rederijId", is(1)));
    }
}